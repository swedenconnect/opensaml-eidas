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
package se.swedenconnect.opensaml.eidas.metadata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opensaml.core.xml.util.XMLObjectSupport;
import org.w3c.dom.Element;

import se.swedenconnect.opensaml.eidas.OpenSAMLTestBase;

/**
 * Test cases for {@code SchemeInformation}.
 *
 * @author Martin Lindström
 */
public class SchemeInformationTest extends OpenSAMLTestBase {

  /**
   * Test marshalling and unmarshalling.
   *
   * @throws Exception
   */
  @Test
  public void testMarshallAndUnmarshall() throws Exception {

    final SchemeInformation info =
        (SchemeInformation) XMLObjectSupport.buildXMLObject(SchemeInformation.DEFAULT_ELEMENT_NAME);
    info.setIssuerName("E-identification board");
    info.setSchemeIdentifier("123456");
    info.setSchemeTerritory("SE");

    final Element element = XMLObjectSupport.marshall(info);

    final SchemeInformation info2 = (SchemeInformation) XMLObjectSupport
        .getUnmarshaller(SchemeInformation.DEFAULT_ELEMENT_NAME).unmarshall(element);
    Assertions.assertEquals(info.getIssuerName(), info2.getIssuerName());
    Assertions.assertEquals(info.getSchemeIdentifier(), info2.getSchemeIdentifier());
    Assertions.assertEquals(info.getSchemeTerritory(), info2.getSchemeTerritory());
  }

  /**
   * Tests assigning null child elements
   *
   * @throws Exception
   */
  @Test
  public void testNull() throws Exception {
    final SchemeInformation info =
        (SchemeInformation) XMLObjectSupport.buildXMLObject(SchemeInformation.DEFAULT_ELEMENT_NAME);
    info.setIssuerName(null);
    info.setSchemeIdentifier("123456");
    info.setSchemeTerritory(null);

    final Element element = XMLObjectSupport.marshall(info);

    final SchemeInformation info2 = (SchemeInformation) XMLObjectSupport
        .getUnmarshaller(SchemeInformation.DEFAULT_ELEMENT_NAME).unmarshall(element);
    Assertions.assertNull(info2.getIssuerName());
    Assertions.assertEquals(info.getSchemeIdentifier(), info2.getSchemeIdentifier());
    Assertions.assertNull(info2.getSchemeTerritory());
  }

}
