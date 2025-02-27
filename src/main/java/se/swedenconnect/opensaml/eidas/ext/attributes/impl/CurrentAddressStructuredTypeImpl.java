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
package se.swedenconnect.opensaml.eidas.ext.attributes.impl;

import org.opensaml.core.xml.AbstractXMLObject;
import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.XMLObjectBuilder;
import org.opensaml.core.xml.config.XMLObjectProviderRegistrySupport;
import org.opensaml.core.xml.schema.XSString;
import se.swedenconnect.opensaml.eidas.ext.attributes.CurrentAddressStructuredType;
import se.swedenconnect.opensaml.eidas.ext.attributes.address.AdminunitFirstline;
import se.swedenconnect.opensaml.eidas.ext.attributes.address.AdminunitSecondline;
import se.swedenconnect.opensaml.eidas.ext.attributes.address.CvaddressArea;
import se.swedenconnect.opensaml.eidas.ext.attributes.address.LocatorDesignator;
import se.swedenconnect.opensaml.eidas.ext.attributes.address.LocatorName;
import se.swedenconnect.opensaml.eidas.ext.attributes.address.PoBox;
import se.swedenconnect.opensaml.eidas.ext.attributes.address.PostCode;
import se.swedenconnect.opensaml.eidas.ext.attributes.address.PostName;
import se.swedenconnect.opensaml.eidas.ext.attributes.address.Thoroughfare;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of {@code CurrentAddressStructuredType}.
 *
 * @author Martin Lindström
 */
public class CurrentAddressStructuredTypeImpl extends AbstractXMLObject implements CurrentAddressStructuredType {

  /** PoBox */
  private PoBox poBox;

  /** LocatorDesignator */
  private LocatorDesignator locatorDesignator;

  /** LocatorName */
  private LocatorName locatorName;

  /** CvaddressArea */
  private CvaddressArea cvaddressArea;

  /** Thoroughfare */
  private Thoroughfare thoroughfare;

  /** PostName */
  private PostName postName;

  /** AdminunitFirstline */
  private AdminunitFirstline adminunitFirstline;

  /** AdminunitSecondline */
  private AdminunitSecondline adminunitSecondline;

  /** PostCode */
  private PostCode postCode;

  /**
   * Constructor.
   *
   * @param namespaceURI the namespace the element is in
   * @param elementLocalName the local name of the XML element this Object represents
   * @param namespacePrefix the prefix for the given namespace
   */
  public CurrentAddressStructuredTypeImpl(
      final String namespaceURI, final String elementLocalName, final String namespacePrefix) {
    super(namespaceURI, elementLocalName, namespacePrefix);
  }

  /** {@inheritDoc} */
  @Override
  public List<XMLObject> getOrderedChildren() {
    final ArrayList<XMLObject> children = new ArrayList<>();

    if (this.poBox != null) {
      children.add(this.poBox);
    }
    if (this.locatorDesignator != null) {
      children.add(this.locatorDesignator);
    }
    if (this.locatorName != null) {
      children.add(this.locatorName);
    }
    if (this.cvaddressArea != null) {
      children.add(this.cvaddressArea);
    }
    if (this.thoroughfare != null) {
      children.add(this.thoroughfare);
    }
    if (this.postName != null) {
      children.add(this.postName);
    }
    if (this.adminunitFirstline != null) {
      children.add(this.adminunitFirstline);
    }
    if (this.adminunitSecondline != null) {
      children.add(this.adminunitSecondline);
    }
    if (this.postCode != null) {
      children.add(this.postCode);
    }

    return Collections.unmodifiableList(children);
  }

  /** {@inheritDoc} */
  @Override
  public void setPoBox(final String poBox) {
    this.poBox = this.prepareForAssignment(this.poBox, this.createXSString(PoBox.class, poBox));
  }

  /** {@inheritDoc} */
  @Override
  public String getPoBox() {
    return this.poBox != null ? this.poBox.getValue() : null;
  }

  /** {@inheritDoc} */
  @Override
  public void setLocatorDesignator(final String locatorDesignator) {
    this.locatorDesignator =
        this.prepareForAssignment(this.locatorDesignator, this.createXSString(LocatorDesignator.class,
            locatorDesignator));
  }

