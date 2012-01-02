(ns valurl.core
  (:gen-class :main true)
  (:use clojure.contrib.command-line)
  (:use valurl.urlvalidation))

(defn -main[& args]
  (with-command-line
    args
    "Usage: -url <FQDN URL> e.g. -url \"http://www.slashdot.org\""
    [[url "The URL to be processed" "http://www.google.co.uk"]]
    (process-links (get-links url))))