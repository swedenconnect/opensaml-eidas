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
package se.swedenconnect.opensaml.eidas.ext.attributes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opensaml.core.xml.util.XMLObjectSupport;
import org.w3c.dom.Element;
import se.swedenconnect.opensaml.eidas.OpenSAMLTestBase;

/**
 * Test cases for {@link NationalityType}, {@link CountryOfBirthType} and {@link CountryOfResidenceType}.
 *
 * @author Martin Lindström
 */
public class CountryStringTypeTest extends OpenSAMLTestBase {

  @Test
  void testMarshallUnmarshallNationality() throws Exception {

    final NationalityType n = (NationalityType) XMLObjectSupport.buildXMLObject(NationalityType.TYPE_NAME);
    n.setValue("SE");

    Assertions.assertEquals("SE", n.getValue());

    final Element element = XMLObjectSupport.marshall(n);
    Assertions.assertNotNull(element);

    final NationalityType n2 =
        (NationalityType) XMLObjectSupport.getUnmarshaller(NationalityType.TYPE_NAME).unmarshall(element);
    Assertions.assertEquals(n.getValue(), n2.getValue());
    Assertions.assertEquals(n.getElementQName(), n2.getElementQName());
  }

  @Test
  void testInvalidCountry() throws Exception {
    final NationalityType n = (NationalityType) XMLObjectSupport.buildXMLObject(NationalityType.TYPE_NAME);
    Assertions.assertThrows(IllegalArgumentException.class, () -> n.setValue("SVE"));
  }

  @Test
  void testToUpperCase() throws Exception {

    final NationalityType n = (NationalityType) XMLObjectSupport.buildXMLObject(NationalityType.TYPE_NAME);
    n.setValue("se");

    Assertions.assertEquals("SE", n.getValue());
  }

  @Test
  void testMarshallUnmarshallCountryOfBirthType() throws Exception {

    final CountryOfBirthType n = (CountryOfBirthType) XMLObjectSupport.buildXMLObject(CountryOfBirthType.TYPE_NAME);
    n.setValue("SE");

    Assertions.assertEquals("SE", n.getValue());

    final Element element = XMLObjectSupport.marshall(n);
    Assertions.assertNotNull(element);

    final CountryOfBirthType n2 =
        (CountryOfBirthType) XMLObjectSupport.getUnmarshaller(CountryOfBirthType.TYPE_NAME).unmarshall(element);
    Assertions.assertEquals(n.getValue(), n2.getValue());
    Assertions.assertEquals(n.getElementQName(), n2.getElementQName());
  }

  @Test
  void testMarshallUnmarshallCountryOfResidenceType() throws Exception {

    final CountryOfResidenceType n =
        (CountryOfResidenceType) XMLObjectSupport.buildXMLObject(CountryOfResidenceType.TYPE_NAME);
    n.setValue("SE");

    Assertions.assertEquals("SE", n.getValue());

    final Element element = XMLObjectSupport.marshall(n);
    Assertions.assertNotNull(element);

    final CountryOfResidenceType n2 =
        (CountryOfResidenceType) XMLObjectSupport.getUnmarshaller(CountryOfResidenceType.TYPE_NAME).unmarshall(element);
    Assertions.assertEquals(n.getValue(), n2.getValue());
    Assertions.assertEquals(n.getElementQName(), n2.getElementQName());
  }

}
