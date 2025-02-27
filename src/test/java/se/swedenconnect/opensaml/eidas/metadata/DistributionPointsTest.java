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
 * Test cases for {@code DistributionPoints}.
 *
 * @author Martin Lindström
 */
public class DistributionPointsTest extends OpenSAMLTestBase {

  /**
   * Test marshalling and unmarshalling.
   *
   * @throws Exception
   */
  @Test
  public void testMarshallAndUnmarshall() throws Exception {

    final DistributionPoints points =
        (DistributionPoints) XMLObjectSupport.buildXMLObject(DistributionPoints.DEFAULT_ELEMENT_NAME);

    final DistributionPoint dp1 =
        (DistributionPoint) XMLObjectSupport.buildXMLObject(DistributionPoint.DEFAULT_ELEMENT_NAME);
    dp1.setURI("https://www.example.com/1");

    final DistributionPoint dp2 =
        (DistributionPoint) XMLObjectSupport.buildXMLObject(DistributionPoint.DEFAULT_ELEMENT_NAME);
    dp2.setURI("https://www.example.com/2");

    points.getDistributionPoints().add(dp1);
    points.getDistributionPoints().add(dp2);

    final Element element = XMLObjectSupport.marshall(points);

    final DistributionPoints points2 = (DistributionPoints) XMLObjectSupport
        .getUnmarshaller(DistributionPoints.DEFAULT_ELEMENT_NAME).unmarshall(element);
    Assertions.assertEquals(points.getDistributionPoints().size(), points2.getDistributionPoints().size());
    Assertions.assertEquals(points.getDistributionPoints().get(0).getURI(),
        points2.getDistributionPoints().get(0).getURI());
    Assertions.assertEquals(points.getDistributionPoints().get(1).getURI(),
        points2.getDistributionPoints().get(1).getURI());
  }

}
