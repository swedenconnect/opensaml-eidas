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

import se.swedenconnect.opensaml.eidas.OpenSAMLTestBase;

/**
 * Test cases for {@link PersonIdentifierType}.
 *
 * @author Martin Lindström
 */
public class PersonIdentifierTypeTest extends OpenSAMLTestBase {

  @Test
  void testParts() throws Exception {
    final PersonIdentifierType p = AttributeUtils.createAttributeValueObject(PersonIdentifierType.class);
    p.setValue("ES/SE/12345678A");

    Assertions.assertEquals("ES", p.getNationalityCode());
    Assertions.assertEquals("SE", p.getDestinationNationalityCode());
    Assertions.assertEquals("12345678A", p.getIdentifierString());
  }

  @Test
  void testIdentifierWithSlash() throws Exception {
    final PersonIdentifierType p = AttributeUtils.createAttributeValueObject(PersonIdentifierType.class);
    p.setValue("ES/SE/123/456");

    Assertions.assertEquals("ES", p.getNationalityCode());
    Assertions.assertEquals("SE", p.getDestinationNationalityCode());
    Assertions.assertEquals("123/456", p.getIdentifierString());
  }

  @Test
  void testMissingParts() throws Exception {
    final PersonIdentifierType p = AttributeUtils.createAttributeValueObject(PersonIdentifierType.class);
    p.setValue("ES");

    Assertions.assertEquals("ES", p.getNationalityCode());
    Assertions.assertNull(p.getDestinationNationalityCode());
    Assertions.assertNull(p.getIdentifierString());
  }

}
