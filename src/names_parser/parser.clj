(ns names-parser.parser
  (:require
    [clojure.string :as string]
    [pallet.thread-expr :as th]))

(defn parse-name [full-name & {:keys [capitalize] :or {capitalize false}}]
  (cond
    ;NAMES/LASTNAMES
    (string/includes? full-name "/")
    (->> (string/split full-name #"/")
         (map #(string/trim %))
         (th/when->> capitalize
                     (map #(->> (string/split % #" ")
                                (map string/capitalize)
                                (string/join " "))))
         (zipmap [:name :lastname])
         (into (sorted-map)))
    ;LASTNAMES,NAMES
    (string/includes? full-name ",")
    (->> (string/split full-name #",")
         (map #(string/trim %))
         (th/when->> capitalize
                     (map #(->> (string/split % #" ")
                                (map string/capitalize)
                                (string/join " "))))
         (zipmap [:lastname :name])
         (into (sorted-map)))
    ;NAMES LASTNAMES
    :else
    (let [name-parts (re-seq #"((?i)(?:de las|de los|de la|de|del|da) (?:[A-Z-azÁÉÍÓÚáéíóúñÑäöüÄÖÜß']+)|(?:[A-Z-azÁÉÍÓÚáéíóúñÑäöüÄÖÜß']+))" full-name)]
      (->> (hash-map :name (if (< (count name-parts) 4)
                             (->> (ffirst name-parts)
                                  (th/when->> capitalize
                                              clojure.string/capitalize))
                             (->> (take 2 name-parts)
                                  (map first)
                                  (th/when->> capitalize
                                              (map clojure.string/capitalize))
                                  (clojure.string/join " ")))
                     :lastname (->> (if (< (count name-parts) 4)
                                      (nthrest name-parts 1)
                                      (nthrest name-parts 2))
                                    (map first)
                                    (th/when->> capitalize
                                                (map clojure.string/capitalize))
                                    (clojure.string/join " ")))
           (into (sorted-map))))))