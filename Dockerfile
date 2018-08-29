FROM centos:7

#--------------------------------------------------------------------------------------------------------------------------
#SETUP JDK
ENV JAVA_VERSION=8u131
ENV JAVA_URL=http://download.oracle.com/otn-pub/java/jdk/8u131-b11/d54c1d3a095b4ff2b6607d096fa80163/jdk-8u131-linux-x64.rpm

RUN curl -fsSL --header "Cookie: oraclelicense=accept-securebackup-cookie" $JAVA_URL > jdk-$JAVA_VERSION-linux-x64.rpm \
    && rpm -ivh jdk-$JAVA_VERSION-linux-x64.rpm \
        && rm jdk-$JAVA_VERSION-linux-x64.rpm
ENV JAVA_HOME=/usr/java/jdk1.8.0_131
RUN mkdir /appdata
ADD . /appdata/textExtraction
WORKDIR "/appdata/textExtraction/target"
CMD ["java","-jar","graphql-1.0-SNAPSHOT.jar"]
