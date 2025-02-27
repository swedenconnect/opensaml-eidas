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
package se.swedenconnect.opensaml.eidas.common;

/**
 * Enumeration representing an eIDAS Level of Assurance.
 *
 * @author Martin Lindström
 */
public enum EidasLoaEnum {

  /** eIDAS "low" Level of assurance. For non-notified eID scheme. */
  LOA_LOW_NON_NOTIFIED(EidasConstants.EIDAS_LOA_LOW_NON_NOTIFIED, 1),

  /** eIDAS "low" Level of assurance. */
  LOA_LOW(EidasConstants.EIDAS_LOA_LOW, 2),

  /** eIDAS "substantial" Level of assurance. For non-notified eID scheme. */
  LOA_SUBSTANTIAL_NON_NOTIFIED(EidasConstants.EIDAS_LOA_SUBSTANTIAL_NON_NOTIFIED, 3),

  /** eIDAS "substantial" Level of assurance. */
  LOA_SUBSTANTIAL(EidasConstants.EIDAS_LOA_SUBSTANTIAL, 4),

  /** eIDAS "high" Level of assurance. For non-notified eID scheme. */
  LOA_HIGH_NON_NOTIFIED(EidasConstants.EIDAS_LOA_HIGH_NON_NOTIFIED, 5),

  /** eIDAS "high" Level of assurance. */
  LOA_HIGH(EidasConstants.EIDAS_LOA_HIGH, 6);

  /**
   * Returns the URI for this LoA.
   *
   * @return an URI
   */
  public String getUri() {
    return this.loaUri;
  }

  /**
   * Returns the sorting order index.
   *
   * @return the sorting order (the higher the strength, the higher the value)
   */
  public int getOrder() {
    return this.order;
  }

  /**
   * Given a URI the method returns the enum value matching.
   *
   * @param uri URI
   * @return the matching enum value or {@code null} is no match is found
   */
  public static EidasLoaEnum parse(final String uri) {
    if (EidasConstants.EIDAS_LOA_LOW_NON_NOTIFIED.equals(uri)
        || EidasConstants.EIDAS_LOA_LOW_NON_NOTIFIED2.equals(uri)) {
      return EidasLoaEnum.LOA_LOW_NON_NOTIFIED;
    }
    else if (EidasConstants.EIDAS_LOA_SUBSTANTIAL_NON_NOTIFIED.equals(uri)
        || EidasConstants.EIDAS_LOA_SUBSTANTIAL_NON_NOTIFIED2.equals(
        uri)) {
      return EidasLoaEnum.LOA_SUBSTANTIAL_NON_NOTIFIED;
    }
    else if (EidasConstants.EIDAS_LOA_HIGH_NON_NOTIFIED.equals(uri)
        || EidasConstants.EIDAS_LOA_HIGH_NON_NOTIFIED2.equals(uri)) {
      return EidasLoaEnum.LOA_HIGH_NON_NOTIFIED;
    }
    else {
      for (final EidasLoaEnum loa : EidasLoaEnum.values()) {
        if (loa.getUri().equals(uri)) {
          return loa;
        }
      }
    }
    return null;
  }

  /**
   * Constructor setting the LoA URI.
   *
   * @param uri the LoA URI
   * @param order the sorting order
   */
  EidasLoaEnum(final String uri, final int order) {
    this.loaUri = uri;
    this.order = order;
  }

  /** The LoA URI. */
  private final String loaUri;

  /** Sorting order. */
  private final int order;

}
