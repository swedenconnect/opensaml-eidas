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
 * Test cases for {@link BirthNameType}.
 *
 * @author Martin Lindström
 */
public class BirthNameTypeTest extends OpenSAMLTestBase {

  /**
   * Test to marhall and unmarshall the object.
   *
   * @throws Exception for errors
   */
  @Test
  public void testMarshallUnmarshall() throws Exception {

    final BirthNameType bnt = (BirthNameType) XMLObjectSupport.buildXMLObject(BirthNameType.TYPE_NAME);
    bnt.setValue("Elsie Adela Lindström");

    Assertions.assertEquals("Elsie Adela Lindström", bnt.getValue());
    Assertions.assertTrue(bnt.getLatinScript());
    Assertions.assertNull(bnt.getLatinScriptXSBooleanValue());

    final Element element = XMLObjectSupport.marshall(bnt);
    Assertions.assertNotNull(element);

    final BirthNameType bnt2 =
        (BirthNameType) XMLObjectSupport.getUnmarshaller(BirthNameType.TYPE_NAME).unmarshall(element);
    Assertions.assertEquals(bnt.getValue(), bnt2.getValue());
    Assertions.assertEquals(bnt.getLatinScript(), bnt2.getLatinScript());
    Assertions.assertEquals(bnt.getElementQName(), bnt2.getElementQName());
  }

}
