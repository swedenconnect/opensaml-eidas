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
package se.swedenconnect.opensaml.eidas.ext.attributes.address.impl;

import org.opensaml.core.xml.schema.impl.XSStringImpl;

import se.swedenconnect.opensaml.eidas.ext.attributes.address.AdminunitSecondline;

/**
 * Implementation of {@code AdminunitSecondline}.
 *
 * @author Martin Lindström
 */
public class AdminunitSecondlineImpl extends XSStringImpl implements AdminunitSecondline {

  /**
   * Constructor.
   *
   * @param namespaceURI the namespace the element is in
   * @param elementLocalName the local name of the XML element this Object represents
   * @param namespacePrefix the prefix for the given namespace
   */
  public AdminunitSecondlineImpl(
      final String namespaceURI, final String elementLocalName, final String namespacePrefix) {
    super(namespaceURI, elementLocalName, namespacePrefix);
  }

}
