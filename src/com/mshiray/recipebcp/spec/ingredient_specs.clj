(ns com.mshiray.recipebcp.spec.ingredient_specs
  (:require [clojure.spec.alpha :as s]

            ))

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
(s/def ::in_measure_units  #{::g "Grams", ::kg "Kilograms", ::lt "Litres",
                             ::tsp "Tea Spoon", ::tbsp "Table Spoon", ::lb "Pound", ::mL "Milli Litres",
                             ::pt "pint", ::qt "Quart", ::ssp "Salt Spoon",
                             ::csp "Coffee Spoon", ::tcf "Tea Cup", ::wgf "Wine Glass",
                             ::gal "Gallon", ::S "Small", ::M "Medium",
                             ::L "Large", ::fl-oz "Fluid Ounce",
                             ::C "cup", ::cm "Centimeter", ::no "number", ::pot "Pottle",
                             ::ds "Dash", ::pn "Pinch", ::dr "Drop",
                             ::smdg "Smidgen", ::dsp "Dessert Spoon", ::sc "Scoop"})

;;Applicable for units of measures like cups or spoons..
(s/def ::in_measure_type  #{::Firmly_Packed ::Lightly_Packed ::Even
                            ::Rounded ::Heaped ::Sifted ::Approx ::Exact})



(s/def ::in_description string?)

;;broader food category the ingredient belongs to
(s/def ::in_category  #{::spice ::oil ::dairy ::fat
                        ::meat ::vegitable ::grain ::pulses ::dryfruit ::souce ::herb ::mix
                        ::flour ::fruit ::poultry ::juice ::bakery ::additive ::seafood ::drink
                        ::confection ::other})

;;form factor of an ingredient needed for recipe
(s/def ::IngredientFormFactor
  (s/keys
   :req-un [::in_form]
   :opt-un [::in_process_type ::in_form_details]))

(s/def ::in_form  #{::whole ::ground ::paste ::liquid ::usual
                    ::chopped ::cut ::powder ::grated ::minced})

(s/def ::in_process_type  #{::fresh ::ripe ::unripe ::raw ::dried ::salted
                            ::roasted ::fried
                            ::boiled ::pickled ::sugared ::baked ::grilled
                            ::stirred ::frozen ::boneless ::cleaned
                            ::canned ::stock ::marinated ::normal
                            ::seasoned ::extract ::usual ::none})


(s/def ::in_form_details  #{::small ::medium ::large
                            ::fine ::granular ::coarse
                            ::thick ::thin
                            ::hard ::soft
                            ::light ::strong
                            ::normal})

;;special instructions/attributes the ingredient need to satisfy
;;todo: need to make it multi value
(s/def ::in_special_instrns #{::glueten_free ::low_fat ::halal ::organic
                                       ::Kosher ::PureVeg ::lactose_free ::msg_free})

(s/def ::alergy_warning boolean?);;if this ingredient is a known alergy inducer
