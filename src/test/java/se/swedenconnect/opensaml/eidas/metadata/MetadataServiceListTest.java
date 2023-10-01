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

import java.security.cert.X509Certificate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opensaml.core.xml.util.XMLObjectSupport;
import org.opensaml.xmlsec.signature.support.SignatureException;
import org.w3c.dom.Element;

import se.swedenconnect.opensaml.eidas.OpenSAMLTestBase;

/**
 * Test cases for {@code MetadataServiceList} and {@code MetadataServiceListSignatureValidator}.
 *
 * @author Martin Lindström
 */
public class MetadataServiceListTest extends OpenSAMLTestBase {

  /**
   * Tests that we can parse a "real life" metadata service list.
   *
   * @throws Exception for errors
   */
  @Test
  public void testDecode() throws Exception {

    final Element mdslElement = loadElement("mdservicelist.xml");
    final MetadataServiceList mdsl = (MetadataServiceList) XMLObjectSupport
        .getUnmarshaller(MetadataServiceList.DEFAULT_ELEMENT_NAME).unmarshall(mdslElement);

    Assertions.assertTrue(mdsl.isSigned());

    Assertions.assertEquals(MetadataServiceListVersion.VERSION_10, mdsl.getVersion());
    Assertions.assertEquals("Swedish E-Identification Board", mdsl.getSchemeInformation().getIssuerName());
    Assertions.assertEquals("urn:se:elegnamnden:eidas:mdlist:local", mdsl.getSchemeInformation().getSchemeIdentifier());
    Assertions.assertEquals("SE", mdsl.getSchemeInformation().getSchemeTerritory());
    Assertions.assertEquals(14, mdsl.getMetadataLists().size());
    Assertions.assertEquals("file:///opt/webapp/configEidas/aggregation/mdServiceList.xml",
        mdsl.getDistributionPoints().getDistributionPoints().get(0).getURI());
  }

  /**
   * Tests signature validation.
   *
   * @throws Exception for errors
   */
  @Test
  public void testSignatureValidate() throws Exception {
    final Element mdslElement = loadElement("mdservicelist.xml");
    final MetadataServiceList mdsl = (MetadataServiceList) XMLObjectSupport
        .getUnmarshaller(MetadataServiceList.DEFAULT_ELEMENT_NAME).unmarshall(mdslElement);

    final X509Certificate cert = loadCertificate("eidas-servicelist-validation-cert.crt");

    final MetadataServiceListSignatureValidator validator = new MetadataServiceListSignatureValidator();
    validator.validateSignature(mdsl, cert);
  }

  /**
   * Tests that signature validation fails if we supply the wrong cert.
   *
   * @throws Exception for errors
   */
  @Test
  public void testSignatureValidateWrongCert() throws Exception {
    final Element mdslElement = loadElement("mdservicelist.xml");
    final MetadataServiceList mdsl = (MetadataServiceList) XMLObjectSupport
        .getUnmarshaller(MetadataServiceList.DEFAULT_ELEMENT_NAME).unmarshall(mdslElement);

    final X509Certificate cert = loadCertificate("Litsec_SAML_Signing.crt");

    final MetadataServiceListSignatureValidator validator = new MetadataServiceListSignatureValidator();
    Assertions.assertThrows(SignatureException.class, () -> {
      validator.validateSignature(mdsl, cert);
    });
  }

  /**
   * Tests that signature validation fails if the signature is bad.
   *
   * @throws Exception for errors
   */
  @Test
  public void testSignatureValidateBadSignature() throws Exception {

    final Element mdslElement = loadElement("mdservicelist-badsign.xml");
    final MetadataServiceList mdsl = (MetadataServiceList) XMLObjectSupport
        .getUnmarshaller(MetadataServiceList.DEFAULT_ELEMENT_NAME).unmarshall(mdslElement);

    final X509Certificate cert = loadCertificate("eidas-servicelist-validation-cert.crt");

    final MetadataServiceListSignatureValidator validator = new MetadataServiceListSignatureValidator();
    Assertions.assertThrows(SignatureException.class, () -> {
      validator.validateSignature(mdsl, cert);
    });
  }

}
