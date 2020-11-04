(ns com.mshiray.recipebcp.service.impl.RecipeServiceImpl

  (:import (com.mshiray.recipebcp.service.api.RecipeService RecipeService)))

;;implementation of RecipeService protocol
(deftype RecipeServiceImpl [recipe]

  ;;implement RecipeService protocol methods
  RecipeService

  (init [x]
    (println (:diet recipe))))