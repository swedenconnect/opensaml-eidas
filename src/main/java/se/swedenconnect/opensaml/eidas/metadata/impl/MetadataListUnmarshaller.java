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
import org.opensaml.core.xml.io.UnmarshallingException;
import org.opensaml.saml.common.AbstractSAMLObjectUnmarshaller;
import org.w3c.dom.Attr;
import se.swedenconnect.opensaml.eidas.metadata.MetadataList;
import se.swedenconnect.opensaml.eidas.metadata.MetadataLocation;

import javax.annotation.Nonnull;

/**
 * A thread safe unmarshaller for {@link MetadataList}.
 *
 * @author Martin Lindström
 */
public class MetadataListUnmarshaller extends AbstractSAMLObjectUnmarshaller {

  /** {@inheritDoc} */
  @Override
  protected void processChildElement(
      @Nonnull final XMLObject parentSAMLObject, @Nonnull final XMLObject childSAMLObject)
      throws UnmarshallingException {
    final MetadataList mdl = (MetadataList) parentSAMLObject;

    if (childSAMLObject instanceof MetadataLocation) {
      mdl.getMetadataLocations().add((MetadataLocation) childSAMLObject);
    }
    else {
      super.processChildElement(parentSAMLObject, childSAMLObject);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void processAttribute(@Nonnull final XMLObject samlObject, final Attr attribute)
      throws UnmarshallingException {

    final MetadataList mdl = (MetadataList) samlObject;

    if (attribute.getLocalName().equals(MetadataList.TERRITORY_ATTR_NAME)) {
      mdl.setTerritory(attribute.getValue());
    }
    else {
      this.processUnknownAttribute(mdl, attribute);
    }
  }

}
