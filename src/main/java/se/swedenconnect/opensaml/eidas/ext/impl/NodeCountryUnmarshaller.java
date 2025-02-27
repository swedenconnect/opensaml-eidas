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
package se.swedenconnect.opensaml.eidas.ext.impl;

import org.opensaml.core.xml.XMLObject;
import org.opensaml.saml.common.AbstractSAMLObjectUnmarshaller;

import se.swedenconnect.opensaml.eidas.ext.NodeCountry;

import javax.annotation.Nonnull;

/**
 * A thread-safe Unmarshaller for {@link NodeCountry} objects.
 *
 * @author Martin Lindström
 */
public class NodeCountryUnmarshaller extends AbstractSAMLObjectUnmarshaller {

  /** {@inheritDoc} */
  @Override
  protected void processElementContent(@Nonnull final XMLObject samlObject, @Nonnull final String elementContent) {
    final NodeCountry nodeCountry = (NodeCountry) samlObject;
    nodeCountry.setNodeCountry(elementContent);
  }

}
