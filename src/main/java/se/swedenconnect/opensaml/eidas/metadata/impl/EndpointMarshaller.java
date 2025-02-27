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
import se.swedenconnect.opensaml.eidas.metadata.Endpoint;

import javax.annotation.Nonnull;

/**
 * A marshaller for {@link Endpoint}.
 *
 * @author Martin Lindström
 */
public class EndpointMarshaller extends AbstractSAMLObjectMarshaller {

  /** {@inheritDoc} */
  @Override
  protected void marshallAttributes(@Nonnull final XMLObject xmlObject, @Nonnull final Element domElement)
      throws MarshallingException {

    final Endpoint endpoint = (Endpoint) xmlObject;

    if (endpoint.getEndpointType() != null) {
      domElement.setAttributeNS(null, Endpoint.ENDPOINT_TYPE_ATTR_NAME, endpoint.getEndpointType());
    }
    if (endpoint.getEntityID() != null) {
      domElement.setAttributeNS(null, Endpoint.ENTITY_ID_ATTR_NAME, endpoint.getEntityID());
    }
    this.marshallUnknownAttributes(endpoint, domElement);
  }

}
