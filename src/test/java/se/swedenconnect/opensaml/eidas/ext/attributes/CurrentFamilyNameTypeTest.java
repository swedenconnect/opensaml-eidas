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
import java.io.InputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opensaml.core.xml.XMLObjectBuilder;
import org.opensaml.core.xml.config.XMLObjectProviderRegistrySupport;
import org.opensaml.core.xml.util.XMLObjectSupport;
import org.opensaml.saml.saml2.core.Attribute;
import org.opensaml.saml.saml2.core.AttributeValue;
import org.w3c.dom.Element;

import net.shibboleth.shared.xml.SerializeSupport;
import se.swedenconnect.opensaml.eidas.OpenSAMLTestBase;

/**
 * Test cases for {@link CurrentFamilyNameType}.
 *
 * @author Martin Lindström
 */
public class CurrentFamilyNameTypeTest extends OpenSAMLTestBase {

  /**
   * Test to marhall and unmarshall the object.
   *
   * @throws Exception for errors
   */
  @Test
  public void testMarshallUnmarshall() throws Exception {

    final CurrentFamilyNameType cfn =
        (CurrentFamilyNameType) XMLObjectSupport.buildXMLObject(CurrentFamilyNameType.TYPE_NAME);
    cfn.setValue("Karlsson");

    Assertions.assertEquals("Karlsson", cfn.getValue());
    Assertions.assertTrue(cfn.getLatinScript());
    Assertions.assertNull(cfn.getLatinScriptXSBooleanValue());

    final Element element = XMLObjectSupport.marshall(cfn);
    Assertions.assertNotNull(element);

    final CurrentFamilyNameType cfn2 =
        (CurrentFamilyNameType) XMLObjectSupport.getUnmarshaller(CurrentFamilyNameType.TYPE_NAME).unmarshall(element);
    Assertions.assertEquals(cfn.getValue(), cfn2.getValue());
    Assertions.assertEquals(cfn.getLatinScript(), cfn2.getLatinScript());
    Assertions.assertEquals(cfn.getElementQName(), cfn2.getElementQName());
  }

  /**
   * Verifies that we can unmarshall the example given in section 2.4 of
   * <a href="https://joinup.ec.europa.eu/sites/default/files/eidas_saml_attribute_profile_v1.0_2.pdf">eIDAS SAML
   * Attribute Profile</a>.
   *
   * @throws Exception for errors
   */
  @Test
  public void testUnmarshallExample() throws Exception {
    final InputStream is = this.getClass().getResourceAsStream("/example-transliteration.xml");
    final Attribute attribute =
        (Attribute) XMLObjectSupport.unmarshallFromInputStream(XMLObjectProviderRegistrySupport.getParserPool(), is);
    Assertions.assertNotNull(attribute);

    Assertions.assertEquals(AttributeConstants.EIDAS_CURRENT_FAMILY_NAME_ATTRIBUTE_NAME, attribute.getName());
    Assertions.assertEquals(AttributeConstants.EIDAS_CURRENT_FAMILY_NAME_ATTRIBUTE_FRIENDLY_NAME,
        attribute.getFriendlyName());
    Assertions.assertEquals(Attribute.URI_REFERENCE, attribute.getNameFormat());

    Assertions.assertTrue(attribute.getAttributeValues().size() == 2);
    Assertions.assertTrue(attribute.getAttributeValues().get(0) instanceof CurrentFamilyNameType);
    Assertions.assertTrue(attribute.getAttributeValues().get(1) instanceof CurrentFamilyNameType);

    final CurrentFamilyNameType v1 = (CurrentFamilyNameType) attribute.getAttributeValues().get(0);
    Assertions.assertTrue(v1.getLatinScript());
    Assertions.assertNull(v1.getLatinScriptXSBooleanValue());
    Assertions.assertEquals("Onasis", v1.getValue());

    final CurrentFamilyNameType v2 = (CurrentFamilyNameType) attribute.getAttributeValues().get(1);
    Assertions.assertFalse(v2.getLatinScript());
    Assertions.assertNotNull(v2.getLatinScriptXSBooleanValue());
    Assertions.assertEquals("Ωνασης", v2.getValue());
  }

  /**
   * Test creating and marshalling/unmarshalling an attribute with a name represented in two ways.
   *
   * @throws Exception for errors
   */
  @Test
  public void testTransliteratedAttribute() throws Exception {
    final Attribute attribute = (Attribute) XMLObjectSupport.buildXMLObject(Attribute.DEFAULT_ELEMENT_NAME);
    attribute.setName(AttributeConstants.EIDAS_CURRENT_FAMILY_NAME_ATTRIBUTE_NAME);
    attribute.setNameFormat(Attribute.URI_REFERENCE);

    @SuppressWarnings("unchecked")
    final XMLObjectBuilder<CurrentFamilyNameType> builder =
        (XMLObjectBuilder<CurrentFamilyNameType>) XMLObjectSupport.getBuilder(CurrentFamilyNameType.TYPE_NAME);

    final CurrentFamilyNameType name1 =
        builder.buildObject(AttributeValue.DEFAULT_ELEMENT_NAME, CurrentFamilyNameType.TYPE_NAME);
    name1.setValue("Onasis");

    final CurrentFamilyNameType name2 =
        builder.buildObject(AttributeValue.DEFAULT_ELEMENT_NAME, CurrentFamilyNameType.TYPE_NAME);
    name2.setValue("Ωνασης");
    name2.setLatinScript(false);

    attribute.getAttributeValues().add(name1);
    attribute.getAttributeValues().add(name2);

    final Element xml = XMLObjectSupport.marshall(attribute);

    final Attribute attribute2 =
        (Attribute) XMLObjectSupport.getUnmarshaller(Attribute.DEFAULT_ELEMENT_NAME).unmarshall(xml);
    Assertions.assertEquals(((CurrentFamilyNameType) attribute.getAttributeValues().get(0)).getValue(),
        ((CurrentFamilyNameType) attribute2
            .getAttributeValues().get(0)).getValue());
    Assertions.assertEquals(((CurrentFamilyNameType) attribute.getAttributeValues().get(1)).getValue(),
        ((CurrentFamilyNameType) attribute2
            .getAttributeValues().get(1)).getValue());

    // Unmarshall again, but this time from the XML string ...
    final String xmlString = SerializeSupport.prettyPrintXML(xml);

    final Attribute attribute3 =
        (Attribute) XMLObjectSupport.unmarshallFromInputStream(XMLObjectProviderRegistrySupport.getParserPool(),
            new ByteArrayInputStream(xmlString.getBytes("UTF-8")));
    Assertions.assertEquals(((CurrentFamilyNameType) attribute.getAttributeValues().get(0)).getValue(),
        ((CurrentFamilyNameType) attribute3
            .getAttributeValues().get(0)).getValue());
    Assertions.assertEquals(((CurrentFamilyNameType) attribute.getAttributeValues().get(1)).getValue(),
        ((CurrentFamilyNameType) attribute3
            .getAttributeValues().get(1)).getValue());
  }

}
