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
package se.swedenconnect.opensaml.eidas.metadata.impl;

import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.io.UnmarshallingException;
import org.opensaml.saml.common.AbstractSAMLObjectUnmarshaller;
import org.w3c.dom.Attr;

import se.swedenconnect.opensaml.eidas.metadata.Endpoint;

/**
 * Unmarshaller for {@link Endpoint}.
 *
 * @author Martin Lindström
 */
public class EndpointUnmarshaller extends AbstractSAMLObjectUnmarshaller {

  /** {@inheritDoc} */
  @Override
  protected void processAttribute(final XMLObject samlObject, final Attr attribute) throws UnmarshallingException {

    final Endpoint endpoint = (Endpoint) samlObject;

    if (attribute.getLocalName().equals(Endpoint.ENDPOINT_TYPE_ATTR_NAME)) {
      endpoint.setEndpointType(attribute.getValue());
    }
    else if (attribute.getLocalName().equals(Endpoint.ENTITY_ID_ATTR_NAME)) {
      endpoint.setEntityID(attribute.getValue());
    }
    else {
      this.processUnknownAttribute(endpoint, attribute);
    }
  }

}
