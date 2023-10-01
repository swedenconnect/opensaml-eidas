/*
 * Copyright 2016-2023 Sweden Connect
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
package se.swedenconnect.opensaml.eidas.ext;

import java.util.List;

import javax.xml.namespace.QName;

import org.opensaml.saml.common.SAMLObject;

import se.swedenconnect.opensaml.eidas.common.EidasConstants;

/**
 * The eIDAS {@code <eidas:RequestedAttributes>} element.
 *
 * <pre>{@code
 * <?xml version="1.0" encoding="UTF-8"?>
 * <xsd:schema
 *      xmlns="http://eidas.europa.eu/saml-extensions"
 *      xmlns:xsd="http://www.w3.org/2001/XMLSchema"
 *      targetNamespace="http://eidas.europa.eu/saml-extensions"
 *      elementFormDefault="qualified"
 *      attributeFormDefault="unqualified"
 *      version="1">
 *
 *   <xs:element name="RequestedAttributes" type="eidas:RequestedAttributesType" />
 *
 *   <xs:complexType name="RequestedAttributesType">
 *     <xs:sequence>
 *       <xs:element minOccurs="0" maxOccurs="unbounded" ref="eidas:RequestedAttribute"/>
 *     </xs:sequence>
 *   </xs:complexType>
 * </xsd:schema>}
 * </pre>
 *
 * @author Martin Lindström
 */
public interface RequestedAttributes extends SAMLObject {

  /** Element name, no namespace. */
  String DEFAULT_ELEMENT_LOCAL_NAME = "RequestedAttributes";

  /** Default element name. */
  QName DEFAULT_ELEMENT_NAME =
      new QName(EidasConstants.EIDAS_NS, DEFAULT_ELEMENT_LOCAL_NAME, EidasConstants.EIDAS_PREFIX);

  /** Local name of the XSI type. */
  String TYPE_LOCAL_NAME = "RequestedAttributesType";

  /** QName of the XSI type. */
  QName TYPE_NAME = new QName(EidasConstants.EIDAS_NS, TYPE_LOCAL_NAME, EidasConstants.EIDAS_PREFIX);

  /**
   * Returns a reference to the list of the requested attributes.
   *
   * @return an attribute list
   */
  List<RequestedAttribute> getRequestedAttributes();

}
