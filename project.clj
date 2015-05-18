(defproject pigpen "0.3.0"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories [["conjars" "http://conjars.org/repo"]]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/data.json "0.2.5"]
                 [org.clojure/data.csv "0.1.2"]
                 [joda-time/joda-time "2.4"]
                 [com.taoensso/nippy "2.8.0"]
                 [prismatic/schema "0.3.3"]
                 [cascading/cascading-core "2.5.1"]
                 [cascading/cascading-hadoop "2.5.1"]
                 [org.slf4j/slf4j-log4j12 "1.6.1"]
                 [org.apache.hadoop/hadoop-core "1.1.2"]]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.6.0"]
                                  [org.apache.pig/pig "0.13.0"]]}}
  :javac-options ["-target" "1.6" "-source" "1.6"]
  :aot :all
  :source-paths ["pigpen-local/src/main/clojure"
                 "pigpen-core/src/main/clojure"
                 "pigpen-cascading/src/main/clojure"]
  :java-source-paths ["pigpen-cascading/src/main/java"]
  :test-paths ["pigpen-local/src/test/clojure"
               "pigpen-core/src/test/clojure"
               "pigpen-cascading/src/test/clojure"])
