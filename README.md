# Spring Kafka sample with 2 Kafka brokers
Sample Source code for the blog post 

*** Environment Setup: ***
Setup WSL2 on Windows with Ubuntu 20.04 LTS, then login to it.

Then set up 2 kafka broker on Wsl:
0. Download Kafka kafka_2.13-3.2.1 from : https://kafka.apache.org/downloads (https://downloads.apache.org/kafka/3.2.1/kafka_2.13-3.2.1.tgz)
1. Extract tgz file and clone to have 2 kafka server on the same Wsl Ubuntu (say, 20.04 LTS)
   In my case:
   drwxr-xr-x  8 tam  tam       4096 Aug 19 19:22 kafka_2.13-3.2.1/
   drwxr-xr-x  9 tam  tam       4096 Aug 20 10:17 kafka_2.13-3.2.1_SecondServer/ 
    Remember to chmod +x -R * (so that we can execute the .sh file)
2. Config the zookeeper and the server.properties
    
    * Kafka Server 1:
        + For zookeeper.properties: set clientPort=2181
        + For server.properties
          + zookeeper.connect=localhost:2181
          + listeners=PLAINTEXT://[::1]:9092
          + log.dirs=./kafka-logs (the log need to be changed to this path because default it is /tmp/kafka-logs will be conflict with another Kafka Server if we don't change)
    * Kafka Server 2:
        + For zookeeper.properties: set clientPort=2182
        + For server.properties
          + zookeeper.connect=localhost:2182
          + listeners=PLAINTEXT://[::1]:9094
          + log.dirs=./kafka-logs
          
3. Run both Kafka servers:
    Go to each Kafka folder above and then run
      + $ bin/zookeeper-server-start.sh config/zookeeper.properties      
      + $ bin/kafka-server-start.sh config/server.properties
   
Then we will have 2 Kafka brokers running at the same WSL host.
4. Create the topic for testing
   bin/kafka-topics.sh --create --topic topic1 --bootstrap-server [::1]:9092
   bin/kafka-topics.sh --create --topic topic2 --bootstrap-server [::1]:9094

5. Produce the messages
   bin/kafka-console-producer.sh --topic topic1 --bootstrap-server [::1]:9092
   bin/kafka-console-producer.sh --topic topic2 --bootstrap-server [::1]:9094

6. Consume the messages
   bin/kafka-console-consumer.sh --topic topic1 --from-beginning --bootstrap-server [::1]:9092
   bin/kafka-console-consumer.sh --topic topic2 --from-beginning --bootstrap-server [::1]:9094

Important note: The Event hub consumer in this demo needs this file to work: C:\jdk1.8.0_202\jre\lib\security\cacerts (means that if you rename/remove it the listenner cannot listen)