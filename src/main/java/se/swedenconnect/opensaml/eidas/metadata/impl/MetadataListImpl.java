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
package se.swedenconnect.opensaml.eidas.metadata.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.xml.namespace.QName;

import org.opensaml.core.xml.AbstractXMLObject;
import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.schema.XSBooleanValue;
import org.opensaml.core.xml.util.AttributeMap;
import org.opensaml.core.xml.util.XMLObjectChildrenList;

import se.swedenconnect.opensaml.eidas.metadata.MetadataList;
import se.swedenconnect.opensaml.eidas.metadata.MetadataLocation;

/**
 * Implementation class for {@link MetadataList}.
 *
 * @author Martin Lindström
 */
public class MetadataListImpl extends AbstractXMLObject implements MetadataList {

  /** Metadata location children. */
  private final XMLObjectChildrenList<MetadataLocation> metadataLocations;

  /** "anyAttribute" attributes */
  private final AttributeMap unknownAttributes;

  /** The territory attribute. */
  private String territory;

  private static final QName suspendQname = new QName("Suspend");

  /**
   * Constructor.
   *
   * @param namespaceURI the namespace the element is in
   * @param elementLocalName the local name of the XML element this Object represents
   * @param namespacePrefix the prefix for the given namespace
   */
  protected MetadataListImpl(final String namespaceURI, final String elementLocalName, final String namespacePrefix) {
    super(namespaceURI, elementLocalName, namespacePrefix);
    this.metadataLocations = new XMLObjectChildrenList<>(this);
    this.unknownAttributes = new AttributeMap(this);
  }

  /** {@inheritDoc} */
  @Override
  public List<XMLObject> getOrderedChildren() {
    final ArrayList<XMLObject> children = new ArrayList<>(this.metadataLocations);
    return Collections.unmodifiableList(children);
  }

  /** {@inheritDoc} */
  @Nonnull
  @Override
  public AttributeMap getUnknownAttributes() {
    return this.unknownAttributes;
  }

  /** {@inheritDoc} */
  @Override
  public List<MetadataLocation> getMetadataLocations() {
    return this.metadataLocations;
  }

  /** {@inheritDoc} */
  @Override
  public String getTerritory() {
    return this.territory;
  }

  /** {@inheritDoc} */
  @Override
  public void setTerritory(final String territory) {
    this.territory = this.prepareForAssignment(this.territory, territory);
  }

  /** {@inheritDoc} */
  @Override
  public boolean getSuspend() {
    final String v = this.unknownAttributes.getOrDefault(suspendQname, XSBooleanValue.toString(false, false));
    return XSBooleanValue.valueOf(v).getValue();
  }

  /** {@inheritDoc} */
  @Override
  public void setSuspend(final boolean suspendFlag) {
    this.unknownAttributes.put(suspendQname, XSBooleanValue.toString(suspendFlag, false));
  }

}
