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
package se.swedenconnect.opensaml.eidas.ext.attributes.impl;

import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.io.UnmarshallingException;
import org.opensaml.core.xml.schema.impl.XSStringUnmarshaller;
import org.w3c.dom.Attr;
import se.swedenconnect.opensaml.eidas.common.EidasConstants;
import se.swedenconnect.opensaml.eidas.ext.attributes.TransliterationStringType;

import javax.annotation.Nonnull;

/**
 * Unmarshaller for {@link TransliterationStringType}.
 *
 * @author Martin Lindström
 */
public class TransliterationStringTypeUnmarshaller extends XSStringUnmarshaller {

  /** {@inheritDoc} */
  @Override
  protected void processAttribute(@Nonnull final XMLObject xmlObject, final Attr attribute)
      throws UnmarshallingException {
    final TransliterationStringType tstring = (TransliterationStringType) xmlObject;

    if (attribute.getLocalName().equals(TransliterationStringType.LATIN_SCRIPT_ATTRIB_NAME)
        && (attribute.getNamespaceURI() == null
        || EidasConstants.EIDAS_NP_NS.equals(attribute.getNamespaceURI()))) {
      tstring.setLatinScript(Boolean.parseBoolean(attribute.getValue()));
    }
  }

}
