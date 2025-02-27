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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.opensaml.core.xml.AbstractXMLObject;
import org.opensaml.core.xml.XMLObject;
import se.swedenconnect.opensaml.eidas.ext.attributes.GenderType;
import se.swedenconnect.opensaml.eidas.ext.attributes.GenderTypeEnumeration;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of {@link GenderType}.
 *
 * @author Martin Lindström
 */
public class GenderTypeImpl extends AbstractXMLObject implements GenderType {

  /** The gender. */
  private GenderTypeEnumeration gender;

  /**
   * Constructor.
   *
   * @param namespaceURI the namespace the element is in
   * @param elementLocalName the local name of the XML element this Object represents
   * @param namespacePrefix the prefix for the given namespace
   */
  public GenderTypeImpl(final String namespaceURI, final String elementLocalName, final String namespacePrefix) {
    super(namespaceURI, elementLocalName, namespacePrefix);
  }

  /** {@inheritDoc} */
  @Override
  public void setGender(final GenderTypeEnumeration gender) {
    this.gender = this.prepareForAssignment(this.gender, gender);
  }

  /** {@inheritDoc} */
  @Override
  public GenderTypeEnumeration getGender() {
    return this.gender;
  }

  /** {@inheritDoc} */
  @Override
  public List<XMLObject> getOrderedChildren() {
    // No children for this element
    return null;
  }

  /** {@inheritDoc} */
  @Override
  @Nullable
  public String toStringValue() {
    return Optional.ofNullable(this.getGender())
        .map(GenderTypeEnumeration::getValue)
        .orElse(null);
  }

  /** {@inheritDoc} */
  @Override
  public void parseStringValue(@Nonnull final String value) throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(value, "value must not be null");
    final GenderTypeEnumeration g = GenderTypeEnumeration.fromValue(value);
    if (g == null) {
      throw new IllegalArgumentException("Invalid gender identifier: " + value);
    }
    this.setGender(g);
  }

}
