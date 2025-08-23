#From registry.bigtree.com:5000/bigtree/busybox:latest
#
#RUN mkdir -p /data/package
#ARG packagename
#COPY $packagename  /data/package
#
#CMD ["tail", "-F", "/dev/null"]








FROM    openjdk:17
VOLUME  /opt/fengshiqing
ADD     spring-cloud-seata.jar   spring-cloud-seata.jar
EXPOSE  8080
ENTRYPOINT ["java","-jar","/spring-cloud-seata.jar"]

# FROM openjdk:8 拉取一个 jdk 为 1.8 的依赖环境
# VOLUME 表示在哪个文件夹下
# fishexam.jar 是你将该 jar 包重新命名为什么名称，
# 在容器中运行 expose 该容器暴露的端口是多少，就是 jar 在容器中以多少端口运行
# ENTRYPOINT 容器启动之后执行的命令，java -jar /fishexam.jar 即启动 jar


