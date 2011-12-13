(ns valurl.urlvalidation
  (:require [net.cgrand.enlive-html :as html])
  (:require [clj-http.client :as client]))

(defn fetch-url
  [url]
  (html/html-resource (java.net.URL. url)))

(defn get-links
  [url]
  (map :href (map :attrs (html/select (fetch-url url) [:a]))))

(defn is-working
  [link]
  (try (if (= 200 (get (client/get link) :status))
	 true
	 false)
       (catch Exception e false)))

(defn process-links
  [links]
  (if (not (empty? links))
    (do
      (if (not (is-working (first links)))
	(do
	  (println (str (first links) " is not working"))
	  (recur (rest links)))
	(do
	  (println (str (first links) " is working"))
	  (recur (concat (rest links) (get-links (first links)))))))))
