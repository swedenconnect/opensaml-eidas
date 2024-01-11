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

import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.io.UnmarshallingException;
import org.opensaml.saml.common.AbstractSAMLObjectUnmarshaller;
import org.opensaml.xmlsec.signature.KeyInfo;
import org.w3c.dom.Attr;

import se.swedenconnect.opensaml.eidas.metadata.Endpoint;
import se.swedenconnect.opensaml.eidas.metadata.MetadataLocation;

/**
 * Unmarshaller for {@link MetadataLocation}.
 *
 * @author Martin Lindström
 */
public class MetadataLocationUnmarshaller extends AbstractSAMLObjectUnmarshaller {

  /** {@inheritDoc} */
  @Override
  protected void processChildElement(final XMLObject parentSAMLObject, final XMLObject childSAMLObject)
      throws UnmarshallingException {

    final MetadataLocation mdl = (MetadataLocation) parentSAMLObject;

    if (childSAMLObject instanceof Endpoint) {
      mdl.getEndpoints().add((Endpoint) childSAMLObject);
    }
    else if (childSAMLObject instanceof KeyInfo) {
      mdl.getKeyInfos().add((KeyInfo) childSAMLObject);
    }
    else {
      super.processChildElement(parentSAMLObject, childSAMLObject);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void processAttribute(final XMLObject samlObject, final Attr attribute) throws UnmarshallingException {

    final MetadataLocation mdl = (MetadataLocation) samlObject;

    if (attribute.getLocalName().equals(MetadataLocation.LOCATION_ATTR_NAME)) {
      mdl.setLocation(attribute.getValue());
    }
    else {
      this.processUnknownAttribute(mdl, attribute);
    }

  }

}
