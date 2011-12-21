(ns valurl.database
 (:require [clojure.contrib.sql :as sql]))

(def db-params {:classname   "org.sqlite.JDBC"
		:subprotocol "sqlite"
		:subname     "db/database.sqlite"})

(defn create-db []
  (sql/with-connection db-params
    (sql/create-table :brokenurls
		      [:url :text]
		      [:rootlink :text])))

(defn get-all-bad-urls []
  (sql/with-connection db-params
    (sql/with-query-results results
      ["select * from brokenurls"]
      (into [] results))))

(defn add-bad-url [url rootlink]
  (sql/with-connection db-params
    (sql/insert-values :brokenurls [:url :rootlink] [url rootlink])))