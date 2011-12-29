(defproject clj-validateurl "0.1.0-SNAPSHOT"
            :description "A web crawling url validation utility"
            :dependencies [[org.clojure/clojure "1.2.1"]
                           [org.clojure/clojure-contrib "1.2.0"]
			   [congomongo "0.1.7"]
                           [enlive "1.0.0"]
			   [clj-http "0.1.3"]]
	    :dev-dependencies [[swank-clojure "1.3.4"]]
	    :main valurl.core)
