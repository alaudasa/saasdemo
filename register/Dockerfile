FROM registry.alauda.cn/alaudasa/centos6-jdk7-maven3-tomcat6

ADD pom.xml /pom.xml
RUN yum install -y which
RUN cd / && mvn dependency:go-offline

ADD . /saasdemo_register
RUN cd /saasdemo_register && \
	mvn package && \
 	rm -r $CATALINA_HOME/webapps/ROOT && \
 	mv /saasdemo_register/target/saasdemo-register.war $CATALINA_HOME/webapps/ROOT.war
