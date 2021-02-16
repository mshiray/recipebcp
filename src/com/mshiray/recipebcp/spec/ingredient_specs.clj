(ns com.mshiray.recipebcp.spec.ingredient_specs
  (:require [clojure.spec.alpha :as s]
            [com.mshiray.recipebcp.domain.type.ingredient :as i]))

;;collection catalog of ingredient
(s/def ::IngredientCatalog (s/coll-of ::Ingredient :min-count 1 :distinct true))

;;ingredient domian entity
(s/def ::Ingredient ;;maps to retail product catalog item
  (s/keys
   :req-un [::in_name ::in_category];;todo: add prodId & prodgrpid to map to retail product catalog.
   :opt-un [::IngredientSpecs ::in_description ::in_measure]))

(s/def ::in_name string?);;todo: add valid name regex

;;recipe specific ingredient specifications
(s/def ::IngredientSpecs
  (s/keys
   :req-un [::IngredientFormFactor]
   :opt-un [::in_special_instrns ::alergy_warning]))


(s/def ::in_measure
  (s/keys
   :req-un [::in_measure_qty]
   :opt-un [::in_measure_type ::in_description]))

;;amount of unit measures inline with ::serves_no (no. of people) specified
;;for the defined recipe.
(s/def ::in_measure_qty (s/cat ::in_measure_no ::in_measure_units))

(s/def ::in_measure_no (s/and number? > 0.0))

;;https://en.wikipedia.org/wiki/Cooking_weights_and_measures
(s/def ::in_measure_units  i/in_measure_units)

;;Applicable for units of measures like cups or spoons..
(s/def ::in_measure_type  i/in_measure_type)

(s/def ::in_description string?)

;;broader food category the ingredient belongs to
(s/def ::in_category i/in_category)

;;form factor of an ingredient needed for recipe
(s/def ::IngredientFormFactor
  (s/keys
   :req-un [::in_form]
   :opt-un [::in_process_type ::in_form_details]))


(s/def ::in_form  i/in_form)

(s/def ::in_process_type  i/in_process_type)

(s/def ::in_form_details i/in_form_details)

;;todo: need to make it multi value
(s/def ::in_special_instrns i/in_special_instrns)

(s/def ::alergy_warning boolean?);;if this ingredient is a known alergy inducer
