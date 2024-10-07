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
package se.swedenconnect.opensaml.eidas.ext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opensaml.core.xml.util.XMLObjectSupport;
import org.w3c.dom.Element;

import se.swedenconnect.opensaml.eidas.OpenSAMLTestBase;

/**
 * Test cases for building, marshalling and unmarshalling of {@code NodeCountry} elements.
 *
 * @author Martin Lindström
 */
public class NodeCountryTest extends OpenSAMLTestBase {

  /**
   * Test building a {@code NodeCountry} element.
   *
   * @throws Exception for errors
   */
  @Test
  public void test() throws Exception {
    final NodeCountry nc = (NodeCountry) XMLObjectSupport.buildXMLObject(NodeCountry.DEFAULT_ELEMENT_NAME);
    Assertions.assertNotNull(nc);
    nc.setNodeCountry("SE");

    final Element element = XMLObjectSupport.marshall(nc);
    Assertions.assertEquals("SE", element.getTextContent());

    final NodeCountry nc2 = (NodeCountry) XMLObjectSupport.getUnmarshaller(NodeCountry.DEFAULT_ELEMENT_NAME).unmarshall(element);
    Assertions.assertEquals("SE", nc2.getNodeCountry());
  }

  /**
   * Tests that we convert to uppercase national code.
   *
   * @throws Exception for errors
   */
  @Test
  public void testConvert() throws Exception {
    final NodeCountry nc = (NodeCountry) XMLObjectSupport.buildXMLObject(NodeCountry.DEFAULT_ELEMENT_NAME);
    Assertions.assertNotNull(nc);
    nc.setNodeCountry("se");

    Assertions.assertEquals("SE", nc.getNodeCountry());
  }

  /**
   * Tests that only valid ISO 3166-1 codes are accepted.
   *
   * @throws Exception for errors
   */
  @Test
  public void testBadInput() throws Exception {

    final NodeCountry nc = (NodeCountry) XMLObjectSupport.buildXMLObject(NodeCountry.DEFAULT_ELEMENT_NAME);

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      nc.setNodeCountry(null);
    });

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      nc.setNodeCountry("s");
    });

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      nc.setNodeCountry("sve");
    });
  }

}
