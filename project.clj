(defproject zapominalka "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [ring-jetty/ring-jetty-adapter "0.1.0-SNAPSHOT"]
                 [ring/ring-defaults "0.3.3"]
                 [compojure "1.6.2"]
                 [hiccup "2.0.0-alpha2"]
                 [metosin/muuntaja "0.6.8"]
                 [org.clojure/data.json "0.2.6"]]
  :main ^:skip-aot zapominalka.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
