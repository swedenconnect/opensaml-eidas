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

import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.io.AbstractXMLObjectUnmarshaller;
import org.opensaml.core.xml.io.UnmarshallingException;

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

/**
 * Thread safe unmarshaller for {@code CurrentAddressStructuredType}.
 *
 * @author Martin Lindström
 */
public class CurrentAddressStructuredTypeUnmarshaller extends AbstractXMLObjectUnmarshaller {

  /** {@inheritDoc} */
  @Override
  protected void processChildElement(final XMLObject parentObject, final XMLObject childObject)
      throws UnmarshallingException {
    final CurrentAddressStructuredType address = (CurrentAddressStructuredType) parentObject;

    if (childObject instanceof PoBox) {
      address.setPoBox(((PoBox) childObject).getValue());
    }
    else if (childObject instanceof LocatorDesignator) {
      address.setLocatorDesignator(((LocatorDesignator) childObject).getValue());
    }
    else if (childObject instanceof LocatorName) {
      address.setLocatorName(((LocatorName) childObject).getValue());
    }
    else if (childObject instanceof CvaddressArea) {
      address.setCvaddressArea(((CvaddressArea) childObject).getValue());
    }
    else if (childObject instanceof Thoroughfare) {
      address.setThoroughfare(((Thoroughfare) childObject).getValue());
    }
    else if (childObject instanceof PostName) {
      address.setPostName(((PostName) childObject).getValue());
    }
    else if (childObject instanceof AdminunitFirstline) {
      address.setAdminunitFirstline(((AdminunitFirstline) childObject).getValue());
    }
    else if (childObject instanceof AdminunitSecondline) {
      address.setAdminunitSecondline(((AdminunitSecondline) childObject).getValue());
    }
    else if (childObject instanceof PostCode) {
      address.setPostCode(((PostCode) childObject).getValue());
    }
    else {
      super.processChildElement(parentObject, childObject);
    }
  }

}
