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

import org.opensaml.core.xml.schema.impl.XSStringImpl;
import se.swedenconnect.opensaml.eidas.ext.attributes.CountryStringType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Abstract implementation class of {@link CountryStringType}.
 *
 * @author Martin Lindström
 */
public class CountryStringTypeImpl extends XSStringImpl implements CountryStringType {

  /**
   * Constructor.
   *
   * @param namespaceURI the namespace the element is in
   * @param elementLocalName the local name of the XML element this Object represents
   * @param namespacePrefix the prefix for the given namespace
   */
  protected CountryStringTypeImpl(@Nonnull final String namespaceURI, @Nonnull final String elementLocalName,
      @Nonnull final String namespacePrefix) {
    super(namespaceURI, elementLocalName, namespacePrefix);
  }

  /**
   * Assigns the value which must be a ISO 3166-1 country code.
   * <p>
   * Note: If supplied with lowercase letters, the method will replace those with uppercase.
   * </p>
   *
   * @param newValue the two-letter ISO 3166-1 country code
   * @throws IllegalArgumentException if the supplied string is not a valid country code
   */
  @Override
  public void setValue(@Nullable final String newValue) throws IllegalArgumentException {
    if (newValue == null) {
      super.setValue(null);
    }
    if (!newValue.matches("[a-zA-Z]{2}")) {
      throw new IllegalArgumentException("Invalid country code: " + newValue);
    }
    super.setValue(newValue.toUpperCase());
  }

  /** {@inheritDoc} */
  @Override
  @Nullable
  public String toStringValue() {
    return this.getValue();
  }

  /** {@inheritDoc} */
  @Override
  public void parseStringValue(@Nonnull final String value) throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(value, "value must not be null");
    this.setValue(value);
  }
}
