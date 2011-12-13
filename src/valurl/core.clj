(ns valurl.core
  (:gen-class :main true)
  (:use valurl.urlvalidation))

(defn -main[]
  (process-links (get-links "http://www.google.co.uk/")))