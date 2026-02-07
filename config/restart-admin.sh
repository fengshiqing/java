#!/bin/bash


cd /root/wisdom-nhri/target
pkill -f wisdom-admin.jar || true
sleep 2

#  > logs/wisdom-admin.log：将 标准输出 (stdout) 重定向到 logs/wisdom-admin.log 文件。
#  2>&1：将 标准错误 (stderr) 也重定向到同一个文件（即 logs/wisdom-admin.log）。
# nohup java -jar  -Dspring.profiles.active=prod  wisdom-admin.jar > logs/wisdom-admin.log 2>&1 &
# 配置了 logback 后，无需打印控制台日志了
nohup java -jar  -Dspring.profiles.active=dev  -Djasypt.encryptor.password=abc  wisdom-admin.jar  >  /dev/null 2>&1  &  tail -f  ../logs/wisdom-admin.log
#  > /dev/null 2>&1 表示丢弃所有控制台输出。因为你的 Logback 已经配置了文件输出，所以不需要再捕获控制台日志。

echo "--------重启 admin 成功。"