Based on the very helpful https://github.com/kelvin-mai/clj-contacts/
------------------

PS Microsoft.PowerShell.Core\FileSystem::\\wsl$\Ubuntu-18.04\home\wil\repo\contact-boo> docker container ls
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                    NAMES
d573eb47cd91        postgres:9.6        "docker-entrypoint.s…"   7 minutes ago       Up 7 minutes        0.0.0.0:5432->5432/tcp   clj_contacts_db
PS Microsoft.PowerShell.Core\FileSystem::\\wsl$\Ubuntu-18.04\home\wil\repo\contact-book> PS Microsoft.PowerShell.Core\FileSystem::\\wsl$\Ubuntu-18.04\home\wil\repo\contact-boo> docker exec -it clj_contacts_db psql -U postgres
psql (9.6.19)
Type "help" for help.

postgres=# \c clj_contacts
You are now connected to database "clj_contacts" as user "postgres".
clj_contacts=# \dt
No relations found.
clj_contacts=# \dt
          List of relations
 Schema |   Name   | Type  |  Owner
--------+----------+-------+----------
 public | contacts | table | postgres
(1 row)

clj_contacts=#