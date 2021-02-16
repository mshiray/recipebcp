(ns com.mshiray.recipebcp.spec.recipe_specs
  (:require [clojure.spec.alpha :as s]
            [com.mshiray.recipebcp.spec [common_specs :as cs]
             [user_specs :as us]
             [ingredient_specs :as is]
             [kitchentool_specs :as ks]]

            [com.mshiray.recipebcp.domain.type [recipe :as r]
             ;;[ingredient :as i]
             ;;[user :as u]
             ;;[kitchentool :as kt]
             ]))

;;collection catalog of recipes
(s/def ::RecipesCatalog (s/coll-of ::Recipe :min-count 1 :distinct true))

;;recipe domain entity
(s/def ::Recipe
  (s/keys
   :req-un [::r_RID ::r_name ::r_publisher ::r_description ::r_estTime ::r_serves_no
            ::r_diet ::r_cuisine ::r_category ::r_taste ::r_taste_meter ::r_ingredients]
   :opt-un [::r_tools ::r_cuisine_regional ::r_religious_tag ::r_serving_instrn ::r_companions]))

;;unique recipe identity number
(s/def ::r_RID pos-int?)

(s/def ::r_name string?)

(s/def ::r_publisher ::us/User)

(s/def ::r_description string?)

;;estimated preparation time in hh:mm
(s/def ::r_estTime (s/and string? #(re-matches cs/time_hh_mm_rx %)))

(s/def ::r_diet r/r_diet)

;;religious meal/procedure compliance
(s/def ::r_religious_tag  r/r_religious_tag)

;;cuisine type
(s/def ::r_cuisine r/r_cuisine)

;;user/app defined custome regional cuisine type e.g. north indian,goan etc.
(s/def ::r_cuisine_regional keyword?)

(s/def ::r_category r/r_category)

(s/def ::r_taste  r/r_taste)

;;tastometer for specieid taste e.g. mild spicy, medium spicy, hot
(s/def ::r_taste_meter  r/r_taste_meter)

;;preferred serving temperature
(s/def ::r_serving_instrn  r/r_serving_instrn)

;;no. of people this recipe is measured for
(s/def ::r_serves_no pos-int?)

(s/def ::r_ingredients (s/coll-of ::is/Ingredient :distinct true :min-count 1))

(s/def ::r_tools (s/coll-of ::ks/KitchenTool :distinct true :min-count 1))

;;optional companion recipes with which the recipe is usually cooked/served with. Range between 1-10
(s/def ::r_companions (s/coll-of ::Recipe :distinct true))
