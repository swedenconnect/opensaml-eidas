![Logo](https://docs.swedenconnect.se/technical-framework/img/sweden-connect.png)

# opensaml-eidas

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) 

---

## Release Notes

### 3.3.3

**Date:** _not yet released_

- [Eidas_1_4_SecurityConfiguration](https://github.com/swedenconnect/opensaml-eidas/blob/main/src/main/java/se/swedenconnect/opensaml/eidas/xmlsec/Eidas_1_4_SecurityConfiguration.java) failed with a `StackOverflowError` when its decryption configuration was created, so the eIDAS 1.4 security profile could not be used. See [#27](https://github.com/swedenconnect/opensaml-eidas/issues/27).

---

Copyright &copy; 2016-2026, [Sweden Connect](https://swedenconnect.se). Licensed under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).
