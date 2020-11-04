(ns com.mshiray.recipebcp.spec.kitchentool_specs
  (:require [clojure.spec.alpha :as s]
            ))

;;specs for kitchen/cooking tools & utensils

(s/def ::KitchenToolsCatalog (s/coll-of ::KitchenTool :min-count 1 :distinct true))

;;ingredient domian entity
(s/def ::KitchenTool ;;maps to retail product catalog item
  (s/keys
   :req [::tl_name ::tl_category]
   :opt [::tl_description]))

(s/def ::tl_name string?)

(s/def ::tl_category string?);;todo: define possible keywords

(s/def ::tl_description string?)
