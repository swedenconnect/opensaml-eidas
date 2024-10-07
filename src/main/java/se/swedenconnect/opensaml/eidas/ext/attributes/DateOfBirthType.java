/*
 * Copyright 2016-2024 Sweden Connect
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

import java.time.LocalDate;

import javax.xml.namespace.QName;

import org.opensaml.core.xml.XMLObject;

import se.swedenconnect.opensaml.eidas.common.EidasConstants;

/**
 * The eIDAS {@code DateOfBirthType}.
 *
 * <pre>{@code
 * <xsd:simpleType name="DateOfBirthType">
 *   <xsd:annotation>
 *     <xsd:documentation>
 *       Date of Birth for the Natural Person (Based on xsd:date i.e. YYYY-MM-DD format).
 *     </xsd:documentation>
 *   </xsd:annotation>
 *   <xsd:restriction base="xsd:date"/>
 * </xsd:simpleType>}
 * </pre>
 *
 * Example:
 *
 * <pre>{@code
 * <saml:Attribute
 *       FriendlyName="DateOfBirth"
 *       Name="http://eidas.europa.eu/attributes/naturalperson/DateOfBirth"
 *       NameFormat="urn:oasis:names:tc:SAML:2.0:attrname-format:uri">
 *   <saml:AttributeValue xsi:type="eidas:DateOfBirthType">
 *     1970-05-28
 *   </saml:AttributeValue>
 * </saml:Attribute>}
 * </pre>
 *
 * @author Martin Lindström
 */
public interface DateOfBirthType extends XMLObject, EidasAttributeValueType {

  /** Local name of the XSI type. */
  String TYPE_LOCAL_NAME = "DateOfBirthType";

  /** QName of the XSI type. */
  QName TYPE_NAME = new QName(EidasConstants.EIDAS_NP_NS, TYPE_LOCAL_NAME, EidasConstants.EIDAS_NP_PREFIX);

  /**
   * Assigns the date.
   *
   * @param date the date to assign
   */
  void setDate(final LocalDate date);

  /**
   * Assigns the date by giving the year, month and day of month.
   *
   * @param year the year (four digits)
   * @param month the month (1-12)
   * @param dayOfMonth the day of month (1-31)
   */
  void setDate(final int year, final int month, final int dayOfMonth);

  /**
   * Returns the birthdate.
   *
   * @return the date
   */
  LocalDate getDate();

  /**
   * Formats the birthdate according to the formatting rules of xsd:date (YYYY-MM-DD).
   *
   * @return a formatted date string
   */
  String formatDate();

}
