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
package se.swedenconnect.opensaml.eidas.ext.attributes.impl;

import org.opensaml.core.xml.schema.XSBooleanValue;
import org.opensaml.core.xml.schema.impl.XSStringImpl;

import se.swedenconnect.opensaml.eidas.ext.attributes.TransliterationStringType;

/**
 * Abstract implementation class of {@link TransliterationStringType}.
 *
 * @author Martin Lindström
 */
public abstract class TransliterationStringTypeImpl extends XSStringImpl implements TransliterationStringType {

  /** The {@code LatinScript} attribute. */
  private XSBooleanValue latinScript;

  /**
   * Constructor.
   *
   * @param namespaceURI the namespace the element is in
   * @param elementLocalName the local name of the XML element this Object represents
   * @param namespacePrefix the prefix for the given namespace
   */
  protected TransliterationStringTypeImpl(final String namespaceURI, final String elementLocalName,
      final String namespacePrefix) {
    super(namespaceURI, elementLocalName, namespacePrefix);
  }

  /** {@inheritDoc} */
  @Override
  public Boolean getLatinScript() {
    if (this.latinScript != null) {
      return this.latinScript.getValue();
    }
    return Boolean.TRUE;
  }

  /** {@inheritDoc} */
  @Override
  public XSBooleanValue getLatinScriptXSBooleanValue() {
    return this.latinScript;
  }

  /** {@inheritDoc} */
  @Override
  public void setLatinScript(final Boolean latinScript) {
    if (latinScript != null) {
      this.latinScript = this.prepareForAssignment(this.latinScript, new XSBooleanValue(latinScript, false));
    }
    else {
      this.latinScript = this.prepareForAssignment(this.latinScript, null);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void setLatinScript(final XSBooleanValue latinScript) {
    this.latinScript = this.prepareForAssignment(this.latinScript, latinScript);
  }

  /** {@inheritDoc} */
  @Override
  public String toStringValue() {
    return this.getValue();
  }

  /** {@inheritDoc} */
  @Override
  public void parseStringValue(final String value) {
    this.setValue(value);
  }

}
