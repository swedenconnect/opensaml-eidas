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

import se.swedenconnect.opensaml.eidas.common.EidasConstants;

import javax.xml.namespace.QName;

/**
 * The eIDAS {@code CountryOfBirth} type.
 *
 * <pre>{@code
 * <xsd:simpleType name="CountryOfResidenceType">
 *   <xsd:annotation>
 *     <xsd:documentation>
 *       Country of residence of the natural person (Two-letter country codes according
 *       to ISO 3166-1 standard).
 *     </xsd:documentation>
 *   </xsd:annotation>
 *   <xsd:restriction base="xsd:string">
 *     <xsd:pattern value="[A-Z][A-Z]"/>
 *   </xsd:restriction>
 * </xsd:simpleType>}
 * </pre>
 *
 * @author Martin Lindström
 */
public interface CountryOfResidenceType extends CountryStringType {

  /** Local name of the XSI type. */
  String TYPE_LOCAL_NAME = "CountryOfResidenceType";

  /** QName of the XSI type. */
  QName TYPE_NAME = new QName(EidasConstants.EIDAS_NP_NS, TYPE_LOCAL_NAME, EidasConstants.EIDAS_NP_PREFIX);

}
