(ns com.mshiray.recipebcp.spec.common_specs
  ;;(:require [clojure.spec.alpha :as s])
  )

;;spec file to maintain reusable common specss & regexs..

;;user name regex  - all smalls alphanum with optional _ or - . No caps allowed.
(def username_rx #"^[a-z0-9_-]{3,15}$")

;;first name regex
(def fname_rx #"^[a-zA-Z]{2,30}$")
;;last name regex
(def lname_rx #"^[a-zA-Z]{1,30}$")

;;email regex
(def email_rx #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,63}$")

;;mobile number regex
(def mobile_num_rx #"^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$")

;;time in hh::mm format <=23:59
(def time_hh_mm_rx #"^[0-2][0-3]:[0-5][0-9]$")

;;date in dd/mm/yyyy
(def date_dd_mm_yyyy_rx #"(?:(?:31(\/)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(\/)(?:0?[13-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})")

;;language pref/ locale regex

(def locale_rx #"^[a-z]{2}(\_)[A-Z]{2}$")


