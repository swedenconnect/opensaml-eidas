![Logo](https://docs.swedenconnect.se/technical-framework/img/sweden-connect.png)

# opensaml-eidas

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) 

---

## Release Notes

### 3.3.3

**Date:** _not yet released_

- Received gender values such as `male` were not mapped to the constants of [GenderTypeEnumeration](https://github.com/swedenconnect/opensaml-eidas/blob/main/src/main/java/se/swedenconnect/opensaml/eidas/ext/attributes/GenderTypeEnumeration.java), so they were not equal to `GenderTypeEnumeration.MALE`. See [#30](https://github.com/swedenconnect/opensaml-eidas/issues/30).

---

Copyright &copy; 2016-2026, [Sweden Connect](https://swedenconnect.se). Licensed under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).
