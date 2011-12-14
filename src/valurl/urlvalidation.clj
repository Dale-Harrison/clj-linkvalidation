(ns valurl.urlvalidation
  (:require [net.cgrand.enlive-html :as html])
  (:require [clj-http.client :as client]))

(defn fetch-url
  [url]
  (html/html-resource (java.net.URL. url)))

(defn fix-relative-urls
  [rootlink links newlinks]
  (if (not (= (next links) nil))
    (do (if (not (= (re-find #"http" (first links)) (seq nil)))
	  (concat rootlink (concat newlinks (first links))))
	(recur rootlink (rest links) newlinks))
    newlinks))

(defn get-links
  [url]
  (fix-relative-urls url (map :href (map :attrs (html/select (fetch-url url) [:a])))))

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
