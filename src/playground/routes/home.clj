(ns playground.routes.home
  (:require [compojure.core :refer :all]
            [cheshire.core :refer  [generate-string]]
            [clojurewerkz.cassaforte.client :as cc]
            [liberator.core 
             :refer [defresource resource request-method-in]]))

(defn get-todo [id] "asdf")

(defroutes app-routes
  (context "/todos" [] 
    (defroutes todos
      (GET  "/" [] (get-todo))
      (POST "/" {todo :todo} (get-todo todo))
      (context "/:id" [id] (defroutes todo-routes
        (GET    "/" [] (get-todo id))
        (PUT    "/" {todo :todo} (get-todo id todo))
        (DELETE "/" [] (get-todo id)))))))


