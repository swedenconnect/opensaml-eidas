![Logo](https://docs.swedenconnect.se/technical-framework/img/sweden-connect.png)

# opensaml-eidas

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) 

---

## Release Notes

### 3.3.3

**Date:** _not yet released_

- `CurrentAddress` values containing `&`, `<` or `>` were not escaped when marshalled, so the attribute could not be parsed by the receiver, and a value could add extra address elements. See [#29](https://github.com/swedenconnect/opensaml-eidas/issues/29).

- When unmarshalling a `CurrentAddress` value, a namespace prefix declared on an ancestor element took precedence over the same prefix declared closer to the value, which could cause the address elements to be ignored. See [#32](https://github.com/swedenconnect/opensaml-eidas/issues/32).

- `parseStringValue` of a `CurrentAddress` attribute value ignored its input. It now reads the format produced by `toStringValue`. See [#34](https://github.com/swedenconnect/opensaml-eidas/issues/34).

- Unmarshalling a `CurrentAddress` value in structured form logged an error even though it succeeded. See [#35](https://github.com/swedenconnect/opensaml-eidas/issues/35).

- Setting `null` as the value of a `CountryOfBirth` or `CountryOfResidence` attribute value failed with a `NullPointerException`. See [#28](https://github.com/swedenconnect/opensaml-eidas/issues/28).

- [Eidas_1_4_SecurityConfiguration](https://github.com/swedenconnect/opensaml-eidas/blob/main/src/main/java/se/swedenconnect/opensaml/eidas/xmlsec/Eidas_1_4_SecurityConfiguration.java) failed with a `StackOverflowError` when its decryption configuration was created, so the eIDAS 1.4 security profile could not be used. See [#27](https://github.com/swedenconnect/opensaml-eidas/issues/27).

---

Copyright &copy; 2016-2026, [Sweden Connect](https://swedenconnect.se). Licensed under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).
