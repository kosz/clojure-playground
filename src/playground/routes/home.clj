(ns playground.routes.home
  (:require [compojure.core :refer :all]
            [cheshire.core :refer  [generate-string]]
            [clojurewerkz.cassaforte.client :as cc]
            [liberator.core 
             :refer [defresource resource request-method-in]]))

(def users(atom ["Hohn" "Jane"]))

(defresource home 
  :handle-ok "Hello Werld"
  :etag "fixed-etag"
  :available-media-types ["text/plain"])

(defresource cass 
  :handle-ok (fn [_] (cc/connect ["127.0.0.1"])
               (print-str "teasdf")) 
  :etag "fixed-etag"
  :available-media-types ["application/json"])

(defresource get-users
  :allowed-methods [:get]
  :handle-ok (fn [_] (generate-string @users))
  :available-media-types ["application/json"])

(defresource add-user
  :method-allowed? (request-method-in :post)
  :post!
  (fn [context]
    (let [params (get-in context [:request :form-params])]
      (swap! users conj (get params "user"))))
  :handle-created (generate-string @users)
  :available-media-types ["application/json"])

(defroutes home-routes
  (ANY "/" request home)
  (ANY "/cass" request cass)
  (ANY "/get-users" request get-users)
  (ANY "/add-user" request add-user))
