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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * The bad decision of using dedicated XML types for each and every eIDAS attribute makes it hard for profiles that want
 * to convert eIDAS attributes to their own definitions. This interface makes it somewhat easier to transform to and
 * from string valued attributes.
 *
 * @author Martin Lindström
 */
public interface EidasAttributeValueType {

  /**
   * Gets the simple string value of the eIDAS attribute.
   *
   * @return string value of the attribute
   */
  @Nullable
  String toStringValue();

  /**
   * Parses the supplied string value into an eIDAS attribute.
   *
   * @param value the value
   * @throws IllegalArgumentException for parsing errors
   * @throws NullPointerException if the value is null
   * @throws IllegalArgumentException if the value can not be parsed
   */
  void parseStringValue(@Nonnull final String value) throws IllegalArgumentException, NullPointerException;

}
