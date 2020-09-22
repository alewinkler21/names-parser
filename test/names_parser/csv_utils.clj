(ns names-parser.csv-utils
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [clojure.string :as string])
  (:import (org.apache.commons.io.input BOMInputStream)))

(def supported-separators [";" "," "~" "\t"])

(defn- guess-csv-separator [csv-file]
  (letfn [(read-first-line [file]
            (with-open [reader (-> file
                                   (io/input-stream)
                                   (BOMInputStream.)
                                   (io/reader :encoding "UTF-8"))]
              (->> (line-seq reader)
                   (first))))]
    (if-let [first-line (read-first-line csv-file)]
      (loop [candidate ""
             separators supported-separators
             max-count 0]
        (if-let [separator (first separators)]
          (let [separator-count (->> (string/split first-line (re-pattern separator))
                                     (count))]
            (recur (if (> separator-count max-count) separator candidate)
                   (rest separators)
                   (if (> separator-count max-count) separator-count max-count)))
          (first candidate)))
      (-> (first supported-separators)
          (first)))))

(defn read-csv [csv-file fn-process-line]
  (with-open [reader (-> csv-file
                         (io/input-stream)
                         (BOMInputStream.)
                         (io/reader :encoding "UTF-8"))]
    (when-let [read-data (csv/read-csv reader :separator (guess-csv-separator csv-file))]
      (let [header (map #(keyword (string/replace % #" " "_")) (first read-data))]
        (->> (rest read-data)
             (map #(zipmap header %))
             (map fn-process-line)
             doall)))))
