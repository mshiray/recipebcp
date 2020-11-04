(ns com.mshiray.recipebcp.service.api.RecipeService
  (gen-class)

  (:import (com.mshiray.recipebcp.domain.type.recipe Recipe)))

;;recipe domain service API signatures

(defprotocol RecipeService
  (init [^Recipe r])
  )