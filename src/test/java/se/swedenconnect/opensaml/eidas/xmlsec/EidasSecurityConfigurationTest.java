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
package se.swedenconnect.opensaml.eidas.xmlsec;

import java.security.cert.X509Certificate;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;

import org.apache.xml.security.signature.XMLSignature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opensaml.core.xml.config.XMLObjectProviderRegistrySupport;
import org.opensaml.core.xml.io.MarshallingException;
import org.opensaml.core.xml.util.XMLObjectSupport;
import org.opensaml.saml.common.SignableSAMLObject;
import org.opensaml.saml.saml2.core.AuthnRequest;
import org.opensaml.saml.saml2.core.Issuer;
import org.opensaml.security.SecurityException;
import org.opensaml.security.credential.Credential;
import org.opensaml.security.credential.impl.AbstractCredentialResolver;
import org.opensaml.security.x509.BasicX509Credential;
import org.opensaml.security.x509.X509Credential;
import org.opensaml.xmlsec.SecurityConfigurationSupport;
import org.opensaml.xmlsec.SignatureSigningParameters;
import org.opensaml.xmlsec.config.impl.DefaultSecurityConfigurationBootstrap;
import org.opensaml.xmlsec.criterion.SignatureSigningConfigurationCriterion;
import org.opensaml.xmlsec.criterion.SignatureValidationConfigurationCriterion;
import org.opensaml.xmlsec.impl.BasicSignatureSigningConfiguration;
import org.opensaml.xmlsec.impl.BasicSignatureSigningParametersResolver;
import org.opensaml.xmlsec.signature.Signature;
import org.opensaml.xmlsec.signature.support.SignatureConstants;
import org.opensaml.xmlsec.signature.support.SignatureException;
import org.opensaml.xmlsec.signature.support.SignatureSupport;
import org.opensaml.xmlsec.signature.support.SignatureTrustEngine;
import org.opensaml.xmlsec.signature.support.impl.ExplicitKeySignatureTrustEngine;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import net.shibboleth.shared.resolver.CriteriaSet;
import net.shibboleth.shared.resolver.ResolverException;
import net.shibboleth.shared.xml.SerializeSupport;
import se.swedenconnect.opensaml.OpenSAMLInitializer;
import se.swedenconnect.opensaml.OpenSAMLSecurityDefaultsConfig;
import se.swedenconnect.opensaml.OpenSAMLSecurityExtensionConfig;
import se.swedenconnect.opensaml.eidas.OpenSAMLTestBase;

/**
 * Test cases for {@code EidasSecurityConfiguration} where we focus on signing according to the eIDAS crypto spec.
 *
 * @author Martin Lindström (martin.lindstrom@litsec.se)
 */
public class EidasSecurityConfigurationTest {

  /**
   * Initializes the OpenSAML library.
   *
   * @throws Exception for init errors
   */
  @BeforeEach
  public void initializeOpenSAML() throws Exception {

    OpenSAMLInitializer.getInstance()
        .initialize(
            new OpenSAMLSecurityDefaultsConfig(new EidasSecurityConfiguration()),
            new OpenSAMLSecurityExtensionConfig());
  }

  /**
   * Verifies that ECDSA is selected for signing if we have a EC key.
   *
   * @throws Exception for errors
   */
  @Test
  public void testECDSA() throws Exception {
    final X509Credential signingCred = OpenSAMLTestBase.loadKeyStoreCredential(
        ClassLoader.getSystemResourceAsStream("ec.jks"), "test", "test", "test");

    final AuthnRequest authnRequest = getMockAuthnRequest();

    sign(authnRequest, signingCred);

    final Element element =
        XMLObjectProviderRegistrySupport.getMarshallerFactory().getMarshaller(authnRequest).marshall(authnRequest);
    System.out.println(SerializeSupport.prettyPrintXML(element));

    Assertions.assertNotNull(authnRequest.getSignature());
    Assertions.assertEquals(SignatureConstants.ALGO_ID_SIGNATURE_ECDSA_SHA256,
        authnRequest.getSignature().getSignatureAlgorithm());

    validate(authnRequest, signingCred.getEntityCertificate());
  }

