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
package se.swedenconnect.opensaml.eidas.metadata;

import java.util.List;

import javax.xml.namespace.QName;

import org.opensaml.saml.common.SAMLObject;

import se.swedenconnect.opensaml.eidas.common.EidasConstants;

/**
 * The {@code <ser:DistributionPoints>} element.
 *
 * <pre>{@code
 * <xs:complexType name="DistributionPointsType">
 *   <xs:annotation>
 *     <xs:documentation>
 *       A list of distribution points. URLs from where the metadata service list can be downloaded.
 *     </xs:documentation>
 *   </xs:annotation>
 *   <xs:sequence>
 *     <xs:element name="DistributionPoint" type="xs:anyURI" minOccurs="1" maxOccurs="unbounded" />
 *   </xs:sequence>
 * </xs:complexType>}
 * </pre>
 *
 * @author Martin Lindström
 */
public interface DistributionPoints extends SAMLObject {

  /** Name of the element. */
  String DEFAULT_ELEMENT_LOCAL_NAME = "DistributionPoints";

  /** Default element name. */
  QName DEFAULT_ELEMENT_NAME = new QName(EidasConstants.EIDAS_SERVICELIST_NS, DEFAULT_ELEMENT_LOCAL_NAME,
      EidasConstants.EIDAS_SERVICELIST_PREFIX);

  /** Local name of the XSI type. */
  String TYPE_LOCAL_NAME = "DistributionPointsType";

  /** QName of the XSI type. */
  QName TYPE_NAME = new QName(EidasConstants.EIDAS_SERVICELIST_NS, TYPE_LOCAL_NAME,
      EidasConstants.EIDAS_SERVICELIST_PREFIX);

  /** Name of the DistributionPoint elements. */
  String DISTRIBUTION_POINT_LOCAL_NAME = "DistributionPoint";

  /**
   * Returns a reference to the list of distribution points.
   *
   * @return a distrubution point list
   */
  List<DistributionPoint> getDistributionPoints();

}
