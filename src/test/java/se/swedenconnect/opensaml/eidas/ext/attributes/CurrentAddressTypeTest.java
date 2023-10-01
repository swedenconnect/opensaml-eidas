/*
 * Copyright 2016-2023 Sweden Connect
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
package se.swedenconnect.opensaml.eidas.ext.attributes;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.xml.namespace.QName;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opensaml.core.xml.Namespace;
import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.XMLObjectBuilder;
import org.opensaml.core.xml.XMLObjectBuilderFactory;
import org.opensaml.core.xml.config.XMLObjectProviderRegistrySupport;
import org.opensaml.core.xml.util.XMLObjectSupport;
import org.opensaml.saml.saml2.core.Attribute;
import org.opensaml.saml.saml2.core.AttributeValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import net.shibboleth.shared.codec.Base64Support;
import net.shibboleth.shared.xml.SerializeSupport;
import se.swedenconnect.opensaml.eidas.OpenSAMLTestBase;
import se.swedenconnect.opensaml.eidas.common.EidasConstants;

/**
 * Test cases for {@link CurrentAddressType} and {@link CurrentAddressStructuredType}.
 *
 * @author Martin Lindström (martin.lindstrom@litsec.se)
 */
public class CurrentAddressTypeTest extends OpenSAMLTestBase {

  /**
   * Tests marshalling and unmarshalling of {@code CurrentAddressStructuredType}.
   *
   * @throws Exception for errors
   */
  @Test
  public void testMarshallAndUnmarshallStructured() throws Exception {

    final XMLObjectBuilderFactory builderFactory = XMLObjectProviderRegistrySupport.getBuilderFactory();

    final Object object = builderFactory.getBuilder(CurrentAddressStructuredType.TYPE_NAME)
        .buildObject(CurrentAddressStructuredType.TYPE_NAME
            .getNamespaceURI(), CurrentAddressStructuredType.TYPE_NAME.getLocalPart(), "eidas");
    final CurrentAddressStructuredType address = CurrentAddressStructuredType.class.cast(object);

    fill(address);

    // Marshall
    final Element element = XMLObjectSupport.marshall(address);
    Assertions.assertNotNull(element);

    // Unmarshall element
    final CurrentAddressStructuredType address2 = (CurrentAddressStructuredType) XMLObjectSupport
        .getUnmarshaller(CurrentAddressStructuredType.TYPE_NAME).unmarshall(element);

    verify(address, address2);

    // Test unmarshall again
    final String xml = SerializeSupport.prettyPrintXML(element);
    final Document doc = XMLObjectProviderRegistrySupport.getParserPool()
        .parse(new ByteArrayInputStream(xml.toString().getBytes("UTF-8")));

    final CurrentAddressStructuredType address3 = (CurrentAddressStructuredType) XMLObjectSupport
        .getUnmarshaller(CurrentAddressStructuredType.TYPE_NAME).unmarshall(doc.getDocumentElement());
    verify(address, address3);
  }

