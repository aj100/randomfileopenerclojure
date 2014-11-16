(ns randomfileopener.core
  (:require [clojure.java.io :as io])
  (:import (java.awt Desktop)
           (org.apache.commons.io FileUtils))
  (:gen-class :main true))

(defn verify? [args]
  "There are either one or two arguments and the second argument has to be the word 'nested'"
  (let [args-count (count (into [] args))]
    (or (= args-count 1)
        (and (= args-count 2)
             (= (second args) "nested")))))

(defn open-random-file [directory is-nested]
  (letfn [(get-files [dir nested]
                     (FileUtils/listFiles (io/file dir) nil (= nested "nested")))
          (open-file [desktop file]
                     (.open desktop file))]
    (let [files (get-files directory is-nested)]
      (if (empty? files)
        (println "No files found in the given directory.")
        (let [sfiles  (shuffle files)
              desktop (java.awt.Desktop/getDesktop) ]
          (doseq [f sfiles]
            (print (.getAbsolutePath f))
            (flush)
            (open-file desktop f)
            (read-line))
          (println "No more files to open."))))))

(defn -main [& args]
  (if-not (verify? args)
    (println "usage: lein run \"C:/YourDirectoryPath\" [nested]")
    (open-random-file (first args) (second args))))
