(ns com.mshiray.recipebcp.domain.type.recipe
  (:gen-class)
  (:import (com.mshiray.recipebcp.domain.type.user User)))
;; => nil


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



