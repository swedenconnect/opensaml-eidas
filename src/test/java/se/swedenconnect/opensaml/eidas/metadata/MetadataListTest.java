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
package se.swedenconnect.opensaml.eidas.metadata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opensaml.core.xml.util.XMLObjectSupport;
import org.w3c.dom.Element;

import se.swedenconnect.opensaml.eidas.OpenSAMLTestBase;

/**
 * Test cases for {@code MetadataList}.
 *
 * @author Martin Lindström
 */
public class MetadataListTest extends OpenSAMLTestBase {

  /**
   * Test marshalling and unmarshalling.
   *
   * @throws Exception
   */
  @Test
  public void testMarshallAndUnmarshall() throws Exception {

    final MetadataList mdl = (MetadataList) XMLObjectSupport.buildXMLObject(MetadataList.DEFAULT_ELEMENT_NAME);
    mdl.setTerritory("SE");
    mdl.getMetadataLocations().add(MetadataLocationTest.createMetadataLocation());

    final Element element = XMLObjectSupport.marshall(mdl);

    final MetadataList mdl2 =
        (MetadataList) XMLObjectSupport.getUnmarshaller(MetadataList.DEFAULT_ELEMENT_NAME).unmarshall(element);

    Assertions.assertEquals(mdl.getTerritory(), mdl2.getTerritory());
    Assertions.assertEquals(mdl.getMetadataLocations().size(), mdl2.getMetadataLocations().size());
    Assertions.assertFalse(mdl.getSuspend());
    Assertions.assertFalse(mdl2.getSuspend());
  }

  /**
   * Tests the suspend-attribute
   *
   * @throws Exception
   */
  @Test
  public void testSuspendAnyAttribute() throws Exception {

    MetadataList mdl = (MetadataList) XMLObjectSupport.buildXMLObject(MetadataList.DEFAULT_ELEMENT_NAME);
    mdl.setTerritory("SE");
    mdl.setSuspend(true);
    mdl.getMetadataLocations().add(MetadataLocationTest.createMetadataLocation());

    Element element = XMLObjectSupport.marshall(mdl);

    MetadataList mdl2 =
        (MetadataList) XMLObjectSupport.getUnmarshaller(MetadataList.DEFAULT_ELEMENT_NAME).unmarshall(element);

    Assertions.assertEquals(mdl.getTerritory(), mdl2.getTerritory());
    Assertions.assertEquals(mdl.getMetadataLocations().size(), mdl2.getMetadataLocations().size());
    Assertions.assertTrue(mdl.getSuspend());
    Assertions.assertTrue(mdl2.getSuspend());

    mdl = (MetadataList) XMLObjectSupport.buildXMLObject(MetadataList.DEFAULT_ELEMENT_NAME);
    mdl.setTerritory("SE");
    mdl.setSuspend(false);
    mdl.getMetadataLocations().add(MetadataLocationTest.createMetadataLocation());

    element = XMLObjectSupport.marshall(mdl);

    mdl2 = (MetadataList) XMLObjectSupport.getUnmarshaller(MetadataList.DEFAULT_ELEMENT_NAME).unmarshall(element);

    Assertions.assertEquals(mdl.getTerritory(), mdl2.getTerritory());
    Assertions.assertEquals(mdl.getMetadataLocations().size(), mdl2.getMetadataLocations().size());
    Assertions.assertFalse(mdl.getSuspend());
    Assertions.assertFalse(mdl2.getSuspend());
  }

}
