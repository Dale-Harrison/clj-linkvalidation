(ns valurl.urlvalidation
  (:require [net.cgrand.enlive-html :as html])
  (:require [clj-http.client :as client])
  (:require [valurl.database :as db]))

(defn fetch-url
  [url]
  (html/html-resource (java.net.URL. url)))

(defn fix-relative-urls
  [rootlink links newlinks]
  (if (not (= (next links) nil))
      (do
	(if (not (= (re-find #"http" (first links)) (seq nil)))
	  (concat rootlink (concat  (first links))))
	(recur rootlink (rest links) newlinks))))

(defn get-links
  [url]
  (map #(vector url %)(map :href (map :attrs (html/select (fetch-url url) [:a])))))

(defn is-working
  [link]
  (try (if (= 200 (get (client/get link) :status))
	 true
	 false)
       (catch Exception e false)))

(defn process-links
  [links]
  (if (not (is-working (get (first links) 1)))
    (do
      (db/add-bad-url (get (first links) 1) (get (first links) 0))
      (recur (rest links)))
    (do
      (recur (concat (rest links) (get-links (get (first links) 1)))))))

