(ns playground.test.handler
  (:use clojure.test
        ring.mock.request
        playground.handler))

(deftest test-app
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (.contains (:body response) "Hello Werld"))))

  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404)))))
