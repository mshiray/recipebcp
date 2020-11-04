(ns com.mshiray.recipebcp

  (:require
   [clojure.spec.alpha :as s]

   ;;[org.clojure/test.check "0.9.0"] need to be added to
   ;;project.clj dependencies for spec.gen.alpha
   [clojure.spec.gen.alpha :as gen]

   [clojure.repl :as crpl]

    ;;this is needed so that all required dependencies are loaded at boot time.

   ;;require all needed spec modules with respective prefixes
   [com.mshiray.recipebcp.spec [recipe_specs :as rs] [ingredient_specs :as is] [user_specs :as us]]

   ;;require all needed user type namespace modules
   ;; (defrecords, deftypes, interfaces etc.)
   [com.mshiray.recipebcp.domain.type user  recipe ingredient])

  (:import (com.mshiray.recipebcp.domain.type.recipe Recipe)
           (com.mshiray.recipebcp.domain.type.user User)
           (com.mshiray.recipebcp.domain.type.ingredient Ingredient)
           (com.mshiray.recipebcp.domain.type.ingredient In_Measure)))






;; ######   Lets do some Simple type validation with spec  ##########

;;simple data type validations based on defined regexs & type constraints
(s/valid? ::rs/r_estTime "02:23")
;; => true

;;validation against defined coll of keywords
(s/valid? ::rs/r_diet ::rs/Keto)
;; => true


;;validate & explain any validation errors..
;;this spec validates against the locale regex pattern defined in common_specs.clj
(s/explain ::us/u_langPref "zh_CN")
;; => nil

(s/valid? ::us/u_langPref "en_US")

;;generate sample data from spec defs
(gen/generate (s/gen pos-int?))
;; => 52589684

(gen/generate (s/gen ::rs/r_cuisine))
;; => :com.mshiray.recipebcp.spec/Italian

