/*
 * Copyright 2016-2025 Sweden Connect
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
import net.shibboleth.shared.codec.EncodingException;
import net.shibboleth.shared.xml.ElementSupport;
import org.opensaml.core.xml.Namespace;
import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.io.AbstractXMLObjectMarshaller;
import org.opensaml.core.xml.io.MarshallingException;
import org.opensaml.core.xml.schema.XSString;
import org.w3c.dom.Element;
import se.swedenconnect.opensaml.eidas.ext.attributes.CurrentAddressType;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

/**
 * The marshaller for {@code CurrentAddressType}.
 * <p>
 * Since the eIDAS {@code CurrentAddressType} is defined to contain a string value that is in fact the Base64-encoding
 * of an XML-snippet of {@code CurrentAddressStructuredType} we have to fool around a bit here.
 * </p>
 * <p>
 * <i>Personal note: I really can't understand how anyone thinks they made things easier for implemetors when they
 * figured that out</i>.
 * </p>
 *
 * @author Martin Lindström
 */
public class CurrentAddressTypeMarshaller extends AbstractXMLObjectMarshaller {

  /**
   * Even though we do have child elements, we need to serialize those, Base64-encode them and add them as a
   * Base64-encoded string value. Therefore, we pretend that there are no child elements, and let
   * {@link #marshallElementContent(XMLObject, Element)} do the work.
   */
  @Override
  protected void marshallChildElements(@Nonnull final XMLObject xmlObject, @Nonnull final Element domElement)
      throws MarshallingException {
  }

  /**
   * The element content of a {@code CurrentAddressType} is the Base64-encoding of the serialized value of the
   * {@code CurrentAddressStructuredType}. So ... we have to get there by iterating over our child elements.
   */
  @Override
  protected void marshallElementContent(final XMLObject xmlObject, @Nonnull final Element domElement)
      throws MarshallingException {

    // Find out if the our namespace already has been defined, and if so, get the prefix.
    //
    final String namespace = CurrentAddressType.TYPE_NAME.getNamespaceURI();
    final Optional<String> _prefix = xmlObject.getNamespaceManager()
        .getNamespaces()
        .stream()
        .filter(n -> namespace.equals(n.getNamespaceURI()))
        .map(Namespace::getNamespacePrefix)
        .findFirst();
    final String prefix = _prefix.isPresent() ? _prefix.get() : CurrentAddressType.TYPE_NAME.getPrefix();

    final StringBuilder sb = new StringBuilder();

    final List<XMLObject> childXMLObjects = xmlObject.getOrderedChildren();
    if (childXMLObjects != null && !childXMLObjects.isEmpty()) {
      for (final XMLObject childXMLObject : childXMLObjects) {
        if (childXMLObject == null) {
          continue;
        }
        if (!(childXMLObject instanceof final XSString childString)) {
          throw new MarshallingException("Unexpected type of child element - " + childXMLObject.getClass().getName());
        }
        if (childString.getValue() == null) {
          continue;
        }
        final String localPart = childString.getElementQName().getLocalPart();
        sb.append(String.format("<%s:%s>%s</%s:%s>", prefix, localPart, childString.getValue(), prefix, localPart));
      }
    }
    if (!sb.isEmpty()) {
      try {
        final byte[] bytes = sb.toString().getBytes(StandardCharsets.UTF_8);
        final String base64String = Base64Support.encode(bytes, true);
        ElementSupport.appendTextContent(domElement, base64String);
      }
      catch (final EncodingException e) {
        throw new MarshallingException(e);
      }
    }
  }

}