  /**
   * Tests marshalling and unmarshalling of {@code CurrentAddressType}.
   *
   * @throws Exception for errors
   */
  @Test
  public void testMarshallAndUnmarshall() throws Exception {

    final XMLObjectBuilderFactory builderFactory = XMLObjectProviderRegistrySupport.getBuilderFactory();

    final Object object = builderFactory.getBuilder(CurrentAddressType.TYPE_NAME).buildObject(
        CurrentAddressType.TYPE_NAME.getNamespaceURI(),
        CurrentAddressType.TYPE_NAME.getLocalPart(), "eidas");
    final CurrentAddressType address = CurrentAddressType.class.cast(object);

    fill(address);

    // Marshall
    final Element element = XMLObjectSupport.marshall(address);
    Assertions.assertNotNull(element);

    // Verify that we got one child element that is the Base64 encoding.
    final NodeList childs = element.getChildNodes();
    Assertions.assertEquals(1, childs.getLength());
    final String base64 = childs.item(0).getNodeValue();
    final byte[] bytes = Base64Support.decode(base64);
    Assertions.assertTrue(new String(bytes).startsWith("<eidas:"));

    // Unmarshall element
    final CurrentAddressType address2 =
        (CurrentAddressType) XMLObjectSupport.getUnmarshaller(CurrentAddressType.TYPE_NAME).unmarshall(element);

    verify(address, address2);

    final String swedishEidString = address2.toSwedishEidString();
    Assertions.assertEquals(
        "LocatorDesignator=6%20tr;LocatorName=10;Thoroughfare=Korta%20gatan;PostName=Solna;AdminunitFirstline=SE;AdminunitSecondline=Uppland;PostCode=19174",
        swedishEidString);

    // Test unmarshall again
    final String xml = SerializeSupport.prettyPrintXML(element);
    final Document doc = XMLObjectProviderRegistrySupport.getParserPool()
        .parse(new ByteArrayInputStream(xml.toString().getBytes("UTF-8")));

    final CurrentAddressType address3 =
        (CurrentAddressType) XMLObjectSupport.getUnmarshaller(CurrentAddressType.TYPE_NAME)
            .unmarshall(doc.getDocumentElement());
    verify(address, address3);

  }

  /**
   * Test unmarshalling an attribute holding a CurrentAddress type. Example is from eIDAS specs.
   * <p>
   * The example contains the Base64-encoding of the following XML-snippet:
   *
   * <pre>
   *  <eidas:LocatorDesignator>22</eidas:LocatorDesignator>
   *  <eidas:Thoroughfare>Arcacia Avenue</eidas:Thoroughfare>
   *  <eidas:PostName>London</eidas:PostName>
  *   <eidas:PostCode>SW1A 1AA</eidas:Postcode>
   * </pre>
   * </p>
   *
   * @throws Exception for errors.
   */
  @Test
  public void testUnmarshallExampleAttribute() throws Exception {

    final String xml =
        "<saml:Attribute FriendlyName=\"CurrentAddress\" Name=\"http://eidas.europa.eu/attributes/naturalperson/CurrentAddress\" NameFormat=\"urn:oasis:names:tc:SAML:2.0:attrname-format:uri\" xmlns:saml=\"urn:oasis:names:tc:SAML:2.0:assertion\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
            + "<saml:AttributeValue xmlns:eidas=\"http://eidas.europa.eu/attributes/naturalperson\" xsi:type=\"eidas:CurrentAddressType\">"
            + "PGVpZGFzOkxvY2F0b3JEZXNpZ25hdG9yPjIyPC9laWRhczpMb2NhdG9yRGVzaWduYX\n"
            + "Rvcj48ZWlkYXM6VGhvcm91Z2hmYXJlPkFyY2FjaWEgQXZlbnVlPC9laWRhczpUaG9y\n"
            + "b3VnaGZhcmU+DQo8ZWlkYXM6UG9zdE5hbWU+TG9uZG9uPC9laWRhczpQb3N0TmFtZT\n"
            + "4NCjxlaWRhczpQb3N0Q29kZT5TVzFBIDFBQTwvZWlkYXM6UG9zdENvZGU+"
            + "</saml:AttributeValue>"
            + "</saml:Attribute>";

    final Document doc =
        XMLObjectProviderRegistrySupport.getParserPool().parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
    final Element elm = doc.getDocumentElement();

    final Attribute attribute =
        (Attribute) XMLObjectSupport.getUnmarshaller(Attribute.DEFAULT_ELEMENT_NAME).unmarshall(elm);
    Assertions.assertNotNull(attribute);
    Assertions.assertEquals(AttributeConstants.EIDAS_CURRENT_ADDRESS_ATTRIBUTE_NAME, attribute.getName());
    Assertions.assertEquals(AttributeConstants.EIDAS_CURRENT_ADDRESS_ATTRIBUTE_FRIENDLY_NAME,
        attribute.getFriendlyName());

    final List<XMLObject> values = attribute.getAttributeValues();
    Assertions.assertTrue(values.size() == 1);
    Assertions.assertTrue(values.get(0) instanceof CurrentAddressType);
    final CurrentAddressType address = (CurrentAddressType) values.get(0);
    Assertions.assertEquals("22", address.getLocatorDesignator());
    Assertions.assertEquals("Arcacia Avenue", address.getThoroughfare());
    Assertions.assertEquals("London", address.getPostName());
    Assertions.assertEquals("SW1A 1AA", address.getPostCode());
  }

