![Logo](docs/img/sweden-connect-logo.png)

# opensaml-eidas

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) ![Maven Central](https://img.shields.io/maven-central/v/se.swedenconnect.opensaml/opensaml-eidas.svg)

OpenSAML extensions for the eIDAS Framework.

---

> **Note**: Support for OpenSAML versions prior to version 5 is implemented by the https://github.com/litsec/eidas-opensaml repository.

---

eIDAS (EU REGULATION [910/2014](http://eur-lex.europa.eu/legal-content/EN/TXT/HTML/?uri=CELEX:32014R0910&from=EN) on electronic identification and trust services for electronic transactions in the European internal market) defines requirements on cross-border recognition of electronic identification means in EU.

The eIDAS technical specifications defines a number of SAML elements and attribute definitions which are normally not supported by standard SAML software. The **eidas-opensaml** Open Source Java library extends the OpenSAML framework with support for the definitions from the eIDAS technical specifications.

The following eIDAS specifications are implemented:
* [eIDAS - Interoperability Architechture v1.4](https://docs.swedenconnect.se/opensaml-eidas/specs/eIDAS_Interoperability_Architecture_v_1_4_final.pdf)

* [ eIDAS - Cryptographic requirements for the Interoperability Framework v1.4](https://docs.swedenconnect.se/opensaml-eidas/specs/eIDAS_Cryptographic_Requirement_v_1_4_final.pdf)

* [eIDAS SAML Message Format v1.4](https://docs.swedenconnect.se/opensaml-eidas/specs/eIDAS_SAML_Message_Format_v_1.4_final.pdf)

* [eIDAS SAML Attribute Profile v1.4](https://docs.swedenconnect.se/opensaml-eidas/specs/eIDAS_SAML_Attribute_Profile_v1_4_final.pdf)


### How to use the use the eidas-opensaml library

The eidas-opensaml artifacts are published to Maven central and a dependency to the library should be included as follows in the application POM-file:

```
<dependency>
  <groupId>se.swedenconnect.opensaml</groupId>
  <artifactId>opensaml-eidas</artifactId>
  <version>${opensaml.eidas.version}</version>
</dependency>
```

### Documentation

* API documentation - [https://docs.swedenconnect.se/opensaml-eidas/apidocs/](https://docs.swedenconnect.se/opensaml-eidas/apidocs/).

### Examples

#### Creating an eIDAS AuthnRequest message

The [eIDAS SAML Message Format v1.4](https://docs.swedenconnect.se/opensaml-eidas/specs/eIDAS_SAML_Message_Format_v_1.4_final.pdf) specification describes how a SAML `AuthnRequest` message should be put together to comply to the eIDAS specifications. 

[CreateAuthnRequestExample.java](https://github.com/swedenconnect/opensaml-eidas/blob/main/src/test/java/se/swedenconnect/opensaml/eidas/examples/CreateAuthnRequestExample.java) illustrates how you could create an authentication request message using the opensaml-eidas library.

#### Parsing an Assertion

An assertion issued from an eIDAS service will contain the attributes defined in [eIDAS SAML Attribute Profile](https://github.com/litsec/eidas-opensaml/files/3236266/eIDAS.SAML.Attribute.Profile.v1.2-FINAL.pdf).

[ParseAssertionExample.java](https://github.com/swedenconnect/opensaml-eidas/blob/main/src/test/java/se/swedenconnect/opensaml/eidas/examples/ParseAssertionExample.java) shows how to parse an Assertion and get hold of all attribute values.

### Resources

##### eIDAS Specifications

* [eIDAS - Interoperability Architechture v1.4](https://docs.swedenconnect.se/opensaml-eidas/specs/eIDAS_Interoperability_Architecture_v_1_4_final.pdf)

* [ eIDAS - Cryptographic requirements for the Interoperability Framework v1.4](https://docs.swedenconnect.se/opensaml-eidas/specs/eIDAS_Cryptographic_Requirement_v_1_4_final.pdf)

* [eIDAS SAML Message Format v1.4](https://docs.swedenconnect.se/opensaml-eidas/specs/eIDAS_SAML_Message_Format_v_1.4_final.pdf)

* [eIDAS SAML Attribute Profile v1.4](https://docs.swedenconnect.se/opensaml-eidas/specs/eIDAS_SAML_Attribute_Profile_v1_4_final.pdf)

##### Swedish eID Framework

* [Technical specifications for the Swedish eID Framework](https://docs.swedenconnect.se/technical-framework)
* [Sweden Connect](https://www.swedenconnect.se) - The portal for the Sweden Connect federation.
* [Sweden Connect - Sandbox](https://sandbox.swedenconnect.se/home/) - The portal for the Swedish eID and eIDAS test infrastructure.

------

Copyright &copy; 2016-2025, [Sweden Connect](https://swedenconnect.se). Licensed under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).
