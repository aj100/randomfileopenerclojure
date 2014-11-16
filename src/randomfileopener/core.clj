(ns randomfileopener.core
  (:require [clojure.java.io :as io])
  (:import (java.awt Desktop)
           (org.apache.commons.io FileUtils))
  (:gen-class :main true))

(defn verify-args? [args]
  "There are either one or two arguments. The first must be a valid directory and the second must be the word 'nested'"
  (let [args-count (count (into [] args))
        arg1 (first args)
        arg2 (second args)]
    (and (or (= args-count 1) (= args-count 2))
         (.exists (io/file arg1))
         (or (nil? arg2) (= arg2 "nested")))))

(defn open-random-file [directory is-nested]
  (letfn [(get-files [dir nested]
                     (FileUtils/listFiles (io/file dir) nil (= nested "nested")))
          (open-file [desktop file]
                     (.open desktop file))]
    (let [files (get-files directory is-nested)]
        (let [sfiles  (shuffle files)
              desktop (java.awt.Desktop/getDesktop) ]
          (doseq [f sfiles]
            (print (.getAbsolutePath f))
            (flush)
            (open-file desktop f)
            (read-line))
          (println "No more files to open.")))))

(defn -main [& args]
  (if-not (verify-args? args)
    (println "usage: lein run \"C:/YourDirectoryPath\" [nested]")
    (open-random-file (first args) (second args))))

