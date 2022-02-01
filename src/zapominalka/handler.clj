(ns zapominalka.handler
  (:require [zapominalka.questions :as que]
            [compojure.core :as c]
            [clojure.pprint :as pp]
            [hiccup.core :as h]
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
  ""
  [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (->
             (pp/pprint req)
             (que/dump-db))})

(defn route-list-keys
  ""
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
   :body    (str (h/html [:html
                          [:body
                           [:h1 "zapominalka"]
                           [:ul
                            [:li "/getq - get question by key"]
                            [:li "/addq - add question from json"]
                            [:li "/db   - dump db"]
                            [:li "/keys - get all available keys"]]]]))})

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
