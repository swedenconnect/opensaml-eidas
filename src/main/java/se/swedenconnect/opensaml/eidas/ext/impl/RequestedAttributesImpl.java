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
package se.swedenconnect.opensaml.eidas.ext.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.opensaml.core.xml.AbstractXMLObject;
import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.util.XMLObjectChildrenList;

import se.swedenconnect.opensaml.eidas.ext.RequestedAttribute;
import se.swedenconnect.opensaml.eidas.ext.RequestedAttributes;

/**
 * Implementation of {@link RequestedAttributes}.
 *
 * @author Martin Lindström
 */
public class RequestedAttributesImpl extends AbstractXMLObject implements RequestedAttributes {

  /** RequestedAttribute children. */
  private final XMLObjectChildrenList<RequestedAttribute> requestedAttributes;

  /**
   * Constructor.
   *
   * @param namespaceURI name space
   * @param elementLocalName local name
   * @param namespacePrefix prefix
   */
  protected RequestedAttributesImpl(
      final String namespaceURI, final String elementLocalName, final String namespacePrefix) {
    super(namespaceURI, elementLocalName, namespacePrefix);
    this.requestedAttributes = new XMLObjectChildrenList<>(this);
  }

  /** {@inheritDoc} */
  @Override
  public List<XMLObject> getOrderedChildren() {
    final ArrayList<XMLObject> children = new ArrayList<>();
    children.addAll(this.requestedAttributes);
    return Collections.unmodifiableList(children);
  }

  /** {@inheritDoc} */
  @Override
  public List<RequestedAttribute> getRequestedAttributes() {
    return this.requestedAttributes;
  }

}
