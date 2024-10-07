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
package se.swedenconnect.opensaml.eidas.examples;

import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.opensaml.core.xml.config.XMLObjectProviderRegistrySupport;
import org.opensaml.core.xml.util.XMLObjectSupport;
import org.opensaml.saml.saml2.core.Assertion;
import org.opensaml.saml.saml2.core.Attribute;
import org.opensaml.saml.saml2.core.AttributeStatement;

import se.swedenconnect.opensaml.eidas.OpenSAMLTestBase;

/**
 * Example code for how to process an eIDAS SAML {@code Assertion}.
 * <p>
 * We parse the {@code Assertion} given as an example in section 6.5 of
 * <a href="https://ec.europa.eu/cefdigital/wiki/download/attachments/82773108/eIDAS%20SAML%20Message%20Format%20v.1.2%20Final.pdf">eIDAS SAML Message Format</a>.
 * </p>
 *
 * @author Martin Lindström
 */
public class ParseAssertionExample extends OpenSAMLTestBase {

  public static void printAssertion(Assertion assertion) {

    System.out.println("Attributes:");
    if (assertion.getAttributeStatements().isEmpty()) {
      System.out.println("  No attribute statement available in assertion");
    }
    else {
      AttributeStatement as = assertion.getAttributeStatements().get(0);
      for (Attribute attr : as.getAttributes()) {
        System.out.println("  " + attr.getName());
      }
    }

    // TODO

  }

  @Test
  public void parseAssertionExample() throws Exception {
    InputStream is = this.getClass().getResourceAsStream("/example-assertion.xml");
    Assertion assertion = (Assertion) XMLObjectSupport.unmarshallFromInputStream(XMLObjectProviderRegistrySupport.getParserPool(), is);

    printAssertion(assertion);
  }

}
