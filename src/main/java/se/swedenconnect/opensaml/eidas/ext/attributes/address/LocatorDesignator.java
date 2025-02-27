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
package se.swedenconnect.opensaml.eidas.ext.attributes.address;

import javax.xml.namespace.QName;

import org.opensaml.core.xml.schema.XSString;

import se.swedenconnect.opensaml.eidas.common.EidasConstants;

/**
 * The {@code LocatorDesignator} element of the {@code CurrentAddressStructuredType}.
 *
 * @author Martin Lindström
 */
public interface LocatorDesignator extends XSString {

  /** Element local name. */
  String DEFAULT_ELEMENT_LOCAL_NAME = "LocatorDesignator";

  /** Default element name. */
  QName DEFAULT_ELEMENT_NAME =
      new QName(EidasConstants.EIDAS_NP_NS, DEFAULT_ELEMENT_LOCAL_NAME, EidasConstants.EIDAS_NP_PREFIX);

}
