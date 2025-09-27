# java
JavaSE、JavaEE学习汇总

## springdoc的官网
https://springdoc.org/#Introduction

swagger文档地址:
http://192.168.2.102:9090/cloud-application-camunda/swagger-ui/index.html
http://192.168.2.102:9090/cloud-application-camunda/v3/api-docs


http://localhost:8080/swagger-ui.html

各种后端技术、平时看到的知识贴、视频教程，都在这里验证下。

JUC教程
https://www.bilibili.com/video/BV18z4y1F7my/?p=38&spm_id_from=333.880.my_history.page.click&vd_source=f2fb919142ce62e6571426a12817634e

分库分表看这里：
https://www.bilibili.com/video/BV12A4y197sa/?spm_id_from=333.788&vd_source=f2fb919142ce62e6571426a12817634e



### 已commit 未 push，如何回退？
执行：git reset --hard HEAD^，可以回退 上一次的 commit，撤销commit后，之前的修改不会保留在本地，都被还原了。
执行：git reset --soft HEAD^，可以回退 上一次的 commit，撤销commit后，之前的修改会保留在本地，可以直接重新发起commit。

