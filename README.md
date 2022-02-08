# zapominalka

app to quickly learn something

## Dependencies

clojure, leiningen

optional: ansible

optional optional: vagrant

## Usage

    $ java -jar zapominalka-0.1.0-standalone.jar [args]

    $ cd deploy
    $ vagrant up
    $ ./build && ./deploy vagrant-server

`vagrant-server` is a host config file for ansible

to add a real server go to `deploy/ansible`, add one
and pass it's name to deploy script

## Options

this program looks at 2 env variables: PORT and DBFILE,
where PORT is port and DBFILE is the path to the dbfile

### Bugs

No bugs 😠

## License

Copyright © 2022 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
