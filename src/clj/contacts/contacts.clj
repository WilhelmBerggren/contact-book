(ns contacts.contacts
  (:require [contacts.db :as db]))

(defn get-contacts 
  [_]
  {:status 200
   :body (db/get-contacts)})

(defn create-contact
  [{:keys [parameters]}]
  (let [data (:body parameters)
        created-id (db/insert-contact data)]
    {:status 201
     :body (db/get-contact-by-id created-id)}))

(defn update-contact
  [{:keys [parameters]}]
  (let [id (get-in parameters [:path :id])
        body (:body parameters)
        data (assoc body :id id)
        updated-count (db/update-contact-by-id data)]
    (if (= 1 updated-count)
      {:status 200
       :body (db/get-contact-by-id {:id id})}
      {:status 404
       :body {:error "Unable to update contact"}})))

(defn get-contact-by-id
  [{:keys [parameters]}]
  (let [id (:path parameters)]
    {:status 201
     :body (db/get-contact-by-id id)}))

(defn delete-contact
  [{:keys [parameters]}]
  (let [id (:path parameters)
        before-deleted (db/get-contact-by-id id)
        deleted-count (db/delete-contact-by-id id)]
    (if (= 1 deleted-count)
      {:status 200
       :body {:deleted true
              :contact before-deleted}}
      {:status 404
       :body {:error "Unable to delete contact"}})))

(comment
  (db/insert-contact {:first-name "John"
                                :last-name "Smith"
                                :email "john.smith@gmail.com"})
  (db/get-contact-by-id {:id 2}))