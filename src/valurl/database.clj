(ns valurl.database
  (:use somnium.congomongo))

(mongo! :db "brokenurls")

(defn add-bad-url [url rootlink]
  (insert! :brokenurls {:rootlink rootlink :url url}))