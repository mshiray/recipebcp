(ns clojuretryout.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!" args))

;simple function to accept string param and print hello
;msg on console
(defn sayHello [name]
  (println "Hello " name))

(sayHello "Hello Heavens!")