  @Test
  public void testUnmarshallNamespaceNotDefined() throws Exception {
    final String xml =
        "<saml:Attribute xmlns:saml=\"urn:oasis:names:tc:SAML:2.0:assertion\" xmlns:a=\"http://schemas.xmlsoap.org/ws/2009/09/identity/claims\" FriendlyName=\"CurrentAddress\" Name=\"http://eidas.europa.eu/attributes/naturalperson/CurrentAddress\" NameFormat=\"urn:oasis:names:tc:SAML:2.0:attrname-format:uri\" a:OriginalIssuer=\"urn:microsoft:cgg2010:fpsts\">"
            + "<saml:AttributeValue xmlns:b=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:tn=\"http://eidas.europa.eu/attributes/naturalperson\" b:type=\"tn:CurrentAddressType\">PGVpZGFzOkxvY2F0b3JEZXNpZ25hdG9yPjEzMTwvZWlkYXM6TG9jYXRvckRlc2lnbmF0b3I+DQo8ZWlkYXM6VGhvcm91Z2hmYXJlPjwvZWlkYXM6VGhvcm91Z2hmYXJlPg0KPGVpZGFzOlBvc3ROYW1lPkFybm9sdGljZSB1IETEm8SNw61uYTwvZWlkYXM6UG9zdE5hbWU+DQo8ZWlkYXM6UG9zdENvZGU+NDA3MTQ8L2VpZGFzOlBvc3RDb2RlPg0KPGVpZGFzOkN2YWRkcmVzc0FyZWE+QXJub2x0aWNlPC9laWRhczpDdmFkZHJlc3NBcmVhPg0K</saml:AttributeValue>"
            + "</saml:Attribute>";

    // <eidas:LocatorDesignator>131</eidas:LocatorDesignator>
    // <eidas:Thoroughfare></eidas:Thoroughfare>
    // <eidas:PostName>Arnoltice u Děčína</eidas:PostName>
    // <eidas:PostCode>40714</eidas:PostCode>
    // <eidas:CvaddressArea>Arnoltice</eidas:CvaddressArea>

    final Document doc =
        XMLObjectProviderRegistrySupport.getParserPool().parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
    final Element elm = doc.getDocumentElement();

    final Attribute attribute =
        (Attribute) XMLObjectSupport.getUnmarshaller(Attribute.DEFAULT_ELEMENT_NAME).unmarshall(elm);
    Assertions.assertNotNull(attribute);
    Assertions.assertEquals(AttributeConstants.EIDAS_CURRENT_ADDRESS_ATTRIBUTE_NAME, attribute.getName());
    Assertions.assertEquals(AttributeConstants.EIDAS_CURRENT_ADDRESS_ATTRIBUTE_FRIENDLY_NAME,
        attribute.getFriendlyName());

    final List<XMLObject> values = attribute.getAttributeValues();
    Assertions.assertTrue(values.size() == 1);
    Assertions.assertTrue(values.get(0) instanceof CurrentAddressType);
    final CurrentAddressType address = (CurrentAddressType) values.get(0);
    Assertions.assertEquals("131", address.getLocatorDesignator());
    Assertions.assertNull(address.getThoroughfare());
    Assertions.assertEquals("Arnoltice u Děčína", address.getPostName());
    Assertions.assertEquals("40714", address.getPostCode());
    Assertions.assertEquals("Arnoltice", address.getCvaddressArea());
  }