  /**
   * Verifies that RSA-PSS is selected for signing if we have a RSA key.
   *
   * @throws Exception for errors
   */
  @Test
  public void testRSAPSS() throws Exception {
    final X509Credential signingCred = OpenSAMLTestBase.loadKeyStoreCredential(
        ClassLoader.getSystemResourceAsStream("rsa.jks"), "test", "test", "test");

    final AuthnRequest authnRequest = getMockAuthnRequest();

    sign(authnRequest, signingCred);

    //System.out.println(SerializeSupport.prettyPrintXML(XMLObjectSupport.marshall(authnRequest)));

    Assertions.assertNotNull(authnRequest.getSignature());
    Assertions.assertEquals(XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA256_MGF1,
        authnRequest.getSignature().getSignatureAlgorithm());

    validate(authnRequest, signingCred.getEntityCertificate());
  }

  /**
   * Creates an {@link AuthnRequest} that we sign.
   *
   * @return an authentication request object
   */
  private static AuthnRequest getMockAuthnRequest() {
    final AuthnRequest authnRequest = (AuthnRequest) XMLObjectSupport.buildXMLObject(AuthnRequest.DEFAULT_ELEMENT_NAME);
    authnRequest.setID("_BmPDpaRGHfHCsqRdeoTHVnsPhNvr3ulQdUoXGgnV");
    authnRequest.setIssueInstant(Instant.now());
    final Issuer issuer = (Issuer) XMLObjectSupport.buildXMLObject(Issuer.DEFAULT_ELEMENT_NAME);
    issuer.setFormat(Issuer.ENTITY);
    issuer.setValue("http://www.fake.issuer.com");
    authnRequest.setIssuer(issuer);
    return authnRequest;
  }

  /**
   * Signs the supplied SAML object using the credentials.
   *
   * @param object object to sign
   * @param signingCredentials signature credentials
   * @param <T> the object type
   * @throws SignatureException for signature creation errors
   */
  public static <T extends SignableSAMLObject> void sign(final T object, final Credential signingCredentials)
      throws SignatureException {
    try {
      object.setSignature(null);

      final BasicSignatureSigningConfiguration signatureCreds = new BasicSignatureSigningConfiguration();
      signatureCreds.setSigningCredentials(Collections.singletonList(signingCredentials));

      final BasicSignatureSigningParametersResolver signatureParametersResolver =
          new BasicSignatureSigningParametersResolver();
      final CriteriaSet criteriaSet = new CriteriaSet(new SignatureSigningConfigurationCriterion(
          SecurityConfigurationSupport.getGlobalSignatureSigningConfiguration(),  // Will get us our eIDAS config
          signatureCreds));

      final SignatureSigningParameters parameters = signatureParametersResolver.resolveSingle(criteriaSet);
      SignatureSupport.signObject(object, parameters);
    }
    catch (ResolverException | org.opensaml.security.SecurityException | MarshallingException e) {
      throw new SignatureException(e);
    }
  }

  public static <T extends SignableSAMLObject> void validate(final T object, final X509Certificate cert)
      throws SignatureException, SecurityException {

    // Temporary code until we figure out how to make the OpenSAML unmarshaller to
    // mark the ID attribute as an ID.
    //
    final Attr idAttr = object.getDOM().getAttributeNode("ID");
    if (idAttr != null) {
      idAttr.getOwnerElement().setIdAttributeNode(idAttr, true);
    }

    final SignatureTrustEngine trustEngine = new ExplicitKeySignatureTrustEngine(new StaticCredentialResolver(cert),
        DefaultSecurityConfigurationBootstrap.buildBasicInlineKeyInfoCredentialResolver());

    final Signature signature = object.getSignature();
    if (signature == null) {
      throw new SignatureException("Object is not signed");
    }
    final CriteriaSet criteriaSet = new CriteriaSet();
    criteriaSet.add(new SignatureValidationConfigurationCriterion(
        // Gets us our eIDAS configuration
        SecurityConfigurationSupport.getGlobalSignatureValidationConfiguration()));

    if (!trustEngine.validate(signature, criteriaSet)) {
      throw new SignatureException("Signature validation failed");
    }
  }

  public static class StaticCredentialResolver extends AbstractCredentialResolver {

    private final X509Credential cred;

    public StaticCredentialResolver(final X509Certificate cert) {
      this.cred = new BasicX509Credential(cert);
    }

    @Override
    public Iterable<Credential> resolve(final CriteriaSet criteriaSet) throws ResolverException {
      return Arrays.asList(this.cred);
    }

  }

}
