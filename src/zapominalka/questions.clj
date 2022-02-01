(ns zapominalka.questions
  (:require [clojure.data.json :as json]))

(def db
  "main question db
   key is the topic of the question
   first item in subvector is the answer
   TODO: load db from json from some kind of cache dir"
  {"history" [["1223" "bitva na reke kalke"]
              ["1240" "nevskaya bitva"]
              ["1242" "ledovoe poboische"]
              ["1480" "stoyanie na reke ugre"]]
   "key2" [["3tf" "lkfs"]
           ["sdfklj" "fdslkfj"]
           ["124" "lksd"]]})

(defn addq 
  "adds new keyword to db and saves it"
  [t req]
  (let [data (json/read-str req)]
    (clojure.pprint/pprint data)
    (let [new-key (first (keys data))]
      (clojure.pprint/pprint (first new-key))
      (str (assoc db new-key (get data new-key))))))

(defn getq
  "requst existing question from the db"
  [t req]
  (let [data (get db req)]
    (json/write-str (get data (rand-int (count data))))))

(defn list-keywords
  "returns list of all available keywords"
  [t]
  (json/write-str (keys db)))

(defn dump-db
  "dumps current db value"
  [t]
  (json/pprint-json db)
  (json/write-str db))
