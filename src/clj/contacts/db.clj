(ns contacts.db
  (:require [honeysql.core :as sql]
            [honeysql.format :refer [comma-join format-clause to-sql]]
            [next.jdbc :as jdbc]))

(def config
  {:dbname "clj_contacts"
   :dbtype "postgresql"
   :user "postgres"
   :password "postgres"})

(def ds (jdbc/get-datasource
         config))

(defn run-query [query] (jdbc/execute! ds (sql/format (sql/build query))))

(defmethod
  format-clause
  :returning
  [[_ fields] _]
  (str "RETURNING " (comma-join (map to-sql fields))))

(defn get-contacts []
  (run-query {:select :*
              :from :contacts}))

(defn get-contact-by-id [id]
  (run-query {:select [:*]
              :from :contacts
              :where [:= :id id]}))

(defn insert-contact [contact]
  (run-query {:insert-into :contacts
              :values [contact]
              :returning :id}))

(defn update-contact-by-id [contact]
  (run-query {:update :contacts
              :set (select-keys contact [:first-name :last-name :id])
              :where [:= :id (:id contact)]}))

(defn delete-contact-by-id [contact]
  (run-query {:delete-from :contacts
              :where [:= :id (:id contact)]}))

(jdbc/execute! ds (sql/format {:select [:*]
                               :from [:contacts]}))