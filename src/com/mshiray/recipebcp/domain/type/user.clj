(ns com.mshiray.recipebcp.domain.type.user
  (:gen-class))

;;defreocrd for User domain entity type
(defrecord User [u_userId u_userName u_fname
                 u_lname u_mobileNo u_emailID u_dob u_regDt])


