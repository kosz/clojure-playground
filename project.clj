(defproject playground "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [liberator "0.11.0"]
                 [ring/ring-json "0.1.2"]
                 [cheshire "5.3.1"]
                 [joplin.core  "0.2.10"]
                 [org.slf4j/slf4j-log4j12  "1.6.6"]
                 [clojurewerkz/cassaforte  "2.0.0"]
                 [log4j/log4j "1.2.16" :exclusions [javax.mail/mail
                                                    javax.jms/jms
                                                    com.sun.jdmk/jmxtools
                                                    com.sun.jmx/jmxri]]
                 [ring-server "0.3.1"]]
  :plugins [[lein-ring "0.8.12"]
            [joplin.lein  "0.2.10"]]
  :ring {:handler playground.handler/app
         :init playground.handler/init
         :destroy playground.handler/destroy}

  :joplin {:migrators {:cass-mig "src/migrators"}
           :seeds {:cass-seed "config/seeds"}
           :databases {:cass-dev {:type :cass, :hosts ["127.0.0.1"], :keyspace "todo" }}
           :environments {:dev [{:db :cass-dev, :migrator :cass-mig, :seed :cass-seed}]}}
  :profiles
  {:uberjar {:aot :all}
   :production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.3.1"]]}})
