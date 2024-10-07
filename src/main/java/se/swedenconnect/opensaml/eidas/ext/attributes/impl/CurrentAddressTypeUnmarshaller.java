/*
 * Copyright 2016-2024 Sweden Connect
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.swedenconnect.opensaml.eidas.ext.attributes.impl;

import net.shibboleth.shared.codec.Base64Support;
import net.shibboleth.shared.codec.DecodingException;
import net.shibboleth.shared.primitive.StringSupport;
import net.shibboleth.shared.xml.XMLConstants;
import net.shibboleth.shared.xml.XMLParserException;
import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.config.XMLObjectProviderRegistrySupport;
import org.opensaml.core.xml.io.UnmarshallingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import se.swedenconnect.opensaml.eidas.common.EidasConstants;
import se.swedenconnect.opensaml.eidas.ext.attributes.CurrentAddressType;

import javax.annotation.Nonnull;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Thread safe unmarshaller for {@link CurrentAddressType}.
 *
 * @author Martin Lindström
 */
public class CurrentAddressTypeUnmarshaller extends CurrentAddressStructuredTypeUnmarshaller {

  /** Class logger. */
  private final static Logger log = LoggerFactory.getLogger(CurrentAddressTypeUnmarshaller.class);

  /**
   * Special handling of the Base64 encoded value that represents the address elements.
   */
  @Nonnull
  @Override
  public XMLObject unmarshall(final Element domElement) throws UnmarshallingException {

    Document newDocument = null;

    Node childNode = domElement.getFirstChild();
    while (childNode != null) {
      if (childNode.getNodeType() != Node.TEXT_NODE) {
        // We skip everything except for a text node.
        log.info("Ignoring node {} - it is not a text node", childNode.getNodeName());
      }
      else {
        newDocument = this.parseContents((Text) childNode, domElement);
        if (newDocument != null) {
          break;
        }
      }
      childNode = childNode.getNextSibling();
    }

    return super.unmarshall(newDocument != null ? newDocument.getDocumentElement() : domElement);
  }

  /**
   * Parses the Base64-encoded contents of a {@code CurrentAddressType} element into the actual elements that this
   * encoding represents.
   *
   * @param node the text node to parse @param domElement the DOM element that we are unmarshalling @return a new DOM
   *          document holding a clone of the {@code domElement} with its previous text node child replaced with the
   *          parsed elements @throws UnmarshallingException for unmarshalling errors @throws
   */
  private Document parseContents(final Text node, final Element domElement) throws UnmarshallingException {

    final String textContent = StringSupport.trimOrNull(node.getWholeText());
    if (textContent == null) {
      log.error("Expected Base64 encoded address elements");
      return null;
    }

    // First Base64-decode the contents ...
    //
    try {
      final byte[] bytes = Base64Support.decode(textContent);
      final String addressElements = new String(bytes, StandardCharsets.UTF_8);

      // Then build a fake XML document holding the contents in element form.
      //

      // The elements represented in 'addressElements' may have a namespace prefix,
      // so we find out if we need to include that in the XML definition.

      final Map<String, String> bindings = getNamespaceBindings(domElement);

      // There has been cases when the eidas: namespace prefix has been used to represent
      // address elements, but the response/assertion itself did not define that prefix.
      // So, let's be a little bit proactive ...
      //
      if (!bindings.containsKey(XMLConstants.XMLNS_PREFIX + ":" + EidasConstants.EIDAS_PREFIX)) {
        bindings.put(XMLConstants.XMLNS_PREFIX + ":" + EidasConstants.EIDAS_PREFIX, EidasConstants.EIDAS_NP_NS);
      }
      if (!bindings.containsKey(XMLConstants.XMLNS_PREFIX + ":" + EidasConstants.EIDAS_NP_PREFIX)) {
        bindings.put(XMLConstants.XMLNS_PREFIX + ":" + EidasConstants.EIDAS_NP_PREFIX, EidasConstants.EIDAS_NP_NS);
      }

      final StringBuilder sb = new StringBuilder();
      sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      sb.append("<").append(domElement.getNodeName());
      for (final Map.Entry<String, String> entry : bindings.entrySet()) {
        sb.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
      }
      sb.append('>');
      sb.append(addressElements);
      sb.append("</").append(domElement.getNodeName()).append(">");

      // Parse into an XML document.
      //
      final Document doc = XMLObjectProviderRegistrySupport.getParserPool()
          .parse(new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8)));

      // Copy the input dom element and replace its child node with our new nodes.
      //
      final Document newDoc = XMLObjectProviderRegistrySupport.getParserPool().newDocument();
      final Element newDom = (Element) domElement.cloneNode(true);
      for (final Map.Entry<String, String> entry : bindings.entrySet()) {
        newDom.setAttributeNS(XMLConstants.XMLNS_NS, entry.getKey(), entry.getValue());
      }
      newDoc.adoptNode(newDom);
      for (Node child; (child = newDom.getFirstChild()) != null; newDom.removeChild(child)) {
      }
      Node newChild = doc.getDocumentElement().getFirstChild();
      while (newChild != null) {
        final Node importedChild = newDoc.importNode(newChild, true);
        newDom.appendChild(importedChild);
        newChild = newChild.getNextSibling();
      }
      newDoc.appendChild(newDom);

      return newDoc;
    }
    catch (final XMLParserException | DecodingException e) {
      throw new UnmarshallingException(e);
    }
  }

  /**
   * Returns a map holding all registered namespace bindings, where the key is the qualified name of the namespace and
   * the value part is the URI.
   *
   * @param element the element to start from
   * @return a namespace map
   */
  private static Map<String, String> getNamespaceBindings(final Element element) {
    final Map<String, String> namespaceMap = new HashMap<>();
    getNamespaceBindings(element, namespaceMap);
    return namespaceMap;
  }

  /**
   * Helper method to {@link #getNamespaceBindings(Element)}
   *
   * @param element the element to parse
   * @param namespaceMap the map to fill
   */
  private static void getNamespaceBindings(final Element element, final Map<String, String> namespaceMap) {
    if (element == null) {
      return;
    }
    final NamedNodeMap attrs = element.getAttributes();
    for (int i = 0; i < attrs.getLength(); i++) {
      final Node node = attrs.item(i);
      final String name = node.getNodeName();
      if ((name != null
          && (XMLConstants.XMLNS_PREFIX.equals(name) || name.startsWith(XMLConstants.XMLNS_PREFIX + ":")))) {
        namespaceMap.put(name, node.getNodeValue());
      }
    }
    final Node parent = element.getParentNode();
    if (parent instanceof Element) {
      getNamespaceBindings((Element) parent, namespaceMap);
    }
  }

}
