(ns primeira-api-clj.core
  (:require [clojure.java.io :as io]
    [clojure.string :as str]))

(defn charset
  "Returns an updated Ring response with the supplied charset added to the
  Content-Type header."
  [resp charset]
  (update-in resp [:headers "Content-Type"]
    (fn [content-type]
      (-> (or content-type "text/plain")
          (str/replace #";\s*charset=[^;]*" "")
          (str "; charset=" charset)))))


(defn app [req]
  (charset {:status 200
    :headers {"content-Type" "text-html"}
    :body "Primeira requisição Clojure!"} "UTF-8"))

