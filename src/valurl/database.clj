(ns valurl.database
  (:use somnium.congomongo)
  (:use clj-time.core)
  (:use clj-time.format))

(def built-in-formatter (formatters :basic-date-time))

(mongo! :db "brokenurls")

(defn add-bad-url [url rootlink]
  (insert! :brokenurls {:rootlink rootlink :url url :time (str (now))}))