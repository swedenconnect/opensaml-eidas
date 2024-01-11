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
package se.swedenconnect.opensaml.eidas.metadata;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.opensaml.saml.security.impl.SAMLSignatureProfileValidator;
import org.opensaml.security.SecurityException;
import org.opensaml.security.credential.Credential;
import org.opensaml.security.credential.impl.AbstractCredentialResolver;
import org.opensaml.security.x509.BasicX509Credential;
import org.opensaml.security.x509.X509Credential;
import org.opensaml.xmlsec.config.impl.DefaultSecurityConfigurationBootstrap;
import org.opensaml.xmlsec.signature.Signature;
import org.opensaml.xmlsec.signature.support.SignatureException;
import org.opensaml.xmlsec.signature.support.SignaturePrevalidator;
import org.opensaml.xmlsec.signature.support.SignatureTrustEngine;
import org.opensaml.xmlsec.signature.support.impl.ExplicitKeySignatureTrustEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.shibboleth.shared.resolver.CriteriaSet;
import net.shibboleth.shared.resolver.Criterion;
import net.shibboleth.shared.resolver.ResolverException;

/**
 * Utility bean for validating signatures on a {@link MetadataServiceList} object.
 *
 * @author Martin Lindström
 */
public class MetadataServiceListSignatureValidator {

  /** Logging instance. */
  private static final Logger log = LoggerFactory.getLogger(MetadataServiceListSignatureValidator.class);

  /** Validator for checking the a Signature is correct with respect to the standards. */
  private final SignaturePrevalidator signatureProfileValidator = new SAMLSignatureProfileValidator();

  /** The signature trust engine to be used when validating signatures. */
  private final SignatureTrustEngine signatureTrustEngine;

  /**
   * Constructor setting up the validator bean.
   */
  public MetadataServiceListSignatureValidator() {
    this.signatureTrustEngine = new ExplicitKeySignatureTrustEngine(new StaticCertificateResolver(),
        DefaultSecurityConfigurationBootstrap.buildBasicInlineKeyInfoCredentialResolver());
  }

  /**
   * Validates the signature of the supplied {@code MetadataServiceList} element using the supplied certificate.
   *
   * @param mdsl the {@code MetadataServiceList}
   * @param signersCertificate the certificate of the signer
   * @throws SignatureException for validation errors
   */
  public void validateSignature(final MetadataServiceList mdsl, final X509Certificate signersCertificate)
      throws SignatureException {

    // The signature to validate.
    //
    final Signature signature = mdsl.getSignature();
    if (signature == null) {
      log.warn("Metadata service list is not signed");
      throw new SignatureException("Metadata service list has no signature");
    }

    // Is the signature correct according to the SAML signature profile?
    //
    try {
      this.signatureProfileValidator.validate(signature);
    }
    catch (final SignatureException e) {
      log.warn("Signature failed pre-validation: " + e.getMessage());
      throw e;
    }

    // Validate the signature.
    //
    final CriteriaSet criteria = new CriteriaSet(new X509CertificateCriterion(signersCertificate));
    try {
      if (!this.signatureTrustEngine.validate(signature, criteria)) {
        final String msg = "Signature validation failed";
        log.warn(msg);
        throw new SignatureException(msg);
      }
    }
    catch (final SecurityException e) {
      final String msg = String.format("A problem was encountered evaluating the signature: %s", e.getMessage());
      log.warn(msg);
      throw new SignatureException(msg, e);
    }

    log.debug("Signature on MetadataServiceList successfully verified");
  }

  /**
   * A static resolver that looks for a {@code X509CertificateCriterion}.
   *
   * @author Martin Lindström
   */
  private static class StaticCertificateResolver extends AbstractCredentialResolver {

    /** {@inheritDoc} */
    @Override
    public Iterable<Credential> resolve(final CriteriaSet criteriaSet) throws ResolverException {

      final List<Credential> certs = new ArrayList<>();

      Optional.ofNullable(criteriaSet)
          .ifPresent(set -> set.stream()
              .filter(X509CertificateCriterion.class::isInstance)
              .map(X509CertificateCriterion.class::cast)
              .map(X509CertificateCriterion::getCertificate)
              .forEach(c -> certs.add(c)));

      return Collections.unmodifiableList(certs);
    }
  }

  /**
   * A simple criterion that holds a certificate (for validation).
   *
   * @author Martin Lindström (martin.lindstrom@litsec.se)
   */
  private static class X509CertificateCriterion implements Criterion {

    /** The certificate. */
    private final X509Credential certificate;

    /**
     * Constructor.
     *
     * @param certificate the certificate to assign
     */
    public X509CertificateCriterion(final X509Certificate certificate) {
      this.certificate = new BasicX509Credential(certificate);
    }

    /**
     * Returns the certificate held by this criterion.
     *
     * @return the certificate
     */
    public X509Credential getCertificate() {
      return this.certificate;
    }
  }

}
