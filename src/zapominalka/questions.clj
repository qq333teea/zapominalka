(ns zapominalka.questions
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]))

(def db-file
  (str (or (System/getenv "DBFILE")
           "resources/db.json")))

(defn send-utf-8
  "unicode workaround
   TODO: proper unicode support"
  [string]
  (clojure.string/replace
   string
  #"\\u(\w{4})"
  (fn [[_ n]]
    (-> (Integer/parseInt n 16)
        char
        str))))

(defn get-db
  "reads db"
  []
  (json/read-str (slurp db-file)))

(defn addq 
  "adds new keyword to db and saves it"
  [_ req]
  (let [data (json/read-str req)]
    (let [new-key (first (keys data))]
      (spit db-file
            (json/write-str
             (assoc (get-db) new-key
                    (get data new-key)))))))

(defn getq
  "requst existing question from the db"
  [_ req]
  (let [data (get (get-db) req)]
    (send-utf-8 (json/write-str (get data (rand-int (count data)))))))

(defn list-keywords
  "returns list of all available keywords"
  [_]
  (json/write-str (keys (get-db))))

(defn dump-db
  "dumps current db value"
  [_]
  (send-utf-8 (slurp db-file)))
