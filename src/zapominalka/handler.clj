(ns zapominalka.handler
  (:require [zapominalka.questions :as que]
            [compojure.core :as c]
            [clojure.pprint :as pp]
            [compojure.route :as route]))

(defn route-getq
  ""
  [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (->
             (pp/pprint req)
             (que/getq (:q (:params req))))})

(defn route-addq
  ""
  [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (->
             (pp/pprint req)
             (que/addq (:q (:params req))))})

(defn route-db
  "home page route"
  [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (->
             (pp/pprint req)
             (que/dump-db))})

(defn route-list-keys
  "home page route"
  [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (->
             (pp/pprint req)
             (que/list-keywords))})

(defn home-view
  "home page route"
  [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Home"})

(defn error-page
  "error route"
  [req]
  {:status  404
   :headers {"Content-Type" "text/html"}
   :body    "404: Not Found"})

(defn routes
  "defines all the routes"
  []
  (c/routes
   (c/GET "/" [] home-view)
   (c/GET "/getq" [] route-getq)
   (c/GET "/addq" [] route-addq)
   (c/GET "/db" [] route-db)
   (c/GET "/keys" [] route-list-keys)
   (route/not-found error-page)))

(defn handler
  "handles routing"
  [req]
  ((routes) req))
