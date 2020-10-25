(ns contacts.db
  (:require [hugsql.core :as hugsql]))

(def config 
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname "//localhost:5432/clj_contacts"
   :user "postgres"
   :password "postgres"})

(hugsql.core/def-db-fns "contacts.sql")

(comment
  config
  (create-contacts-table config)
  (get-contacts config)
  (delete-contact-by-id config {:id 2})
  (get-contact-by-id config {:id 1})
  (insert-contact config {:first-name "anti"
                          :last-name "wilhlem"
                          :email "anti@wil.helm"}))