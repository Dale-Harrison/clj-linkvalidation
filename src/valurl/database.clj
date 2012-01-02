(ns valurl.database
  (:use somnium.congomongo)
  (:use clj-time.core)
  (:use clj-time.format))

(def built-in-formatter (formatters :basic-date-time))

(def conn
     (make-connection "brokenurlsdb"
		      :host "127.0.0.1"
		      :port 27017))

(set-connection! conn)

(mongo! :db "brokenurls")

(defn add-bad-url [url rootlink]
  (insert! :brokenurls {:rootlink rootlink :url url :time (str (now))}))