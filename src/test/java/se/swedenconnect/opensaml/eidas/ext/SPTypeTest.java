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
package se.swedenconnect.opensaml.eidas.ext;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opensaml.core.xml.config.XMLObjectProviderRegistrySupport;
import org.opensaml.core.xml.io.UnmarshallingException;
import org.opensaml.core.xml.util.XMLObjectSupport;
import org.w3c.dom.Element;

import se.swedenconnect.opensaml.eidas.OpenSAMLTestBase;
import se.swedenconnect.opensaml.eidas.ext.impl.SPTypeBuilder;

/**
 * Test cases for building, marshalling and unmarshalling of {@code SPType} elements.
 *
 * @author Martin Lindström
 */
public class SPTypeTest extends OpenSAMLTestBase {

  /**
   * Test building a {@code SPType} element.
   *
   * @throws Exception for errors
   */
  @Test
  public void testBuild() throws Exception {

    final SPTypeBuilder builder = new SPTypeBuilder();
    final SPType type = builder.buildObject();
    Assertions.assertNotNull(type);
    type.setType(SPTypeEnumeration.PRIVATE);

    final SPType type2 = (SPType) XMLObjectSupport.buildXMLObject(SPType.DEFAULT_ELEMENT_NAME);
    Assertions.assertNotNull(type2);
  }

  /**
   * Test marshalling an {@code SPType} element.
   *
   * @throws Exception for errors
   */
  @Test
  public void testMarshall() throws Exception {
    final SPType type = (SPType) XMLObjectSupport.buildXMLObject(SPType.DEFAULT_ELEMENT_NAME);
    type.setType(SPTypeEnumeration.PUBLIC);

    final Element element = XMLObjectSupport.marshall(type);
    Assertions.assertEquals("SPType", element.getLocalName());
    Assertions.assertEquals("public", element.getTextContent());
  }

  /**
   * Test unmarshalling into an SPType.
   *
   * @throws Exception for errors
   */
  @Test
  public void testUnmarshall() throws Exception {
    final String pub = "<eidas:SPType xmlns:eidas=\"http://eidas.europa.eu/saml-extensions\">public</eidas:SPType>";
    SPType type = (SPType) XMLObjectSupport.unmarshallFromInputStream(XMLObjectProviderRegistrySupport.getParserPool(),
        new ByteArrayInputStream(pub.getBytes()));
    Assertions.assertEquals(SPTypeEnumeration.PUBLIC, type.getType());

    final String pubUppercase =
        "<eidas:SPType xmlns:eidas=\"http://eidas.europa.eu/saml-extensions\">PUBLIC</eidas:SPType>";
    type = (SPType) XMLObjectSupport.unmarshallFromInputStream(XMLObjectProviderRegistrySupport.getParserPool(),
        new ByteArrayInputStream(pubUppercase.getBytes()));
    Assertions.assertEquals(SPTypeEnumeration.PUBLIC, type.getType());

    final String priv = "<eidas:SPType xmlns:eidas=\"http://eidas.europa.eu/saml-extensions\">private</eidas:SPType>";
    type = (SPType) XMLObjectSupport.unmarshallFromInputStream(XMLObjectProviderRegistrySupport.getParserPool(),
        new ByteArrayInputStream(priv.getBytes()));
    Assertions.assertEquals(SPTypeEnumeration.PRIVATE, type.getType());

    final String privUppercase =
        "<eidas:SPType xmlns:eidas=\"http://eidas.europa.eu/saml-extensions\">PRIVATE</eidas:SPType>";
    type = (SPType) XMLObjectSupport.unmarshallFromInputStream(XMLObjectProviderRegistrySupport.getParserPool(),
        new ByteArrayInputStream(privUppercase.getBytes()));
    Assertions.assertEquals(SPTypeEnumeration.PRIVATE, type.getType());
  }

  @Test
  public void testUnmarshallErrors() throws Exception {
    final String badValue =
        "<eidas:SPType xmlns:eidas=\"http://eidas.europa.eu/saml-extensions\">government</eidas:SPType>";

    Assertions.assertThrows(UnmarshallingException.class, () -> {
      XMLObjectSupport.unmarshallFromInputStream(XMLObjectProviderRegistrySupport.getParserPool(),
          new ByteArrayInputStream(badValue.getBytes()));
    });

    final String missingValue =
        "<eidas:SPType xmlns:eidas=\"http://eidas.europa.eu/saml-extensions\">  </eidas:SPType>";

    Assertions.assertThrows(UnmarshallingException.class, () -> {
      XMLObjectSupport.unmarshallFromInputStream(XMLObjectProviderRegistrySupport.getParserPool(),
          new ByteArrayInputStream(missingValue.getBytes()));
    });
  }

}
