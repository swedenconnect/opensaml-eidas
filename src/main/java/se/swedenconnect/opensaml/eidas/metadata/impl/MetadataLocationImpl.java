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

import net.shibboleth.shared.codec.Base64Support;
import net.shibboleth.shared.codec.EncodingException;
import org.opensaml.core.xml.AbstractXMLObject;
import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.schema.XSBooleanValue;
import org.opensaml.core.xml.util.AttributeMap;
import org.opensaml.core.xml.util.XMLObjectChildrenList;
import org.opensaml.xmlsec.signature.KeyInfo;
import org.opensaml.xmlsec.signature.X509Data;
import org.opensaml.xmlsec.signature.impl.KeyInfoBuilder;
import org.opensaml.xmlsec.signature.impl.X509CertificateBuilder;
import org.opensaml.xmlsec.signature.impl.X509DataBuilder;
import se.swedenconnect.opensaml.eidas.metadata.Endpoint;
import se.swedenconnect.opensaml.eidas.metadata.MetadataLocation;

import javax.annotation.Nonnull;
import javax.xml.namespace.QName;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation class for {@link MetadataLocation}.
 *
 * @author Martin Lindström
 */
public class MetadataLocationImpl extends AbstractXMLObject implements MetadataLocation {

  /** Endpoint children. */
  private final XMLObjectChildrenList<Endpoint> endpoints;

  /** Key infos (certificates). */
  private final XMLObjectChildrenList<KeyInfo> keyInfos;

  /** The location attribute. */
  private String location;

  /** "anyAttribute" attributes */
  private final AttributeMap unknownAttributes;

  private static final QName suspendQname = new QName("Suspend");

  /**
   * Constructor.
   *
   * @param namespaceURI the namespace the element is in
   * @param elementLocalName the local name of the XML element this Object represents
   * @param namespacePrefix the prefix for the given namespace
   */
  public MetadataLocationImpl(final String namespaceURI, final String elementLocalName, final String namespacePrefix) {
    super(namespaceURI, elementLocalName, namespacePrefix);
    this.endpoints = new XMLObjectChildrenList<>(this);
    this.keyInfos = new XMLObjectChildrenList<>(this);
    this.unknownAttributes = new AttributeMap(this);
  }

  /** {@inheritDoc} */
  @Override
  public List<XMLObject> getOrderedChildren() {
    final ArrayList<XMLObject> children = new ArrayList<>();
    children.addAll(this.endpoints);
    children.addAll(this.keyInfos);
    return Collections.unmodifiableList(children);
  }

  /** {@inheritDoc} */
  @Override
  public List<Endpoint> getEndpoints() {
    return this.endpoints;
  }

  /** {@inheritDoc} */
  @Override
  public List<KeyInfo> getKeyInfos() {
    return this.keyInfos;
  }

  /** {@inheritDoc} */
  @Override
  public void addX509Certificate(final X509Certificate certificate) {

    final String encoding;
    try {
      encoding = Base64Support.encode(certificate.getEncoded(), true);
    }
    catch (final CertificateEncodingException | EncodingException e) {
      throw new SecurityException("Failed to get certificate encoding", e);
    }
    final org.opensaml.xmlsec.signature.X509Certificate cert = new X509CertificateBuilder().buildObject();
    cert.setValue(encoding);

    final X509Data x509data = new X509DataBuilder().buildObject();
    x509data.getX509Certificates().add(cert);

    final KeyInfo keyInfo = new KeyInfoBuilder().buildObject();
    keyInfo.getX509Datas().add(x509data);

    this.getKeyInfos().add(keyInfo);
  }

  /** {@inheritDoc} */
  @Override
  public String getLocation() {
    return this.location;
  }

  /** {@inheritDoc} */
  @Override
  public void setLocation(final String location) {
    this.location = this.prepareForAssignment(this.location, location);
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

  @Nonnull
  @Override
  public AttributeMap getUnknownAttributes() {
    return this.unknownAttributes;
  }

}
