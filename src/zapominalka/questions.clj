(ns zapominalka.questions
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]))

(def db-file "resources/db.json")

(defn get-db
  "reads db"
  []
  (json/read-str (slurp db-file)))

(defn addq 
  "adds new keyword to db and saves it"
  [t req]
  (let [data (json/read-str req)]
    (let [new-key (first (keys data))]
      (spit db-file
            (json/write-str
             (assoc (get-db) new-key
                    (get data new-key)))))))

(defn getq
  "requst existing question from the db"
  [t req]
  (let [data (get (get-db) req)]
    (json/write-str (get data (rand-int (count data))))))

(defn list-keywords
  "returns list of all available keywords"
  [t]
  (json/write-str (keys (get-db))))

(defn dump-db
  "dumps current db value"
  [t]
  (str (slurp db-file)))
