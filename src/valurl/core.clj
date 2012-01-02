(ns valurl.core
  (:gen-class :main true)
  (:use clojure.contrib.command-line)
  (:use valurl.urlvalidation))

(defn -main[& args]
  (with-command-line
    args
    ""
    [[url "The URL to be processed" "http://www.slashdot.co.uk"]]
    (process-links (get-links url))))