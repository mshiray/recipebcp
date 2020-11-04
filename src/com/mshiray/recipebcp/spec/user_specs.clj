(ns com.mshiray.recipebcp.spec.user_specs
  (:require [clojure.spec.alpha :as s])
  (:require [com.mshiray.recipebcp.spec.common_specs :as cs]))

;;recipe domian entity
(s/def ::User
  (s/keys
   :req-un [::u_userId ::u_userName ::u_fname ::u_lname ::u_mobileNo ::u_emailID ::u_dob ::u_regDt]
   :opt-un [::u_langPref]))

;;user id is a positive integer
(s/def ::u_userId int?)

;;username is all small alphanumeric string without space
(s/def ::u_userName (s/and string? #(re-matches cs/username_rx %)))

(s/def ::u_fname (s/and string? #(re-matches cs/fname_rx %)))
(s/def ::u_lname (s/and string? #(re-matches cs/lname_rx %)))

(s/def ::u_emailID (s/and string? #(re-matches cs/email_rx %)))
(s/def ::u_mobileNo (s/and string? #(re-matches cs/mobile_num_rx %)))

;;date of birth
(s/def ::u_dob (s/and string? #(re-matches cs/date_dd_mm_yyyy_rx %)))

;;date of registration
(s/def ::u_regDt (s/and string? #(re-matches cs/date_dd_mm_yyyy_rx %)))

;;language preference
(s/def ::u_langPref (s/and string? #(re-matches cs/locale_rx %)))