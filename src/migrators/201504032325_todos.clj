(ns migrators.201504032325-todos
  (:use [joplin.cassandra.database])
  (:require [clojurewerkz.cassaforte.cql   :as cql]
            [clojurewerkz.cassaforte.query :as cq]))

(defn up [db]
  (let [conn (get-connection (:hosts db) (:keyspace db))]
    (cql/create-table conn "todos"
                      (cq/column-definitions {:id :int
                                              :todo :varchar
                                              :primary-key [:id]}))))


(defn down [db]
  (let [conn (get-connection (:hosts db) (:keyspace db))]
    (cql/drop-table conn "todos")))
