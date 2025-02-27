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
package se.swedenconnect.opensaml.eidas.xmlsec;

import org.opensaml.xmlsec.DecryptionConfiguration;
import org.opensaml.xmlsec.EncryptionConfiguration;
import org.opensaml.xmlsec.encryption.support.EncryptionConstants;
import org.opensaml.xmlsec.impl.BasicDecryptionConfiguration;
import org.opensaml.xmlsec.impl.BasicEncryptionConfiguration;

import java.util.List;

/**
 * A security configuration for OpenSAML according to version 1.4 of "eIDAS - Cryptographic requirements for the
 * Interoperability Framework".
 *
 * @author Martin Lindström
 */
public class Eidas_1_4_SecurityConfiguration extends EidasSecurityConfiguration {

  /** {@inheritDoc} */
  @Override
  public String getProfileName() {
    return "eidas_1_4";
  }

  /**
   * Creates an encryption configuration for eIDAS according to section 3.2.1 and 3.2.2 of "eIDAS - Cryptographic
   * requirements for the Interoperability Framework version 1.4".
   */
  @Override
  protected EncryptionConfiguration createDefaultEncryptionConfiguration() {

    final BasicEncryptionConfiguration config =
        (BasicEncryptionConfiguration) super.createDefaultEncryptionConfiguration();

    config.setKeyTransportEncryptionAlgorithms(List.of(
        EncryptionConstants.ALGO_ID_KEYTRANSPORT_RSAOAEP11,
        EncryptionConstants.ALGO_ID_KEYWRAP_AES256,
        EncryptionConstants.ALGO_ID_KEYWRAP_AES128
    ));
    config.setExcludedAlgorithms(List.of(
        EncryptionConstants.ALGO_ID_BLOCKCIPHER_TRIPLEDES,
        EncryptionConstants.ALGO_ID_KEYTRANSPORT_RSA15,
        EncryptionConstants.ALGO_ID_BLOCKCIPHER_AES128,
        EncryptionConstants.ALGO_ID_BLOCKCIPHER_AES192,
        EncryptionConstants.ALGO_ID_BLOCKCIPHER_AES256,
        EncryptionConstants.ALGO_ID_KEYTRANSPORT_RSAOAEP
    ));

    return config;
  }

  /**
   * Creates a decryption configuration for eIDAS according to section 3.2.1 and 3.2.2 of "eIDAS - Cryptographic
   * requirements for the Interoperability Framework".
   */
  @Override
  protected DecryptionConfiguration createDefaultDecryptionConfiguration() {

    final BasicDecryptionConfiguration config =
        (BasicDecryptionConfiguration) super.getDecryptionConfiguration();

    config.setIncludedAlgorithms(config.getIncludedAlgorithms().stream()
        .filter(a -> !EncryptionConstants.ALGO_ID_KEYTRANSPORT_RSAOAEP.equals(a))
        .toList());

    return config;
  }

}
