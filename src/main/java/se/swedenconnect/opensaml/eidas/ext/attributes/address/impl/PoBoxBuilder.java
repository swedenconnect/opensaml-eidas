/*
 * Copyright 2016-2023 Litsec AB
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
package se.swedenconnect.opensaml.eidas.ext.attributes.address.impl;

import org.opensaml.core.xml.AbstractXMLObjectBuilder;

import se.swedenconnect.opensaml.eidas.common.EidasConstants;
import se.swedenconnect.opensaml.eidas.ext.attributes.address.PoBox;

/**
 * Builder for {@code PoBox}.
 *
 * @author Martin Lindström
 */
public class PoBoxBuilder extends AbstractXMLObjectBuilder<PoBox> {

  /** {@inheritDoc} */
  public PoBox buildObject() {
    return this.buildObject(EidasConstants.EIDAS_NP_NS, PoBox.DEFAULT_ELEMENT_LOCAL_NAME,
        EidasConstants.EIDAS_NP_PREFIX);
  }

  /** {@inheritDoc} */
  @Override
  public PoBox buildObject(final String namespaceURI, final String localName, final String namespacePrefix) {
    return new PoBoxImpl(namespaceURI, localName, namespacePrefix);
  }

}
