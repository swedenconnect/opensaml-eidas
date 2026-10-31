/*
 * Copyright 2016-2026 Sweden Connect
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

import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.schema.XSString;
import se.swedenconnect.opensaml.eidas.ext.attributes.CurrentAddressType;
import se.swedenconnect.opensaml.eidas.ext.attributes.address.AdminunitFirstline;
import se.swedenconnect.opensaml.eidas.ext.attributes.address.AdminunitSecondline;
import se.swedenconnect.opensaml.eidas.ext.attributes.address.CvaddressArea;
import se.swedenconnect.opensaml.eidas.ext.attributes.address.LocatorDesignator;
import se.swedenconnect.opensaml.eidas.ext.attributes.address.LocatorName;
import se.swedenconnect.opensaml.eidas.ext.attributes.address.PoBox;
import se.swedenconnect.opensaml.eidas.ext.attributes.address.PostCode;
import se.swedenconnect.opensaml.eidas.ext.attributes.address.PostName;
import se.swedenconnect.opensaml.eidas.ext.attributes.address.Thoroughfare;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Implementation of {@code CurrentAddressType}.
 *
 * @author Martin Lindström
 */
public class CurrentAddressTypeImpl extends CurrentAddressStructuredTypeImpl implements CurrentAddressType {

  /**
   * Constructor.
   *
   * @param namespaceURI the namespace the element is in
   * @param elementLocalName the local name of the XML element this Object represents
   * @param namespacePrefix the prefix for the given namespace
   */
  public CurrentAddressTypeImpl(
      final String namespaceURI, final String elementLocalName, final String namespacePrefix) {
    super(namespaceURI, elementLocalName, namespacePrefix);
  }

  /** {@inheritDoc} */
  @Override
  public String toSwedishEidString() {
    final StringBuilder sb = new StringBuilder();

    final List<XMLObject> children = this.getOrderedChildren();
    for (final XMLObject child : children) {
      if (child instanceof XSString) {
        final String value = ((XSString) child).getValue();
        if (value.trim().isEmpty()) {
          continue;
        }
        if (!sb.isEmpty()) {
          sb.append(';');
        }
        sb.append(child.getElementQName().getLocalPart())
            .append('=')
            .append(URLEncoder.encode(value, StandardCharsets.UTF_8).replaceAll("\\+", "%20"));
      }
    }

    return sb.toString();
  }

  /** {@inheritDoc} */
  @Override
  @Nullable
  public String toStringValue() {
    return this.toSwedishEidString();
  }

  /**
   * Parses a string in the format produced by {@link #toSwedishEidString()}, for example
   * {@code "LocatorDesignator=22;Thoroughfare=Arcacia%20Avenue;PostName=London"}. All current address elements are
   * replaced by the parsed ones.
   */
  @Override
  public void parseStringValue(@Nonnull final String value) throws IllegalArgumentException, NullPointerException {
    Objects.requireNonNull(value, "value must not be null");

    final Map<String, Consumer<String>> setters = Map.of(
        PoBox.DEFAULT_ELEMENT_LOCAL_NAME, this::setPoBox,
        LocatorDesignator.DEFAULT_ELEMENT_LOCAL_NAME, this::setLocatorDesignator,
        LocatorName.DEFAULT_ELEMENT_LOCAL_NAME, this::setLocatorName,
        CvaddressArea.DEFAULT_ELEMENT_LOCAL_NAME, this::setCvaddressArea,
        Thoroughfare.DEFAULT_ELEMENT_LOCAL_NAME, this::setThoroughfare,
        PostName.DEFAULT_ELEMENT_LOCAL_NAME, this::setPostName,
        AdminunitFirstline.DEFAULT_ELEMENT_LOCAL_NAME, this::setAdminunitFirstline,
        AdminunitSecondline.DEFAULT_ELEMENT_LOCAL_NAME, this::setAdminunitSecondline,
        PostCode.DEFAULT_ELEMENT_LOCAL_NAME, this::setPostCode);

    // Parse everything before changing anything, so that a bad value leaves the object as it was.
    final Map<String, String> parsed = new LinkedHashMap<>();
    for (final String pair : value.split(";")) {
      if (pair.isBlank()) {
        continue;
      }
      final int pos = pair.indexOf('=');
      if (pos <= 0) {
        throw new IllegalArgumentException("Invalid address element: " + pair);
      }
      final String name = pair.substring(0, pos).trim();
      if (!setters.containsKey(name)) {
        throw new IllegalArgumentException("Unknown address element: " + name);
      }
      parsed.put(name, URLDecoder.decode(pair.substring(pos + 1), StandardCharsets.UTF_8));
    }

    setters.values().forEach(s -> s.accept(null));
    parsed.forEach((name, v) -> setters.get(name).accept(v));
  }

}
