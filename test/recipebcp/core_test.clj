(ns recipebcp.core-test)

(require '[clojure.test :as t])
;;(require '[clojure.test.check :as tc])
;;(require '[clojure.test.check.generators :as gen])
;;(require '[clojure.test.check.properties :as prop])



(t/deftest adtn
  (t/is (= 4 (+ 2 2)))
  (t/is (= 7 (+ 3 4))))

(t/is (instance? Double 1.1))
(t/is (.startsWith "abcde" "ab"))