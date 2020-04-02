FROM openjdk:8-jdk-alpine
EXPOSE 8080

VOLUME /tmp
ADD /xyblog-search/target/xyblog.jar /app.jar
RUN bash -c 'touch /app.jar'
RUN /sbin/ip route|awk '/default/ { print  $3,"\tdockerhost" }' >> /etc/hosts
ENTRYPOINT ["java","-jar","/app.jar"]
