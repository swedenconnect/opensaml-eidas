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
package se.swedenconnect.opensaml.eidas.ext.attributes;

/**
 * Enumeration of the {@code <eidas:GenderType>} type.
 *
 * @author Martin Lindström
 */
public final class GenderTypeEnumeration {

  /** Male. */
  public static final GenderTypeEnumeration MALE = new GenderTypeEnumeration("Male");

  /** Female. */
  public static final GenderTypeEnumeration FEMALE = new GenderTypeEnumeration("Female");

  /** Unspecified. */
  public static final GenderTypeEnumeration UNSPECIFIED = new GenderTypeEnumeration("Unspecified");

  /** The gender. */
  private final String gender;

  /**
   * Constructor.
   *
   * @param gender the gender
   */
  public GenderTypeEnumeration(final String gender) {
    this.gender = gender;
  }

  /**
   * Returns the gender value.
   *
   * @return the value
   */
  public String getValue() {
    return this.gender;
  }

  /**
   * Parses a string into a {@code GenderTypeEnumeration} object.
   *
   * @param value the value to parse
   * @return a {@code GenderTypeEnumeration} object
   */
  public static GenderTypeEnumeration fromValue(final String value) {
    if (MALE.getValue().equalsIgnoreCase(value)) {
      return MALE;
    }
    else if (FEMALE.getValue().equalsIgnoreCase(value)) {
      return FEMALE;
    }
    else if (UNSPECIFIED.getValue().equalsIgnoreCase(value)) {
      return UNSPECIFIED;
    }
    else {
      return null;
    }
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return this.gender;
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (this.gender == null ? 0 : this.gender.hashCode());
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if ((obj == null) || (this.getClass() != obj.getClass())) {
      return false;
    }
    final GenderTypeEnumeration other = (GenderTypeEnumeration) obj;
    if (this.gender == null) {
      return other.gender == null;
    }
    else {
      return this.gender.equals(other.gender);
    }
  }

}
