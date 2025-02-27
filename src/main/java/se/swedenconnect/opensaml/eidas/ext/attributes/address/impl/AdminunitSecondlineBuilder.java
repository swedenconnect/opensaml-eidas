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
package se.swedenconnect.opensaml.eidas.ext.attributes.address.impl;

import jakarta.annotation.Nonnull;
import org.opensaml.core.xml.AbstractXMLObjectBuilder;
import se.swedenconnect.opensaml.eidas.common.EidasConstants;
import se.swedenconnect.opensaml.eidas.ext.attributes.address.AdminunitSecondline;

/**
 * Builder for {@code AdminunitSecondline}.
 *
 * @author Martin Lindström
 */
public class AdminunitSecondlineBuilder extends AbstractXMLObjectBuilder<AdminunitSecondline> {

  /**
   * Builds an {@link AdminunitSecondline} object.
   *
   * @return an {@link AdminunitSecondline} object
   */
  @Nonnull
  public AdminunitSecondline buildObject() {
    return this.buildObject(EidasConstants.EIDAS_NP_NS, AdminunitSecondline.DEFAULT_ELEMENT_LOCAL_NAME,
        EidasConstants.EIDAS_NP_PREFIX);
  }

  /** {@inheritDoc} */
  @Override
  @Nonnull
  public AdminunitSecondline buildObject(
      final String namespaceURI, @Nonnull final String localName, final String namespacePrefix) {
    return new AdminunitSecondlineImpl(namespaceURI, localName, namespacePrefix);
  }

}
