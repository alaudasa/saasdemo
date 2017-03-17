FROM  mysql
COPY  entrypoint.sh  /usr/local/bin/docker-entrypoint.sh
COPY  initdb.sql  /docker-entrypoint-initdb.d/ 
#COPY  replace.sh   /docker-entrypoint-initdb.d/ 
#RUN chmod 77 /docker-entrypoint-initdb.d/replace.sh