;;generate sample test data for specified spec def
(gen/sample (s/gen ::rs/r_diet 3))
;; => (:com.mshiray.recipebcp.spec/Vegan
;;     :com.mshiray.recipebcp.spec/Other
;;...


;;prints documentation of the specified spec
(crpl/doc ::is/Ingredient)


;;check if specified keyword belongs to the sequence.
(def tests #{:a :b :c})
(some tests #{:b})
;; => :b

;;validate inline sequence key-val pair data
(s/valid? ::is/Ingredient {:in_name "toast" :in_category ::is/bakery})
;; => true





;#######  Define required record type instances for our recipe spec validations ###########

;;define instance of user defrecord as the publisher of recipe
(def user (User. 2345 "joesimpson" "Joe"
                 "Simpson" "+919970456243" "jsms345@treemail.com"
                 "23/11/1985" "11/02/2014"))
;; => #'com.mshiray.recipebcp/user

(s/valid? ::us/User user)
;; => true

;;create new user record by adding optional lang pref property
;;to existing user record
(def user_with_lang_pref (assoc user ::us/u_langPref "en_US"))

(-> user_with_lang_pref ::us/u_langPref)
;; => "en_US"

(def flour (Ingredient. "Wheat flour" ::is/bakery))         ;;Note:  '.' when record type is imported


(s/valid? ::is/Ingredient flour)
;; => true

(s/valid? ::is/Ingredient {::is/in_name     (-> flour :in_name)
                           ::is/in_category (-> flour :in_category)})
;; => true

;;get keys of defrecord type
(keys flour)
;; => (:in_name :in_category)

;;return no. of keys defined in defrecord
(count flour)
;; => 2


;;access defrecord instance named properties
(-> flour :in_name)
;; => "Wheat flour"

(-> flour :in_category)
;; => :com.mshiray.recipebcp.spec/bakery

;;validate defrecord instancd against defined Ingredient spec..
(s/valid? ::is/Ingredient flour)
;; => true


;;create ingredient data from hash map instead of defreocrd type
(def eggs (hash-map
           :in_name "eggs"
           :in_category ::is/poultry))

;;vlaidate created hashmap data against the defined Ingredient spec...
(s/valid? ::is/Ingredient eggs)
;; => true

;;append optional properties key-vals to existing entity map
(def eggs_with_specs (assoc eggs
                       ;;IngredientFormFactor props
                            :in_form ::is/whole
                            :in_process_type ::is/fresh
                            :in_form_details ::is/large

                            :in_special_instrns ::is/organic
                            :alergy_warning false
                            :in_description "organic farm fresh chicken eggs"))


;;access properties of compbined eggs entity map
(-> eggs_with_specs :in_name)
;; => "eggs"


;; => "eggs"
(eggs_with_specs :in_description)
;; => "organic farm fresh chicken eggs"

;;validate appended entity map against the Ingredient specs
(s/valid? ::is/Ingredient eggs_with_specs)
;; => true

;;TODO
;;ref: https://clojuredocs.org/clojure.core/defrecord
;; This is particularly useful for mapping parsed CSV files to records
;;(map #(apply t/Ingredient %) parsed-csv-file)

;;lets add flour ingredient measures required for our recipes
(def flour_msr_qty (In_Measure. 2 ::is/C))

;;append measures record isntance to flour ingredient for our bread recipe
(def two_cup_flour (assoc flour ::in_measure flour_msr_qty
                          ::in_measure_type ::Lightly_Packed
                          ::in_decription "whole wheat flour one cup"))

(s/valid? ::is/Ingredient two_cup_flour)
;; => true

;lets see internal data structure of the created flour ingredient
(-> two_cup_flour)
;; => {:in_name "Wheat flour",
;;     :in_category
;;     :com.mshiray.recipebcp.spec.ingredient_specs/bakery,
;;     :com.mshiray.recipebcp/in_measure
;;     {:in_measure_no 2,
;;      :in_measure_units
;;      :com.mshiray.recipebcp.spec.ingredient_specs/C},
;;     :com.mshiray.recipebcp/in_measure_type
;;     :com.mshiray.recipebcp/Lightly_Packed,
;;     :com.mshiray.recipebcp/in_decription
;;     "whole wheat flour one cup"}


;;access nested cup measures quantity amount & units for flour ingredient
(-> two_cup_flour ::in_measure :in_measure_no)
;; => 2
(-> two_cup_flour ::in_measure :in_measure_units :value)
;; => :com.mshiray.recipebcp.spec.ingredient_specs/C


;;create new ingredient record instance for salt
(def salt (Ingredient. "salt" ::is/additive))

;;append measures properties to salt ingredient
(def one_tsp_salt
  (assoc salt ::is/in_measure [[1 ::is/tsp]
                               ::is/Lightly_Packed
                               "usual cup filled to top"]))
;; => #'com.mshiray.recipebcp/one_tsp_salt


;;access nested recipe composite properties
(-> one_tsp_salt ::is/in_measure)

;; => [[1 :com.mshiray.recipebcp.spec.ingredient_specs/tsp]
;;     :com.mshiray.recipebcp.spec.ingredient_specs/Lightly_Packed
;;     "usual cup filled to top"]

(s/valid? ::is/Ingredient one_tsp_salt)
;; => false


(def bread_recipe (Recipe. 135 "bread"
                           user_with_lang_pref "brown bread" "01:00" 4
                           ::rs/Vegiterian ::rs/Western
                           ::rs/Bakery ::rs/Other ::T3
                           [two_cup_flour one_tsp_salt]))

;;define recipe defrecord with optional companion recipe added.
(def omlette_recipe (Recipe. 134 "omlette" user_with_lang_pref
                             "masala omlette" "00:20" 1
                             ::rs/NonVeg ::rs/Indian ::rs/Breakfast ::rs/Spicy ::T4
                             [eggs salt]))


;;append optional companion recipe property to existing recipe defrecord
(def bread_omlette (assoc omlette_recipe ::rs/companions [bread_recipe]))

;;access nested properties of defrecord
(-> bread_omlette :r_publisher :u_fname)
;; => "Joe"
