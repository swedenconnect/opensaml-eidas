/*
 * Copyright 2016-2026 Sweden Connect
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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opensaml.core.xml.schema.XSString;
import org.opensaml.saml.saml2.core.Attribute;
import se.swedenconnect.opensaml.eidas.OpenSAMLTestBase;

/**
 * Test cases for eJusticeNaturalPersonRole.
 *
 * @author Martin Lindström
 */
public class EJusticeNaturalPersonRoleTest extends OpenSAMLTestBase {

  @Test
  void testCreate() throws Exception {

    final Attribute attribute = AttributeUtils.createAttribute(
        AttributeConstants.EJUSTICE_NATURAL_PERSON_ROLE_ATTRIBUTE_NAME,
        AttributeConstants.EJUSTICE_LEGAL_PERSON_ROLE_ATTRIBUTE_FRIENDLY_NAME,
        Attribute.URI_REFERENCE);

    final XSString value = AttributeUtils.createAttributeValueObject(XSString.TYPE_NAME, XSString.class);
    value.setValue("VIP1");
    attribute.getAttributeValues().add(value);

    /*
    final Element element = XMLObjectSupport.marshall(attribute);
    final String xml = SerializeSupport.prettyPrintXML(element);
    System.out.println(xml);
     */

    Assertions.assertEquals(AttributeConstants.EJUSTICE_NATURAL_PERSON_ROLE_ATTRIBUTE_NAME, attribute.getName());
    Assertions.assertEquals(AttributeConstants.EJUSTICE_LEGAL_PERSON_ROLE_ATTRIBUTE_FRIENDLY_NAME,
        attribute.getFriendlyName());
    Assertions.assertTrue(attribute.getAttributeValues().getFirst() instanceof XSString);
    Assertions.assertEquals("VIP1", ((XSString) attribute.getAttributeValues().getFirst()).getValue());
  }
}
