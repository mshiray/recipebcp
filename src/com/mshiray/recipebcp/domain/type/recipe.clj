(ns com.mshiray.recipebcp.domain.type.recipe
  (:gen-class)
  (:require [com.mshiray.recipebcp.domain.type.user])
  (:import  (com.mshiray.recipebcp.domain.type.user User)))

;;Domain entity type for recipe
(defrecord Recipe [^int r_RID
                   ^String r_name
                   ^User r_publisher
                   ^String r_description
                   ^String r_estTime
                   r_serves_no
                   r_diet
                   r_cuisine
                   r_category
                   r_taste
                   r_taste_meter
                   r_ingredients])


;;Recipe specific enums/ Value Object required & optional keywords

(def r_diet  #{::Vegan ::Vegiterian ::NonVeg ::Keto ::Other})

(def r_cuisine  #{::Indian ::Asian ::Universal ::Mexican ::Mediterranean
                  ::Chinese ::Thai ::Continetal ::Western
                  ::Japanese ::Moroccan ::British
                  ::Taiwanese ::French ::Italian ::Nordic
                  ::Scandinavian ::MiddleEastern ::Oceanian
                  ::Latin ::American ::African ::Korean ::Other})

(def r_category #{::Dish ::FastFood ::Soup ::Appetizer
                  ::Dessert ::Snack ::Bakery
                  ::Salad ::Munchies ::Masala ::Gravy ::Gourmet
                  ::Grill ::Stew ::Beverage ::cocktail ::Brew ::Confection ::Deli
                  ::Beverage-Alcoholic ::Pickle ::Dairy
                  ::Starter ::Bread ::Rice ::Noodles ::Breakfast ::Flour ::Souce})

(def r_taste  #{::Sweet ::Spicy ::Sour ::Salty ::Tangy ::Bitter ::Umami ::Other})

(def r_taste_meter  #{::T1 ::T2 ::T3 ::T4 ::T5})

(def r_serving_instrn  #{::hot ::chilled ::cold ::frozen ::warm})

(def r_religious_tag  #{::Kosher ::HinduNonVeg ::PureVeg ::HinduFast ::halal ::jain})