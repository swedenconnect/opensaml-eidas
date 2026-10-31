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
package se.swedenconnect.opensaml.eidas.xmlsec;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.opensaml.xmlsec.encryption.support.EncryptionConstants;

import se.swedenconnect.opensaml.eidas.OpenSAMLTestBase;
import se.swedenconnect.opensaml.xmlsec.config.SecurityConfiguration;

/**
 * Test cases that verify that all eIDAS security configuration profiles can be created.
 *
 * @author Martin Lindström
 */
public class SecurityConfigurationProfilesTest extends OpenSAMLTestBase {

  /**
   * Gets the security configurations to test.
   *
   * @return a stream of security configurations
   */
  static Stream<SecurityConfiguration> configurations() {
    return Stream.of(
        new EidasSecurityConfiguration(),
        new Eidas_1_4_SecurityConfiguration(),
        new RelaxedEidasSecurityConfiguration());
  }

  /**
   * Verifies that all configurations of a profile can be created.
   *
   * @param config the security configuration
   */
  @ParameterizedTest
  @MethodSource("configurations")
  public void testCreateConfigurations(final SecurityConfiguration config) {
    Assertions.assertNotNull(config.getEncryptionConfiguration());
    Assertions.assertNotNull(config.getDecryptionConfiguration());
    Assertions.assertNotNull(config.getSignatureSigningConfiguration());
    Assertions.assertNotNull(config.getSignatureValidationConfiguration());
  }

  /**
   * Verifies that the 1.4 profile does not accept RSA-OAEP with MGF1-SHA1 (rsa-oaep-mgf1p) for decryption.
   */
  @Test
  public void testEidas14Decryption() {
    final SecurityConfiguration config = new Eidas_1_4_SecurityConfiguration();
    Assertions.assertFalse(config.getDecryptionConfiguration().getIncludedAlgorithms()
        .contains(EncryptionConstants.ALGO_ID_KEYTRANSPORT_RSAOAEP));
    Assertions.assertTrue(config.getDecryptionConfiguration().getIncludedAlgorithms()
        .contains(EncryptionConstants.ALGO_ID_KEYTRANSPORT_RSAOAEP11));
  }

}
