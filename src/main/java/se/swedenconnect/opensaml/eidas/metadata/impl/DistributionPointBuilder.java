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
package se.swedenconnect.opensaml.eidas.metadata.impl;

import org.opensaml.saml.common.AbstractSAMLObjectBuilder;

import se.swedenconnect.opensaml.eidas.metadata.DistributionPoint;

import javax.annotation.Nonnull;

/**
 * Builder for {@link DistributionPoint} objects.
 *
 * @author Martin Lindström
 */
public class DistributionPointBuilder extends AbstractSAMLObjectBuilder<DistributionPoint> {

  /** {@inheritDoc} */
  @Nonnull
  @Override
  public DistributionPoint buildObject() {
    return new DistributionPointImpl(DistributionPoint.DEFAULT_ELEMENT_NAME.getNamespaceURI(),
        DistributionPoint.DEFAULT_ELEMENT_LOCAL_NAME,
        DistributionPoint.DEFAULT_ELEMENT_NAME.getPrefix());
  }

  /** {@inheritDoc} */
  @Nonnull
  @Override
  public DistributionPoint buildObject(
      final String namespaceURI, @Nonnull final String localName, final String namespacePrefix) {
    return new DistributionPointImpl(namespaceURI, localName, namespacePrefix);
  }

}