  /**
   * Test that creates an attribute and places a CurrentAddessType as a value.
   *
   * @throws Exception for errors
   */
  @Test
  public void testAttributeCreate() throws Exception {

    final Attribute attribute = (Attribute) XMLObjectSupport.buildXMLObject(Attribute.DEFAULT_ELEMENT_NAME);
    attribute.getNamespaceManager().registerNamespaceDeclaration(new Namespace(EidasConstants.EIDAS_NP_NS, "eidas"));
    attribute.setName(AttributeConstants.EIDAS_CURRENT_ADDRESS_ATTRIBUTE_NAME);
    attribute.setFriendlyName(AttributeConstants.EIDAS_CURRENT_ADDRESS_ATTRIBUTE_FRIENDLY_NAME);
    attribute.setNameFormat(Attribute.URI_REFERENCE);

    @SuppressWarnings("unchecked")
    final XMLObjectBuilder<CurrentAddressType> builder =
        (XMLObjectBuilder<CurrentAddressType>) XMLObjectSupport.getBuilder(CurrentAddressType.TYPE_NAME);
    final CurrentAddressType address = builder.buildObject(AttributeValue.DEFAULT_ELEMENT_NAME,
        new QName(EidasConstants.EIDAS_NP_NS, CurrentAddressType.TYPE_NAME.getLocalPart(), "eidas"));
    fill(address);

    attribute.getAttributeValues().add(address);

    final Element attrElement = XMLObjectSupport.marshall(attribute);

    // System.out.println(SerializeSupport.prettyPrintXML(attrElement));

    // Make sure we inserted the correct namespace prefix while marshalling the CurrentAddressType
    Assertions.assertTrue(new String(Base64Support.decode(attrElement.getFirstChild().getFirstChild().getNodeValue()))
        .startsWith("<eidas:"));

    // Unmarshall
    final Attribute attribute2 =
        (Attribute) XMLObjectSupport.getUnmarshaller(Attribute.DEFAULT_ELEMENT_NAME).unmarshall(attrElement);

    Assertions.assertNotNull(attribute2);
    Assertions.assertEquals(AttributeConstants.EIDAS_CURRENT_ADDRESS_ATTRIBUTE_NAME, attribute2.getName());
    Assertions.assertEquals(AttributeConstants.EIDAS_CURRENT_ADDRESS_ATTRIBUTE_FRIENDLY_NAME,
        attribute2.getFriendlyName());

    final List<XMLObject> values = attribute.getAttributeValues();
    Assertions.assertTrue(values.size() == 1);
    Assertions.assertTrue(values.get(0) instanceof CurrentAddressType);
    final CurrentAddressType address2 = (CurrentAddressType) values.get(0);
    verify(address, address2);
  }

  private static void fill(final CurrentAddressStructuredType address) {
    address.setLocatorDesignator("6 tr");
    address.setLocatorName("10");
    address.setThoroughfare("Korta gatan");
    address.setPostName("Solna");
    address.setPostCode("19174");
    address.setAdminunitFirstline("SE");
    address.setAdminunitSecondline("Uppland");
  }

  private static void verify(final CurrentAddressStructuredType expected, final CurrentAddressStructuredType actual) {
    Assertions.assertEquals(expected.getElementQName(), actual.getElementQName());
    Assertions.assertEquals(expected.getLocatorDesignator(), actual.getLocatorDesignator());
    Assertions.assertEquals(expected.getLocatorName(), actual.getLocatorName());
    Assertions.assertEquals(expected.getThoroughfare(), actual.getThoroughfare());
    Assertions.assertEquals(expected.getPostName(), actual.getPostName());
    Assertions.assertEquals(expected.getPostCode(), actual.getPostCode());

    Assertions.assertEquals(expected.getCvaddressArea(), actual.getCvaddressArea());
  }

}
