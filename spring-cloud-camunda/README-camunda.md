

## 整合 Camunda流程引擎
官方文档：
https://docs.camunda.org/manual/7.18/user-guide/process-engine/process-engine-api/
官方社区论坛：
https://camunda.com/blog/category/community/

创建账户：
http://127.0.0.1:9090/camunda/app/admin/default/setup/#/setup

登录：
http://127.0.0.1:9090/camunda/app/admin/default/#/login


#### 概念
流程（PROCESS） : 通过工具建模最终生成的BPMN文件，里面有整个流程的定义。</p>
流程实例（Instance） ：流程启动后的实例。</p>
流程变量（Variables） ：流程任务之间传递的参数。</p>
任务（TASK） ：流程中定义的每一个节点。</p>
流程部署 ：将之前流程定义的.bpmn文件部署到工作流平台。</p>


流程管理：
http://127.0.0.1:9090/camunda/app/cockpit/default/#/login
http://127.0.0.1:9090/camunda/app/cockpit/default/#/dashboard

画流程图小技巧：
每个节点的命名，带上流程图的前缀名称，尽量不要使用自动生成的随机数/随机码，方便在数据库的表里看数据。



ACT_ID_ 这部分表示用户模块，配置文件里面的用户，信息就在此模块
ACT_HI_ 表示流程历史记录
* act_hi_actinst： 执行的活动历史
* act_hi_taskinst：执行任务历史
* act_hi_procinst：执行流程实例历史
* act_hi_varinst：流程变量历史表

ACT_RE_ 表示流程资源存储
* act_re_procdef：流程定义存储
* act_re_deployment: 自动部署，springboot每次启动都会重新部署，生成记录

ACT_RU_ 表示流程运行时表数据，流程结束后会删除
* act_ru_execution：运行时流程实例，这个我觉得比较重要，发起的正在运行中的流程，都在这个表里面！！！！！！！！！！！！！
* act_ru_task：运行时的任务
* act_ru_variable：运行时的流程变量

ACT_GE_ 流程通用数据

act_ge_bytearray：每次部署的文件2进制数据，所以如果文件修改后，重启也没用，因为重新生成了记录，需要清掉数据库，或者这个表记录
登录界面



http://127.0.0.1:9090/camunda/app/cockpit/default/#/processes?pdSearchQuery=%5B%5D
这个页面的流程定义部署的，对应着 ACT_RE_PROCDEF 表的数据

ACT_GE_BYTEARRAY 表，存放流程的定义。
ACT_HI_ACTINST 表，存放流程实例经过了哪些节点，下一个节点是什么节点，是谁处理等信息


ACT_RU_TASK

ACT_RU_EXECUTION

