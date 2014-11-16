(ns randomfileopener.core
  (:require [clojure.tools.cli :refer [parse-opts]])
  (:import (java.awt Desktop))
  (:gen-class :main true))


(defn -main [& args]
  (if-not (verify-params args)
    (println "usage: lein run \"C:/YourDirectoryPath\" [nested]")
    (println "valid")))

(defn verify-params [args]
  "There are either one or two arguments and the second argument has to be the word 'nested'"
  (let [args-count (count (into [] args))]
    (or (= args-count 2) (= args-count 1))))


(-main "a" "b" "c")

;; (def files
;;   (.listFiles (io/file "C:/Users/Anurag/Documents/Projects/Clojure")))


;; (map println files)


;;(.open (java.awt.Desktop/getDesktop) (io/file "C:/Users/Anurag/Desktop/082114_1217_goodguy1.png"))


;(def directory (clojure.java.io/file "C:/Users/Anurag/Documents/Projects/Clojure"))
;(def files (file-seq directory))
;(take 10 files)


;;
