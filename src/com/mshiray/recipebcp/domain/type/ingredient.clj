(ns com.mshiray.recipebcp.domain.type.ingredient
  (:gen-class))

;;Domain entity type for ingredient

(defrecord IngredientFormFactor [in_form in_process_type in_form_details])

(defrecord IngredientSpecs [^IngredientFormFactor in_form in_special_instrns in_alergic])

(defrecord IngredientwSpecs [^String in_name in_category ^IngredientSpecs in_specs])

(defrecord Ingredient [in_name in_category])

(defrecord In_Measure [in_measure_no in_measure_units])
;; => com.mshiray.recipebcp.domain.type.ingredient.In_Measure

