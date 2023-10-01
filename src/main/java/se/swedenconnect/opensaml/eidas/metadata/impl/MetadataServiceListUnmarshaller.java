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
package se.swedenconnect.opensaml.eidas.metadata.impl;

import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.io.UnmarshallingException;
import org.opensaml.saml.common.AbstractSAMLObjectUnmarshaller;
import org.opensaml.xmlsec.signature.Signature;
import org.w3c.dom.Attr;

import com.google.common.base.Strings;

import net.shibboleth.shared.xml.DOMTypeSupport;
import se.swedenconnect.opensaml.eidas.metadata.DistributionPoints;
import se.swedenconnect.opensaml.eidas.metadata.MetadataList;
import se.swedenconnect.opensaml.eidas.metadata.MetadataServiceList;
import se.swedenconnect.opensaml.eidas.metadata.MetadataServiceListVersion;
import se.swedenconnect.opensaml.eidas.metadata.SchemeInformation;

/**
 * A thread safe unmarshaller for {@link MetadataServiceList}.
 *
 * @author Martin Lindström
 */
public class MetadataServiceListUnmarshaller extends AbstractSAMLObjectUnmarshaller {

  /** {@inheritDoc} */
  @Override
  protected void processChildElement(final XMLObject parentObject, final XMLObject childObject)
      throws UnmarshallingException {

    final MetadataServiceList mdsl = (MetadataServiceList) parentObject;

    if (childObject instanceof SchemeInformation) {
      mdsl.setSchemeInformation((SchemeInformation) childObject);
    }
    else if (childObject instanceof MetadataList) {
      mdsl.getMetadataLists().add((MetadataList) childObject);
    }
    else if (childObject instanceof DistributionPoints) {
      mdsl.setDistributionPoints((DistributionPoints) childObject);
    }
    else if (childObject instanceof Signature) {
      mdsl.setSignature((Signature) childObject);
    }
    else {
      super.processChildElement(parentObject, childObject);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void processAttribute(final XMLObject samlObject, final Attr attribute) throws UnmarshallingException {

    final MetadataServiceList mdsl = (MetadataServiceList) samlObject;

    if (attribute.getLocalName().equals(MetadataServiceList.ID_ATTR_NAME)) {
      mdsl.setID(attribute.getValue());
      attribute.getOwnerElement().setIdAttributeNode(attribute, true);
    }
    else if (attribute.getLocalName().equals(MetadataServiceList.VERSION_ATTR_NAME)) {
      mdsl.setVersion(MetadataServiceListVersion.valueOf(attribute.getValue()));
    }
    else if (attribute.getLocalName().equals(MetadataServiceList.ISSUE_DATE_ATTR_NAME)
        && !Strings.isNullOrEmpty(attribute.getValue())) {
      mdsl.setIssueDate(DOMTypeSupport.stringToInstant(attribute.getValue()));
    }
    else if (attribute.getLocalName().equals(MetadataServiceList.NEXT_UPDATE_ATTR_NAME)
        && !Strings.isNullOrEmpty(attribute.getValue())) {
      mdsl.setNextUpdate(DOMTypeSupport.stringToInstant(attribute.getValue()));
    }
    else {
      super.processAttribute(samlObject, attribute);
    }

  }

}
