#!/bin/bash

# 脚本：监控 JAR 文件变化并自动重启
# 启动脚本，然后查看日志（必须在 /root/wisdom-nhri/target 目录下 执行）：
# nohup  ./wisdom-watch-dog.sh  >  wisdom-watch-dog.log  2>&1  &  tail -f  wisdom-watch-dog.log


cd /root/wisdom-nhri/target || exit


admin_jar="wisdom-admin.jar"
gateway_jar="wisdom-gateway.jar"
mis_external_jar="wisdom-mis-external.jar"



# 函数：启动 JAR 应用
function restart_app() {
    local jar_file=$1

    echo "---$(date): 【检测到 $jar_file 更新，正在重启应用...】"
    echo "----------------------------------------- 停止: $jar_file"
    pkill -f "$jar_file" || true
    sleep 3

    echo "----------------------------------------- 启动: $jar_file"
    nohup java -jar -Dspring.profiles.active=dev  -Djasypt.encryptor.password=abc  "$jar_file"    2>&1  &

    echo "----------------------------------------- 执行完毕，应用重启是否成功，请查看具体应用的日志"
    echo ""
    echo ""
    echo ""
}


# 监控目录中 JAR 文件的变动（关闭写入、移动覆盖等）
echo ""
echo "开始监控所有JAR文件的变化..."
echo ""
echo ""

# 使用单一 inotifywait 监控当前目录下的所有 jar 文件
# -m 持续监控模式，而非监控一次后退出
# -e close_write -e moved_to -e modify -e create 指定监控的事件类型：
#    close_write : 文件被写入并关闭
#    moved_to : 文件被移动到监控目录
#    modify : 文件内容被修改
#    create : 新文件被创建
inotifywait  -m -e close_write --format '%f'  /root/wisdom-nhri/target  |  while read  file; do
    case "$file" in
        "$gateway_jar")
            restart_app "$gateway_jar"
            ;;
        "$admin_jar")
            restart_app "$admin_jar"
            ;;
        "$mis_external_jar")
            restart_app "$mis_external_jar"
            ;;
    esac
done
