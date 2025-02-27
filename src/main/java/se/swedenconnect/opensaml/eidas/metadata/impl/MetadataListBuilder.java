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

import org.opensaml.saml.common.AbstractSAMLObjectBuilder;
import se.swedenconnect.opensaml.eidas.metadata.MetadataList;

import javax.annotation.Nonnull;

/**
 * Builder for {@link MetadataList} objects.
 *
 * @author Martin Lindström
 */
public class MetadataListBuilder extends AbstractSAMLObjectBuilder<MetadataList> {

  /** {@inheritDoc} */
  @Nonnull
  @Override
  public MetadataList buildObject() {
    return new MetadataListImpl(MetadataList.DEFAULT_ELEMENT_NAME.getNamespaceURI(),
        MetadataList.DEFAULT_ELEMENT_LOCAL_NAME,
        MetadataList.DEFAULT_ELEMENT_NAME.getPrefix());
  }

  /** {@inheritDoc} */
  @Nonnull
  @Override
  public MetadataList buildObject(
      final String namespaceURI, @Nonnull final String localName, final String namespacePrefix) {
    return new MetadataListImpl(namespaceURI, localName, namespacePrefix);
  }

}
