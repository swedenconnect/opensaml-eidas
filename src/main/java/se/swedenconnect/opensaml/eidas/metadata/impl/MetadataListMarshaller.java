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
package se.swedenconnect.opensaml.eidas.metadata.impl;

import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.io.MarshallingException;
import org.opensaml.saml.common.AbstractSAMLObjectMarshaller;
import org.w3c.dom.Element;
import se.swedenconnect.opensaml.eidas.metadata.MetadataList;

import javax.annotation.Nonnull;

/**
 * A marshaller for {@link MetadataList}.
 *
 * @author Martin Lindström
 */
public class MetadataListMarshaller extends AbstractSAMLObjectMarshaller {

  /** {@inheritDoc} */
  @Override
  protected void marshallAttributes(@Nonnull final XMLObject xmlObject, @Nonnull final Element domElement)
      throws MarshallingException {

    final MetadataList mdl = (MetadataList) xmlObject;

    if (mdl.getTerritory() != null) {
      domElement.setAttributeNS(null, MetadataList.TERRITORY_ATTR_NAME, mdl.getTerritory());
    }
    this.marshallUnknownAttributes(mdl, domElement);
  }

}
