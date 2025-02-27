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
import org.opensaml.core.xml.schema.impl.XSStringImpl;

import se.swedenconnect.opensaml.eidas.ext.attributes.PersonIdentifierType;

import java.util.Objects;

/**
 * Implementation of the {@link PersonIdentifierType} interface.
 *
 * @author Martin Lindström
 */
public class PersonIdentifierTypeImpl extends XSStringImpl implements PersonIdentifierType {

  /**
   * Constructor.
   *
   * @param namespaceURI the namespace the element is in
   * @param elementLocalName the local name of the XML element this Object represents
   * @param namespacePrefix the prefix for the given namespace
   */
  public PersonIdentifierTypeImpl(
      final String namespaceURI, final String elementLocalName, final String namespacePrefix) {
    super(namespaceURI, elementLocalName, namespacePrefix);
  }

  /** {@inheritDoc} */
  @Override
  public String getNationalityCode() {
    return this.getPart(0);
  }

  /** {@inheritDoc} */
  @Override
  public String getDestinationNationalityCode() {
    return this.getPart(1);
  }

  /** {@inheritDoc} */
  @Override
  public String getIdentifierString() {
    return this.getPart(2);
  }

  /**
   * Extracts the given part from the unique identifier.
   *
   * @param pos position
   * @return the part or {@code null}
   */
  private String getPart(final int pos) {
    final String value = this.getValue();
    if (value == null) {
      return null;
    }
    final String[] parts = value.split("/");
    return parts.length >= pos + 1 ? parts[pos] : null;
  }

  /** {@inheritDoc} */
  @Override
  @Nullable
  public String toStringValue() {
    return this.getValue();
  }

  /** {@inheritDoc} */
  @Override
  public void parseStringValue(@Nonnull final String value) throws NullPointerException {
    Objects.requireNonNull(value, "value must not be null");
    this.setValue(value);
  }

}
