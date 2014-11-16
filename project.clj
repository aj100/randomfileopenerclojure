(defproject randomfileopener "0.1.0"
  :description "Opens a random file in the given directory."
  :url "http://www.therightabstractions.com"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.cli "0.3.1"]
                 [lein-light-nrepl "0.1.0"]]
  :repl-options {:nrepl-middleware [lighttable.nrepl.handler/lighttable-ops]}
  :main randomfileopener.core
  :aot [randomfileopener.core])
