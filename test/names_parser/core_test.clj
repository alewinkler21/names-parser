(ns names-parser.core-test
  (:require [clojure.test :refer :all]
            [names-parser.parser :refer :all]
            [clojure.java.io :as io]
            [names-parser.csv-utils :as csv]))

(deftest test-parse-name
  (testing "parse-name"
    (csv/read-csv (io/resource "names.csv") #(let [parsed (parse-name (:COMPLETO %))]
                                               (is (and (= (:name parsed) (:NOMBRE %))
                                                        (= (:lastname parsed) (:APELLIDO %)))
                                                   (format "Failed for value %s" (:COMPLETO %)))))))