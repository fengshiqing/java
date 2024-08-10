# java
JavaSE、JavaEE学习汇总

各种后端技术、平时看到的知识贴、视频教程，都在这里验证下。

JUC教程
https://www.bilibili.com/video/BV18z4y1F7my/?p=38&spm_id_from=333.880.my_history.page.click&vd_source=f2fb919142ce62e6571426a12817634e

分库分表看这里：
https://www.bilibili.com/video/BV12A4y197sa/?spm_id_from=333.788&vd_source=f2fb919142ce62e6571426a12817634e


# 项目中接入的中间件

## mysql


## redis


## nacos
启动：bash /opt/nacos-2.3.0/bin/startup.sh -m standalone

停止：bash /opt/nacos-2.3.0/bin/shutdown.sh

管理台页面：http://192.168.10.100:8848/nacos



## kafka（包含zookeeper）
1、启动 zookeeper
bash /opt/kafka_2.13-3.6.1/bin/zookeeper-server-start.sh -daemon  /opt/kafka_2.13-3.6.1/config/zookeeper.properties
2、启动 kafka
bash /opt/kafka_2.13-3.6.1/bin/kafka-server-start.sh     -daemon  /opt/kafka_2.13-3.6.1/config/server.properties

1、停止 kafka
bash /opt/kafka_2.13-3.6.1/bin/kafka-server-stop.sh
2、停止 zookeeper
bash /opt/kafka_2.13-3.6.1/bin/zookeeper-server-stop.sh



## elasticsearch
启动：nohup /opt/elasticsearch-8.14.2/bin/elasticsearch > /opt/runlogs/es-log/run.log  &

停止：sudo kill -9 $(ps aux | grep 'elasticsearch' | awk '{print $2}')



## 整合 Camunda流程引擎
创建账户：
http://127.0.0.1:8080/camunda/app/admin/default/setup/#/setup

登录：
http://127.0.0.1:8080/camunda/app/admin/default/#/login

流程管理：
http://127.0.0.1:8080/camunda/app/cockpit/default/#/login

画流程图小技巧：
每个节点的命名，带上流程图的前缀名称，尽量不要使用自动生成的随机数/随机码，方便在数据库的表里看数据。

