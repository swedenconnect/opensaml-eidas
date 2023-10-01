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
package se.swedenconnect.opensaml.eidas.ext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opensaml.core.xml.util.XMLObjectSupport;
import org.opensaml.saml.saml2.core.Attribute;
import org.w3c.dom.Element;

import se.swedenconnect.opensaml.eidas.OpenSAMLTestBase;
import se.swedenconnect.opensaml.eidas.common.EidasConstants;
import se.swedenconnect.opensaml.eidas.ext.attributes.AttributeConstants;

/**
 * Test cases for {@link RequestedAttribute}.
 *
 * @author Martin Lindström
 */
public class RequestedAttributeTest extends OpenSAMLTestBase {

  /**
   * Test marshalling and unmarshalling.
   *
   * @throws Exception
   */
  @Test
  public void testMarshallAndUnmarshall() throws Exception {

    final RequestedAttribute ra =
        (RequestedAttribute) XMLObjectSupport.buildXMLObject(RequestedAttribute.DEFAULT_ELEMENT_NAME);

    ra.setName(AttributeConstants.EIDAS_CURRENT_FAMILY_NAME_ATTRIBUTE_NAME);
    ra.setFriendlyName(AttributeConstants.EIDAS_CURRENT_FAMILY_NAME_ATTRIBUTE_FRIENDLY_NAME);
    ra.setIsRequired(Boolean.TRUE);
    ra.setNameFormat(Attribute.URI_REFERENCE);
    Assertions.assertTrue(ra.getAttributeValues().isEmpty());
    Assertions.assertEquals(EidasConstants.EIDAS_NS, ra.getElementQName().getNamespaceURI());
    Assertions.assertEquals(RequestedAttribute.DEFAULT_ELEMENT_LOCAL_NAME, ra.getElementQName().getLocalPart());
    Assertions.assertEquals(EidasConstants.EIDAS_PREFIX, ra.getElementQName().getPrefix());

    final Element element = XMLObjectSupport.marshall(ra);
    Assertions.assertEquals(AttributeConstants.EIDAS_CURRENT_FAMILY_NAME_ATTRIBUTE_NAME,
        element.getAttribute(Attribute.NAME_ATTTRIB_NAME));
    Assertions.assertEquals(AttributeConstants.EIDAS_CURRENT_FAMILY_NAME_ATTRIBUTE_FRIENDLY_NAME,
        element.getAttribute(Attribute.FRIENDLY_NAME_ATTRIB_NAME));
    Assertions.assertEquals(Boolean.TRUE.toString(),
        element.getAttribute(org.opensaml.saml.saml2.metadata.RequestedAttribute.IS_REQUIRED_ATTRIB_NAME));
    Assertions.assertEquals(Attribute.URI_REFERENCE, element.getAttribute(Attribute.NAME_FORMAT_ATTRIB_NAME));

    final RequestedAttribute ra2 = (RequestedAttribute) XMLObjectSupport
        .getUnmarshaller(RequestedAttribute.DEFAULT_ELEMENT_NAME).unmarshall(element);
    Assertions.assertEquals(ra.getName(), ra2.getName());
    Assertions.assertEquals(ra.getFriendlyName(), ra2.getFriendlyName());
    Assertions.assertEquals(ra.getNameFormat(), ra2.getNameFormat());
    Assertions.assertEquals(ra.isRequiredXSBoolean(), ra2.isRequiredXSBoolean());
    Assertions.assertEquals(ra.getElementQName(), ra2.getElementQName());
    Assertions.assertTrue(ra2.getAttributeValues().isEmpty());
  }

}
