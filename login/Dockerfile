FROM registry.alauda.cn/alaudasa/centos6-jdk7-maven3-tomcat6

ADD pom.xml /pom.xml
RUN yum install -y which
RUN cd / && mvn dependency:go-offline

ADD . /saasdemo_login
RUN cd /saasdemo_login && \
	mvn package && \
 	rm -r $CATALINA_HOME/webapps/ROOT && \
 	mv /saasdemo_login/target/saasdemo-login.war $CATALINA_HOME/webapps/ROOT.war
