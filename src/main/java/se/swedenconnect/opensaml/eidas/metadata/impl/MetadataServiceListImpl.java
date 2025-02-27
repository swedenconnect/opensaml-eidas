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
package se.swedenconnect.opensaml.eidas.metadata.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.util.XMLObjectChildrenList;
import org.opensaml.saml.common.AbstractSignableSAMLObject;

import se.swedenconnect.opensaml.eidas.metadata.DistributionPoints;
import se.swedenconnect.opensaml.eidas.metadata.MetadataList;
import se.swedenconnect.opensaml.eidas.metadata.MetadataServiceList;
import se.swedenconnect.opensaml.eidas.metadata.MetadataServiceListVersion;
import se.swedenconnect.opensaml.eidas.metadata.SchemeInformation;

/**
 * Implementation class for {@link MetadataList}.
 *
 * @author Martin Lindström
 */
public class MetadataServiceListImpl extends AbstractSignableSAMLObject implements MetadataServiceList {

  /** ID of the list. */
  private String id;

  /** The version attribute. */
  private MetadataServiceListVersion version;

  /** The issue date attribute. */
  private Instant issueDate;

  /** The next update attribute. */
  private Instant nextUpdate;

  /** The SchemeInformation element. */
  private SchemeInformation schemeInformation;

  /** MetadataList children. */
  private final XMLObjectChildrenList<MetadataList> metadataLists;

  /** The DistributionPoints element. */
  private DistributionPoints distributionPoints;

  /**
   * Constructor.
   *
   * @param namespaceURI the namespace the element is in
   * @param elementLocalName the local name of the XML element this Object represents
   * @param namespacePrefix the prefix for the given namespace
   */
  protected MetadataServiceListImpl(
      final String namespaceURI, final String elementLocalName, final String namespacePrefix) {
    super(namespaceURI, elementLocalName, namespacePrefix);
    this.version = MetadataServiceListVersion.VERSION_10;
    this.metadataLists = new XMLObjectChildrenList<>(this);
  }

  /** {@inheritDoc} */
  @Override
  public String getSignatureReferenceID() {
    return this.id;
  }

  /** {@inheritDoc} */
  @Override
  public List<XMLObject> getOrderedChildren() {
    final ArrayList<XMLObject> children = new ArrayList<>();
    if (this.schemeInformation != null) {
      children.add(this.schemeInformation);
    }
    children.addAll(this.metadataLists);
    if (this.distributionPoints != null) {
      children.add(this.distributionPoints);
    }
    if (this.getSignature() != null) {
      children.add(this.getSignature());
    }
    return Collections.unmodifiableList(children);
  }

  /** {@inheritDoc} */
  @Override
  public MetadataServiceListVersion getVersion() {
    return this.version;
  }

  /** {@inheritDoc} */
  @Override
  public void setVersion(final MetadataServiceListVersion version) {
    this.version = this.prepareForAssignment(this.version, version);
  }

  /** {@inheritDoc} */
  @Override
  public String getID() {
    return this.id;
  }

  /** {@inheritDoc} */
  @Override
  public void setID(final String id) {
    final String oldID = this.id;
    this.id = this.prepareForAssignment(this.id, id);
    this.registerOwnID(oldID, this.id);
  }

  /** {@inheritDoc} */
  @Override
  public Instant getIssueDate() {
    return this.issueDate;
  }

  /** {@inheritDoc} */
  @Override
  public void setIssueDate(final Instant issueDate) {
    this.issueDate = this.prepareForAssignment(this.issueDate, issueDate);
  }

  /** {@inheritDoc} */
  @Override
  public Instant getNextUpdate() {
    return this.nextUpdate;
  }

  /** {@inheritDoc} */
  @Override
  public void setNextUpdate(final Instant nextUpdate) {
    this.nextUpdate = this.prepareForAssignment(this.nextUpdate, nextUpdate);
  }

  /** {@inheritDoc} */
  @Override
  public SchemeInformation getSchemeInformation() {
    return this.schemeInformation;
  }

  /** {@inheritDoc} */
  @Override
  public void setSchemeInformation(final SchemeInformation schemeInformation) {
    this.schemeInformation = this.prepareForAssignment(this.schemeInformation, schemeInformation);
  }

  /** {@inheritDoc} */
  @Override
  public List<MetadataList> getMetadataLists() {
    return this.metadataLists;
  }

  /** {@inheritDoc} */
  @Override
  public DistributionPoints getDistributionPoints() {
    return this.distributionPoints;
  }

  /** {@inheritDoc} */
  @Override
  public void setDistributionPoints(final DistributionPoints distributionPoints) {
    this.distributionPoints = this.prepareForAssignment(this.distributionPoints, distributionPoints);
  }

}
