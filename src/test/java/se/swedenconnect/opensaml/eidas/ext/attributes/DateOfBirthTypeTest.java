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
 * Test cases for {@link DateOfBirthType}.
 *
 * @author Martin Lindström (martin.lindstrom@litsec.se)
 */
public class DateOfBirthTypeTest extends OpenSAMLTestBase {

  /**
   * Test to marhall and unmarshall the object.
   *
   * @throws Exception for errors
   */
  @Test
  public void testMarshallUnmarshall() throws Exception {

    DateOfBirthType date = (DateOfBirthType) XMLObjectSupport.buildXMLObject(DateOfBirthType.TYPE_NAME);
    date.setDate(1969, 11, 29);

    Element xml = XMLObjectSupport.marshall(date);
    Assertions.assertEquals("1969-11-29", xml.getTextContent());

    // System.out.println(SerializeSupport.prettyPrintXML(xml));

    DateOfBirthType date2 =
        (DateOfBirthType) XMLObjectSupport.getUnmarshaller(DateOfBirthType.TYPE_NAME).unmarshall(xml);
    Assertions.assertEquals(date.getDate(), date2.getDate());
    Assertions.assertEquals("1969-11-29", date2.toStringValue());

    // Create Java Time
    date = (DateOfBirthType) XMLObjectSupport.buildXMLObject(DateOfBirthType.TYPE_NAME);
    date.setDate(java.time.LocalDate.of(1969, 11, 29));

    xml = XMLObjectSupport.marshall(date);
    Assertions.assertEquals("1969-11-29", xml.getTextContent());

    date2 = (DateOfBirthType) XMLObjectSupport.getUnmarshaller(DateOfBirthType.TYPE_NAME).unmarshall(xml);
    Assertions.assertEquals(date.getDate(), date2.getDate());

    final DateOfBirthType date3 = (DateOfBirthType) XMLObjectSupport.buildXMLObject(DateOfBirthType.TYPE_NAME);
    date3.parseStringValue("1969-11-29");

    xml = XMLObjectSupport.marshall(date);
    Assertions.assertEquals("1969-11-29", xml.getTextContent());

    final DateOfBirthType date4 =
        (DateOfBirthType) XMLObjectSupport.getUnmarshaller(DateOfBirthType.TYPE_NAME).unmarshall(xml);
    Assertions.assertEquals(date3.getDate(), date4.getDate());
    Assertions.assertEquals("1969-11-29", date4.toStringValue());
  }

  @Test
  public void testParseError() throws Exception {
    final DateOfBirthType date = (DateOfBirthType) XMLObjectSupport.buildXMLObject(DateOfBirthType.TYPE_NAME);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      date.parseStringValue("NOT_A_DATE");
    });
  }

  @Test
  public void testEmpty() throws Exception {
    final DateOfBirthType date = (DateOfBirthType) XMLObjectSupport.buildXMLObject(DateOfBirthType.TYPE_NAME);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      date.parseStringValue("");
    });
  }

  @Test
  public void testParseBadYear() throws Exception {
    final DateOfBirthType date = (DateOfBirthType) XMLObjectSupport.buildXMLObject(DateOfBirthType.TYPE_NAME);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      date.parseStringValue("69-11-29"); // Not a full year
    });
  }

}
