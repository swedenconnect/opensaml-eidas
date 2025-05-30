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
package se.swedenconnect.opensaml.eidas.ext.attributes.impl;

import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.io.AbstractXMLObjectUnmarshaller;

import se.swedenconnect.opensaml.eidas.ext.attributes.GenderType;
import se.swedenconnect.opensaml.eidas.ext.attributes.GenderTypeEnumeration;

import javax.annotation.Nonnull;

/**
 * Thread-safe unmarshaller for {@link GenderType} objects.
 *
 * @author Martin Lindström
 */
public class GenderTypeUnmarshaller extends AbstractXMLObjectUnmarshaller {

  /** {@inheritDoc} */
  @Override
  protected void processElementContent(@Nonnull final XMLObject xmlObject, @Nonnull final String elementContent) {
    final GenderType gender = (GenderType) xmlObject;
    gender.setGender(new GenderTypeEnumeration(elementContent));
  }

}
