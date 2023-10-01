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
package se.swedenconnect.opensaml.eidas.ext.impl;

import org.opensaml.saml.common.AbstractSAMLObjectBuilder;

import se.swedenconnect.opensaml.eidas.common.EidasConstants;
import se.swedenconnect.opensaml.eidas.ext.SPType;

/**
 * Builder of {@link SPTypeImpl}.
 *
 * @author Martin Lindström
 */
public class SPTypeBuilder extends AbstractSAMLObjectBuilder<SPType> {

  /**
   * Constructor.
   */
  public SPTypeBuilder() {
  }

  /** {@inheritDoc} */
  @Override
  public SPType buildObject() {
    return this.buildObject(EidasConstants.EIDAS_NS, SPType.DEFAULT_ELEMENT_LOCAL_NAME, EidasConstants.EIDAS_PREFIX);
  }

  /** {@inheritDoc} */
  @Override
  public SPType buildObject(final String namespaceURI, final String localName, final String namespacePrefix) {
    return new SPTypeImpl(namespaceURI, localName, namespacePrefix);
  }

}
