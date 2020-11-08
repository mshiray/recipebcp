(ns com.mshiray.recipebcp.domain.type.ingredient
  (:gen-class))

;;Domain entity type for ingredient

(defrecord IngredientFormFactor [in_form in_process_type in_form_details])

(defrecord IngredientSpecs [^IngredientFormFactor in_form in_special_instrns in_alergic])

(defrecord IngredientwSpecs [^String in_name in_category ^IngredientSpecs in_specs])

(defrecord Ingredient [in_name in_category])

(defrecord In_Measure [in_measure_no in_measure_units])


;;Innumerated value objects keywords definitions


;;broader food category the ingredient belongs to
(def in_category  #{::spice ::oil ::dairy ::fat
                    ::meat ::vegitable ::grain ::pulses ::dryfruit ::souce ::herb ::mix
                    ::flour ::fruit ::poultry ::juice ::bakery ::additive ::seafood ::drink
                    ::confection ::other})

(def in_form  #{::whole ::ground ::paste ::liquid ::usual
                ::chopped ::cut ::powder ::grated ::minced})

(def in_process_type  #{::fresh ::ripe ::unripe ::raw ::dried ::salted
                        ::roasted ::fried
                        ::boiled ::pickled ::sugared ::baked ::grilled
                        ::stirred ::frozen ::boneless ::cleaned
                        ::canned ::stock ::marinated ::normal
                        ::seasoned ::extract ::usual ::none})


(def in_form_details  #{::small ::medium ::large
                        ::fine ::granular ::coarse
                        ::thick ::thin
                        ::hard ::soft
                        ::light ::strong
                        ::normal})

;;special instructions/attributes the ingredient need to satisfy
;;todo: need to make it multi value
(def in_special_instrns #{::glueten_free ::low_fat ::halal ::organic
                          ::Kosher ::PureVeg ::lactose_free ::msg_free})


;;https://en.wikipedia.org/wiki/Cooking_weights_and_measures
(def in_measure_units  #{::g "Grams", ::kg "Kilograms", ::lt "Litres"
                         ::tsp "Tea Spoon", ::tbsp "Table Spoon", ::lb "Pound", ::mL "Milli Litres"
                         ::pt "pint", ::qt "Quart", ::ssp "Salt Spoon"
                         ::csp "Coffee Spoon", ::tcf "Tea Cup", ::wgf "Wine Glass"
                         ::gal "Gallon", ::S "Small", ::M "Medium"
                         ::L "Large", ::fl-oz "Fluid Ounce"
                         ::C "cup", ::cm "Centimeter", ::no "number", ::pot "Pottle"
                         ::ds "Dash", ::pn "Pinch", ::dr "Drop"
                         ::smdg "Smidgen", ::dsp "Dessert Spoon", ::sc "Scoop"})

;;Applicable for units of measures like cups or spoons..
(def in_measure_type  #{::Firmly_Packed ::Lightly_Packed ::Even
                        ::Rounded ::Heaped ::Sifted ::Approx ::Exact})