(ns playground.routes.home
  (:require [compojure.core :refer :all]
            [playground.views.layout :as layout]))

(defn home []
  (layout/common [:h1 "Hello Werld"]))

(defroutes home-routes
  (GET "/" [] (home)))
