(defproject clj-validateurl "0.1.0-SNAPSHOT"
            :description "A web crawling url validation utility"
            :dependencies [[org.clojure/clojure "1.2.1"]
                           [org.clojure/clojure-contrib "1.2.0"]
			   [sqlitejdbc "0.5.6"]
                           [enlive "1.0.0"]
			   [clj-http "0.1.3"]]
	    :main valurl.core)
