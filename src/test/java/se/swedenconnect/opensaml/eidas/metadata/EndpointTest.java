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
 * Test cases for {@code Endpoint}.
 *
 * @author Martin Lindström
 */
public class EndpointTest extends OpenSAMLTestBase {

  /**
   * Test marshalling and unmarshalling.
   *
   * @throws Exception
   */
  @Test
  public void testMarshallAndUnmarshall() throws Exception {

    final Endpoint ep = (Endpoint) XMLObjectSupport.buildXMLObject(Endpoint.DEFAULT_ELEMENT_NAME);
    ep.setEndpointType(Endpoint.PROXY_SERVICE_ENDPOINT_TYPE);
    ep.setEntityID("http://eidas.node.se");

    final Element element = XMLObjectSupport.marshall(ep);

    final Endpoint ep2 = (Endpoint) XMLObjectSupport.getUnmarshaller(Endpoint.DEFAULT_ELEMENT_NAME).unmarshall(element);
    Assertions.assertEquals(ep.getEndpointType(), ep2.getEndpointType());
    Assertions.assertEquals(ep.getEntityID(), ep2.getEntityID());
  }

  /**
   * Tests the suspend-attribute
   *
   * @throws Exception
   */
  @Test
  public void testSuspendAnyAttribute() throws Exception {

    Endpoint ep = (Endpoint) XMLObjectSupport.buildXMLObject(Endpoint.DEFAULT_ELEMENT_NAME);
    ep.setEndpointType(Endpoint.PROXY_SERVICE_ENDPOINT_TYPE);
    ep.setEntityID("http://eidas.node.se");
    ep.setSuspend(true);

    Element element = XMLObjectSupport.marshall(ep);

    Endpoint ep2 = (Endpoint) XMLObjectSupport.getUnmarshaller(Endpoint.DEFAULT_ELEMENT_NAME).unmarshall(element);
    Assertions.assertEquals(ep.getEndpointType(), ep2.getEndpointType());
    Assertions.assertEquals(ep.getEntityID(), ep2.getEntityID());
    Assertions.assertTrue(ep2.getSuspend());

    ep = (Endpoint) XMLObjectSupport.buildXMLObject(Endpoint.DEFAULT_ELEMENT_NAME);
    ep.setEndpointType(Endpoint.PROXY_SERVICE_ENDPOINT_TYPE);
    ep.setEntityID("http://eidas.node.se");
    ep.setSuspend(false);

    element = XMLObjectSupport.marshall(ep);

    ep2 = (Endpoint) XMLObjectSupport.getUnmarshaller(Endpoint.DEFAULT_ELEMENT_NAME).unmarshall(element);
    Assertions.assertEquals(ep.getEndpointType(), ep2.getEndpointType());
    Assertions.assertEquals(ep.getEntityID(), ep2.getEntityID());
    Assertions.assertFalse(ep2.getSuspend());
  }

  /**
   * Tests the HideFromDiscovery-attribute
   *
   * @throws Exception
   */
  @Test
  public void testHideFromDiscoveryAttribute() throws Exception {
    Endpoint ep = (Endpoint) XMLObjectSupport.buildXMLObject(Endpoint.DEFAULT_ELEMENT_NAME);
    ep.setEndpointType(Endpoint.PROXY_SERVICE_ENDPOINT_TYPE);
    ep.setEntityID("http://eidas.node.se");
    ep.setHideFromDiscovery(true);

    Element element = XMLObjectSupport.marshall(ep);

    Endpoint ep2 = (Endpoint) XMLObjectSupport.getUnmarshaller(Endpoint.DEFAULT_ELEMENT_NAME).unmarshall(element);
    Assertions.assertEquals(ep.getEndpointType(), ep2.getEndpointType());
    Assertions.assertEquals(ep.getEntityID(), ep2.getEntityID());
    Assertions.assertTrue(ep2.getHideFromDiscovery());

    ep = (Endpoint) XMLObjectSupport.buildXMLObject(Endpoint.DEFAULT_ELEMENT_NAME);
    ep.setEndpointType(Endpoint.PROXY_SERVICE_ENDPOINT_TYPE);
    ep.setEntityID("http://eidas.node.se");

    element = XMLObjectSupport.marshall(ep);

    ep2 = (Endpoint) XMLObjectSupport.getUnmarshaller(Endpoint.DEFAULT_ELEMENT_NAME).unmarshall(element);
    Assertions.assertEquals(ep.getEndpointType(), ep2.getEndpointType());
    Assertions.assertEquals(ep.getEntityID(), ep2.getEntityID());
    Assertions.assertFalse(ep2.getHideFromDiscovery());
  }

}
