# names-parser

A Clojure library designed to parse names.


## Installation

To install, add the following dependency to your project.clj file:

```
[names-parser "0.1.0"]
```

## Usage

To get started with using names-parser, require names-parser.parser in your project:

```
(ns yourapp.sample
       (:require [names-parser.parser :as names-parser]))
```

#### Parse a name

```
(names-parser/parse-name "Ana Micaela Monterroso de Lavalleja")

output => {:lastname "Monterroso de Lavalleja", :name "Ana Micaela"}
```

**Parameters**

*full-name*: the name you want to parse (it must be the full name, the library is expecting one or two names and two lastnames).

*capitalize (optional)*: if you want the output capitalized.

## License

Copyright © 2020 Alejandro Winkler

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
