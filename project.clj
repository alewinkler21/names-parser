(defproject names-parser "0.1.0"
  :description "Names parser"
  :url "https://github.com/alewinkler21"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [com.palletops/thread-expr "1.3.0"]
                 [org.clojure/data.csv "1.0.0"]
                 [commons-io "2.5"]]
  :repl-options {:init-ns names-parser.parser})