  /** {@inheritDoc} */
  @Override
  public String getLocatorDesignator() {
    return this.locatorDesignator != null ? this.locatorDesignator.getValue() : null;
  }

  /** {@inheritDoc} */
  @Override
  public void setLocatorName(final String locatorName) {
    this.locatorName = this.prepareForAssignment(this.locatorName, this.createXSString(LocatorName.class, locatorName));
  }

  /** {@inheritDoc} */
  @Override
  public String getLocatorName() {
    return this.locatorName != null ? this.locatorName.getValue() : null;
  }

  /** {@inheritDoc} */
  @Override
  public void setCvaddressArea(final String cvaddressArea) {
    this.cvaddressArea =
        this.prepareForAssignment(this.cvaddressArea, this.createXSString(CvaddressArea.class, cvaddressArea));
  }

  /** {@inheritDoc} */
  @Override
  public String getCvaddressArea() {
    return this.cvaddressArea != null ? this.cvaddressArea.getValue() : null;
  }

  /** {@inheritDoc} */
  @Override
  public void setThoroughfare(final String thoroughfare) {
    this.thoroughfare =
        this.prepareForAssignment(this.thoroughfare, this.createXSString(Thoroughfare.class, thoroughfare));
  }

  /** {@inheritDoc} */
  @Override
  public String getThoroughfare() {
    return this.thoroughfare != null ? this.thoroughfare.getValue() : null;
  }

  /** {@inheritDoc} */
  @Override
  public void setPostName(final String postName) {
    this.postName = this.prepareForAssignment(this.postName, this.createXSString(PostName.class, postName));
  }

  /** {@inheritDoc} */
  @Override
  public String getPostName() {
    return this.postName != null ? this.postName.getValue() : null;
  }

  /** {@inheritDoc} */
  @Override
  public void setAdminunitFirstline(final String adminunitFirstline) {
    this.adminunitFirstline =
        this.prepareForAssignment(this.adminunitFirstline, this.createXSString(AdminunitFirstline.class,
            adminunitFirstline));
  }

  /** {@inheritDoc} */
  @Override
  public String getAdminunitFirstline() {
    return this.adminunitFirstline != null ? this.adminunitFirstline.getValue() : null;
  }

  /** {@inheritDoc} */
  @Override
  public void setAdminunitSecondline(final String adminunitSecondline) {
    this.adminunitSecondline =
        this.prepareForAssignment(this.adminunitSecondline, this.createXSString(AdminunitSecondline.class,
            adminunitSecondline));
  }

  /** {@inheritDoc} */
  @Override
  public String getAdminunitSecondline() {
    return this.adminunitSecondline != null ? this.adminunitSecondline.getValue() : null;
  }

  /** {@inheritDoc} */
  @Override
  public void setPostCode(final String postCode) {
    this.postCode = this.prepareForAssignment(this.postCode, this.createXSString(PostCode.class, postCode));
  }

  /** {@inheritDoc} */
  @Override
  public String getPostCode() {
    return this.postCode != null ? this.postCode.getValue() : null;
  }

  /**
   * Utility method for creating an OpenSAML object given its type and assigns the value.
   *
   * @param clazz the class to create
   * @param value the string value to assign
   * @return the XML object or {@code null} if value is {@code null}
   */
  private <T extends XSString> T createXSString(final Class<T> clazz, final String value) {
    if (value == null) {
      return null;
    }
    final QName elementName;
    final String localName;
    try {
      elementName = (QName) clazz.getDeclaredField("DEFAULT_ELEMENT_NAME").get(null);
      localName = (String) clazz.getDeclaredField("DEFAULT_ELEMENT_LOCAL_NAME").get(null);
    }
    catch (final NoSuchFieldException | IllegalArgumentException | IllegalAccessException | SecurityException e) {
      throw new RuntimeException(e);
    }
    final XMLObjectBuilder<? extends XMLObject> builder =
        XMLObjectProviderRegistrySupport.getBuilderFactory().getBuilder(elementName);
    final Object object = builder.buildObject(
        new QName(this.getElementQName().getNamespaceURI(), localName, this.getElementQName().getPrefix()));
    final T xsstring = clazz.cast(object);
    xsstring.setValue(value);
    return xsstring;
  }

}
