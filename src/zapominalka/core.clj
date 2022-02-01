(ns zapominalka.core
  (:gen-class)
  (:require [zapominalka.handler :as handler]
            [ring-jetty.adapter.jetty :as jetty]
            [ring.middleware.defaults :as ring-defaults]
            [muuntaja.middleware :as m]))

(defonce server (atom nil))

(defn start-jetty!
  "server start wrapper function"
  []
  (reset!
   server
   (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
   (jetty/run-jetty (-> #'handler/handler
                        m/wrap-format
                        (ring-defaults/wrap-defaults ring-defaults/api-defaults))
                    {:join? false
                     :port port}))))

(defn stop-jetty!
  "server stop wrapper function"
  []
  (.stop @server)
  (reset! server nil))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (start-jetty!))
