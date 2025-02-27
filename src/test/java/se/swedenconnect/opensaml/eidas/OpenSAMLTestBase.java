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
package se.swedenconnect.opensaml.eidas;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.opensaml.core.xml.config.XMLObjectProviderRegistrySupport;
import org.opensaml.security.x509.X509Credential;
import org.opensaml.security.x509.impl.KeyStoreX509CredentialAdapter;
import org.w3c.dom.Element;

import net.shibboleth.shared.xml.XMLParserException;
import se.swedenconnect.opensaml.OpenSAMLInitializer;
import se.swedenconnect.opensaml.OpenSAMLSecurityExtensionConfig;

/**
 * Abstract base class that initializes OpenSAML for test classes.
 *
 * @author Martin Lindström (martin.lindstrom@litsec.se)
 */
public abstract class OpenSAMLTestBase {

  /** Builder features for the default parser pool. */
  private static final Map<String, Boolean> builderFeatures;

  /** Factory for creating certificates. */
  private static CertificateFactory factory = null;

  static {
    builderFeatures = new HashMap<>();
    builderFeatures.put("http://apache.org/xml/features/disallow-doctype-decl", Boolean.TRUE);
    builderFeatures.put("http://apache.org/xml/features/validation/schema/normalized-value", Boolean.FALSE);
    builderFeatures.put("http://javax.xml.XMLConstants/feature/secure-processing", Boolean.TRUE);

    try {
      factory = CertificateFactory.getInstance("X.509");
    }
    catch (final CertificateException e) {
      throw new SecurityException(e);
    }
  }

  /**
   * Initializes the OpenSAML library.
   *
   * @throws Exception for init errors
   */
  @BeforeAll
  public static void initializeOpenSAML() throws Exception {

    OpenSAMLInitializer.getInstance()
        .initialize(
            new OpenSAMLSecurityExtensionConfig());
  }

  /**
   * Loads an XML element from file.
   *
   * @param systemResource the file
   * @return the element
   * @throws XMLParserException for parsing errors
   */
  public static Element loadElement(final String systemResource) throws XMLParserException {
    final InputStream serviceListStream = ClassLoader.getSystemResourceAsStream(systemResource);
    return XMLObjectProviderRegistrySupport.getParserPool().parse(serviceListStream).getDocumentElement();
  }

  /**
   * Loads a certificate from file (system resource).
   *
   * @param systemResource the certificate file
   * @return a certificate
   * @throws CertificateException for decoding errors
   */
  public static X509Certificate loadCertificate(final String systemResource) throws CertificateException {
    final InputStream certStream = ClassLoader.getSystemResourceAsStream(systemResource);
    return (X509Certificate) factory.generateCertificate(certStream);
  }

  /**
   * Loads a key store.
   *
   * @param keyStoreStream the stream for the keystore
   * @param keyStorePassword the password
   * @param keyStoreType the type
   * @return a {@code KeyStore} object
   * @throws KeyStoreException for key store errors
   * @throws IOException if the key store cannot be found
   */
  public static KeyStore loadKeyStore(final InputStream keyStoreStream, final String keyStorePassword,
      final String keyStoreType) throws KeyStoreException,
      IOException {
    try {
      final KeyStore keyStore =
          keyStoreType != null ? KeyStore.getInstance(keyStoreType) : KeyStore.getInstance(KeyStore.getDefaultType());
      keyStore.load(keyStoreStream, keyStorePassword.toCharArray());
      return keyStore;
    }
    catch (NoSuchAlgorithmException | CertificateException e) {
      throw new KeyStoreException(e);
    }
  }

  /**
   * Loads a key store and creates a credential.
   *
   * @param keyStoreStream the stream for the keystore
   * @param keyStorePassword the key store password
   * @param alias the alias
   * @param keyPassword the key password
   * @return a credential
   * @throws KeyStoreException for key store errors
   * @throws IOException if the key store cannot be found
   */
  public static X509Credential loadKeyStoreCredential(final InputStream keyStoreStream, final String keyStorePassword,
      final String alias, final String keyPassword)
      throws KeyStoreException, IOException {
    final KeyStore keyStore = loadKeyStore(keyStoreStream, keyStorePassword, "jks");
    return new KeyStoreX509CredentialAdapter(keyStore, alias, keyPassword.toCharArray());
  }

}
