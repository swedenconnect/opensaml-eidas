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

import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.io.UnmarshallingException;
import org.opensaml.core.xml.schema.XSString;
import org.opensaml.core.xml.schema.XSURI;
import org.opensaml.saml.common.AbstractSAMLObjectUnmarshaller;
import se.swedenconnect.opensaml.eidas.metadata.SchemeInformation;

import javax.annotation.Nonnull;
import javax.xml.namespace.QName;

/**
 * Unmarshaller for {@link SchemeInformation} objects.
 *
 * @author Martin Lindström
 */
public class SchemeInformationUnmarshaller extends AbstractSAMLObjectUnmarshaller {

  /** {@inheritDoc} */
  @Override
  protected void processChildElement(
      @Nonnull final XMLObject parentSAMLObject, @Nonnull final XMLObject childSAMLObject)
      throws UnmarshallingException {

    final SchemeInformation schemeInformation = (SchemeInformation) parentSAMLObject;

    final QName issuerNameIdQName = new QName(schemeInformation.getElementQName().getNamespaceURI(),
        SchemeInformation.ISSUER_NAME_LOCAL_NAME, schemeInformation.getElementQName().getPrefix());

    final QName schemeIdentifierQName = new QName(schemeInformation.getElementQName().getNamespaceURI(),
        SchemeInformation.SCHEME_IDENTIFIER_LOCAL_NAME, schemeInformation.getElementQName().getPrefix());

    final QName schemeTerritoryQName = new QName(schemeInformation.getElementQName().getNamespaceURI(),
        SchemeInformation.SCHEME_TERRITORY_LOCAL_NAME, schemeInformation.getElementQName().getPrefix());

    if (childSAMLObject instanceof XSString && issuerNameIdQName.equals(childSAMLObject.getElementQName())) {
      if (schemeInformation instanceof SchemeInformationImpl) {
        ((SchemeInformationImpl) schemeInformation).setIssuerName((XSString) childSAMLObject);
      }
      else {
        schemeInformation.setIssuerName(((XSString) childSAMLObject).getValue());
      }
    }
    else if (childSAMLObject instanceof XSURI && schemeIdentifierQName.equals(childSAMLObject.getElementQName())) {
      if (schemeInformation instanceof SchemeInformationImpl) {
        ((SchemeInformationImpl) schemeInformation).setSchemeIdentifier((XSURI) childSAMLObject);
      }
      else {
        schemeInformation.setSchemeIdentifier(((XSURI) childSAMLObject).getURI());
      }
    }
    else if (childSAMLObject instanceof XSString && schemeTerritoryQName.equals(childSAMLObject.getElementQName())) {
      if (schemeInformation instanceof SchemeInformationImpl) {
        ((SchemeInformationImpl) schemeInformation).setSchemeTerritory((XSString) childSAMLObject);
      }
      else {
        schemeInformation.setSchemeTerritory(((XSString) childSAMLObject).getValue());
      }
    }
    else {
      super.processChildElement(parentSAMLObject, childSAMLObject);
    }
  }

}
