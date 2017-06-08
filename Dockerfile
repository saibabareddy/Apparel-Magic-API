FROM java:8
EXPOSE 8080
ADD /target/ApparelMagicClient.jar ApparelMagicClient.jar
ENTRYPOINT ["java","-jar","ApparelMagicClient.jar"]