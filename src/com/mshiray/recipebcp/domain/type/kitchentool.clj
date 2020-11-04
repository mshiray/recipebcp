(ns com.mshiray.recipebcp.domain.type.kitchentool

  (:gen-class))

;;domain entity record type for kitchen tools like oven, grinder, spatula, pan etc.
;;mapped to product catalog items from vendors.
(defrecord KitchenTool [name category description])