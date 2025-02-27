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
package se.swedenconnect.opensaml.eidas.ext.attributes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opensaml.core.xml.util.XMLObjectSupport;
import org.w3c.dom.Element;

import se.swedenconnect.opensaml.eidas.OpenSAMLTestBase;

/**
 * Test cases for {@link PlaceOfBirthType}.
 *
 * @author Martin Lindström
 */
public class PlaceOfBirthTypeTest extends OpenSAMLTestBase {

  /**
   * Test to marhall and unmarshall the object.
   *
   * @throws Exception for errors
   */
  @Test
  public void testMarshallUnmarshall() throws Exception {

    final PlaceOfBirthType pbt = (PlaceOfBirthType) XMLObjectSupport.buildXMLObject(PlaceOfBirthType.TYPE_NAME);
    pbt.setValue("Stockholm");

    Assertions.assertEquals("Stockholm", pbt.getValue());

    final Element element = XMLObjectSupport.marshall(pbt);
    Assertions.assertNotNull(element);

    final PlaceOfBirthType pbt2 =
        (PlaceOfBirthType) XMLObjectSupport.getUnmarshaller(PlaceOfBirthType.TYPE_NAME).unmarshall(element);
    Assertions.assertEquals(pbt.getValue(), pbt2.getValue());
    Assertions.assertEquals(pbt.getElementQName(), pbt2.getElementQName());
  }

}
