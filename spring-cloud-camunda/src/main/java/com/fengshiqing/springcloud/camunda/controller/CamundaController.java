/*
 * Copyright (c) 2025. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.camunda.controller;

import com.fengshiqing.common.model.Resp;
import com.fengshiqing.common.model.RespData;
import com.fengshiqing.springcloud.camunda.dao.UserFlowMapper;
import com.fengshiqing.springcloud.camunda.dao.entity.UserFlowEntity;
import com.fengshiqing.springcloud.camunda.utils.I18nUtil;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Comment;
import org.camunda.bpm.engine.task.Task;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

@AllArgsConstructor
@Slf4j
@RestController
public class CamundaController {

    private final RepositoryService repositoryService;

    private final IdentityService identityService;

    private final RuntimeService runtimeService;

    private final TaskService taskService;

    private final UserFlowMapper userFlowMapper;


    /**
     * 功能描述：删除流程部署。
     * 说明：如果有运行中的实例，必须调用 deleteDeploymentCascade 方法，或者调用 带有 cascade 参数的重载方法
     *
     * @param deploymentId 对应管理台的流程定义中的 "Deployment ID" 字段，示例：4c01058a-58a8-11ef-be97-f8b46aafbdd4
     * @return Resp
     */
    @GetMapping("/deleteDeployment")
    public Resp deleteDeployment(@RequestParam(value = "deploymentId") String deploymentId) {
        log.info("【deleteDeployment】【start】【deploymentId：{}】", deploymentId);
        // 这将删除与特定部署关联的所有流程定义，即使其中一些可能还在运行。
        repositoryService.deleteDeployment(deploymentId);
        log.info("【deleteDeployment】【end】");
        return new Resp(200, I18nUtil.getMessage("biz.operate.success"));
    }


    /**
     * 功能描述：删除流程定义。
     * 注意：不能有运行中的实例，否则会删除失败
     *
     * @param definitionId 对应管理台的流程定义中的 "Definition ID" 字段，示例：over_time_flow:3:4c2d45ad-58a8-11ef-be97-f8b46aafbdd4
     * @return Resp
     */
    @GetMapping("/deleteDefinition")
    public Resp deleteProcessDefinition(@RequestParam(value = "definitionId") String definitionId) {
        log.info("【deleteProcessDefinitions】【start】【definitionId：{}】", definitionId);
        // 这将删除与特定部署关联的所有流程定义，即使其中一些可能还在运行。
        repositoryService.deleteProcessDefinition(definitionId);
        log.info("【deleteProcessDefinition】【end】");
        return new Resp(200, I18nUtil.getMessage("biz.operate.success"));
    }


    /**
     * 功能描述：创建流程实例
     *
     * @param processDefKey：这里首先需要传入的是流程定义模板的key，前提是我们之前已经部署了相应的流程模板。(Process_0bhiqm1)
     * @param businessKey：这是流程实例的业务标识Key，需要用该字段与我们的业务单据进行绑定。
     * @param assignee：启动流程实例时的操作人，这里可以理解为制单人，或者是送审人，但是需要注意，在实际应用场景中，我们的制单人不一定就是单据送审人。
     *
     * @return String
     */
    @GetMapping("/ask_for_leave_flow_start")
    // rollbackFor 属性不写时，代码中出现了空指针等异常，会被回滚，文件读写，网络出问题，spring就没法回滚了。
    // java里面将派生于Error或者RuntimeException（比如空指针，1/0）的异常称为unchecked异常，其他继承自java.lang.Exception得异常统称为Checked Exception，如IOException、TimeoutException等。
    // 然后我教大家怎么记这个，因为很多同学容易弄混：你写代码的时候有些IOException我们的编译器是能够检测到的，说以叫checked异常，你写代码的时候空指针等死检测不到的，所以叫unchecked异常
    @Transactional(rollbackFor = Exception.class)
    public RespData<String> askForLeaveFlowStart(
            @NotBlank @RequestParam(value = "processDefKey") String processDefKey,
            @NotBlank @RequestParam(value = "businessKey") String businessKey,
            @NotBlank @RequestParam(value = "assignee") String assignee
    ) {
        log.info("【请假流程】【start】【processDefKey：{}，business：{}，assignee：{}】", processDefKey, businessKey, assignee);

        HashMap<String, Object> varMap = new HashMap<>();
        //流程启动初始化数据
        varMap.put("assignee", assignee);
        identityService.setAuthenticatedUserId(assignee); // ACT_HI_PROCINST.START_USER_ID字段的赋值-开始节点人
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("ask_for_leave_flow", businessKey, varMap);
        String processInstanceId = instance.getProcessInstanceId(); // ACT_HI_ACTINST 表的 PROC_INST_ID_
        log.info("【请假流程】【end】【processInstanceId：{}】", processInstanceId);

        userFlowMapper.insert(new UserFlowEntity("fengshiqing", processInstanceId, "ask_for_leave_flow", assignee, "结婚办酒席"));

        return new RespData<>(processInstanceId);
    }


    /**
     * 功能描述：创建 "加班流程" 流程实例
     *
     * @param businessKey：这是流程实例的业务标识Key，需要用该字段与我们的业务单据进行绑定。
     * @param assignee：启动流程实例时的操作人，这里可以理解为制单人，或者是送审人，但是需要注意，在实际应用场景中，我们的制单人不一定就是单据送审人。
     */
    @GetMapping("/over_time_flow")
    public RespData<String> overTimeFlow(@NotBlank @RequestParam(value = "businessKey") String businessKey,
                                         @NotBlank @RequestParam(value = "assignee") String assignee) {
        HashMap<String, Object> varMap = new HashMap<>();
        //流程启动初始化数据
        varMap.put("assignee", assignee);
        identityService.setAuthenticatedUserId(assignee); //ACT_HI_PROCINST.START_USER_ID字段的赋值-开始节点人
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("over_time_flow", varMap);
        String processInstanceId = instance.getProcessInstanceId(); // ACT_HI_ACTINST 表的 PROC_INST_ID_
        return new RespData<>(processInstanceId);
    }


    /**
     * 功能描述：提交申请
     *
     * @param leaveDays：模板中定义的数据变量。
     * @param businessKey：这是流程实例的业务标识Key，需要用该字段与我们的业务单据进行绑定。
     * @param assignee：单据送审人，也就是申请人
     */
    @GetMapping("/ask_for_leave_flow_user_apply")
    public String submitApplication(@NotBlank @RequestParam(value = "leaveDays") Long leaveDays,
                                    @NotBlank @RequestParam(value = "businessKey") String businessKey,
                                    @NotBlank @RequestParam(value = "assignee") String assignee) {
        String resultString;
        Task task = queryTaskByBusinessKey(businessKey, assignee);
        if (task == null) {
            log.info("【没有查询到对应的单据流程】");
            resultString = "没有查询到对应的单据流程";
        } else if (!task.getAssignee().equalsIgnoreCase(assignee)) {
            resultString = "没有审核权限！";
        } else {
            //设置审核人
            HashMap<String, Object> map = new HashMap<>();
            map.put("reason", "请假");
            map.put("leaveDays", leaveDays);

            List<String> leaders = new ArrayList<>();
            if (leaveDays < 5) {
                leaders.add("zhangsan");
            } else if (leaveDays > 5 && leaveDays <= 10) {
                leaders.add("zhangsan");
                leaders.add("lisi");
            } else if (leaveDays > 10) {
                leaders.add("zhangsan");
                leaders.add("lisi");
                leaders.add("wangwu");
            }

            map.put("leader", leaders);
            String taskId = task.getId();
            taskService.setVariables(taskId, map);
            taskService.complete(taskId);
            resultString = "请假成功！";
        }
        return resultString;
    }


    /**
     * 功能描述：审核操作
     *
     * @param businessKey 业务id
     * @param assignee 流程处理人
     * @param remark 备注/处理意见
     */
    @GetMapping("/approveOne")
    public String approveOne(
            @NotBlank @RequestParam(value = "businessKey") String businessKey,
            @NotBlank @RequestParam(value = "assignee") String assignee,
            @NotBlank @RequestParam(value = "result") String result,
            @RequestParam(value = "remark") String remark
    ) {
        String resultString = "";
        Task task = queryTaskByBusinessKey(businessKey, assignee);
        if (task == null) {
            resultString = "没有查询到对应的单据流程";
        } else if (!task.getAssignee().equalsIgnoreCase(assignee)) {
            resultString = "没有审核权限！";
        } else {
            if (task.getAssignee().equals("zhangsan")) {
                Map<String, Object> variables = taskService.getVariables(task.getId());
                System.out.println("variables" + variables.toString());
                HashMap<String, Object> map = new HashMap<>();
                map.put("flag", result);
                map.put("remark", remark);
                taskService.createComment(task.getId(), task.getProcessInstanceId(), "审核原因：" + remark);
                taskService.complete(task.getId(), map);
                return "zhangsan" + (result.equals("true") ? "同意" : "不同意");
            } else if (task.getAssignee().equals("lisi")) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("flag", result);
                map.put("remark", remark);
                taskService.createComment(task.getId(), task.getProcessInstanceId(), "审核原因：" + remark);
                taskService.complete(task.getId(), map);
                return "lisi" + (result.equals("true") ? "同意" : "不同意");
            } else if (task.getAssignee().equals("wangwu")) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("flag", result);
                map.put("remark", remark);
                taskService.createComment(task.getId(), task.getProcessInstanceId(), "审核原因：" + remark);
                taskService.complete(task.getId(), map);
                return "wangwu" + (result.equals("true") ? "同意" : "不同意");
            }

        }
        return resultString;
    }

    /**
     * 功能描述：根据业务标识代码获取当前节点
     *
     * @param businessKey 业务id
     * @param assignee 流程处理人或者流程制作人
     */
    private Task queryTaskByBusinessKey(String businessKey, String assignee) {
        Task task;
        // 冯仕清：我跟了下源码：org.camunda.bpm.engine.impl.persistence.entity.ExecutionManager#findProcessInstancesByQueryCriteria，发现查询的是 ACT_RU_EXECUTION 表
        ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey).singleResult();
        if (instance == null) return null;
        List<Task> list = taskService.createTaskQuery().processInstanceId(instance.getProcessInstanceId()).active().list(); // 查询正在运行时的流程实例ID，我这边存储到了 t_user_flow 表中，可以直接查询出来的

        if (list.size() == 1) {
            task = list.get(0);
        } else {
            task = taskService.createTaskQuery().processInstanceId(instance.getProcessInstanceId()).active()
                    .taskAssignee(assignee).singleResult();
        }
        return task;
    }


    private final HistoryService historyService;

    /**
     * 功能描述：查询我创建的流程
     *
     * @param userId 创建流程人的用户ID
     * @return List<String>
     */
    @GetMapping("/queryMySalaryProcess/{userId}")
    public List<String> queryMySalaryProcess(@PathVariable(value = "userId") String userId) {
        /* 迭代【可添加更多种条件查询】:时间范围、内置分页查询等！ */
        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery()
                //通过制单人来查询流程中的数据
                .startedBy(userId)
                .list();
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList<String> businessKeyList = new ArrayList<>();
        list.forEach(procInst -> businessKeyList.add(procInst.getBusinessKey()));
        return businessKeyList;
    }


    /**
     * 功能描述：查询已办/未办 单据
     *
     * @param userId 用户id
     * @param type   数据类型
     */
    @GetMapping("/queryMyTodoTask")
    public List queryMyTodoTask(@RequestParam(value = "userId") String userId,
                                @RequestParam(value = "type") String type) {
        ArrayList<Object> businessList = new ArrayList<>();
        //查询代办
        if (type.equalsIgnoreCase("0")) {
            List<Task> tasks = taskService.createTaskQuery()
                    .taskAssignee(userId)
                    .active()
//                    .listPage() 【可分页查询】
                    .list();
            if (tasks == null || tasks.isEmpty()) {
                return null;
            }
            tasks.forEach(task -> {
                //为查询到相关单据
                String businessKey = historyService.createHistoricProcessInstanceQuery()
//                        .orderByProcessInstanceStartTime() 【可添加时间查询范围】
//                        .orderByProcessInstanceEndTime()
                        .processInstanceId(task.getProcessInstanceId())
                        .singleResult()
                        .getBusinessKey();
                businessList.add(businessKey);
            });
        }
        //查询已办
        else if (type.equalsIgnoreCase("1")) {
            List<HistoricTaskInstance> completedTaskList = historyService.createHistoricTaskInstanceQuery()
                    .taskAssignee(userId)
                    .finished()
                    .taskDeleteReason("completed")
                    .list();
            if (completedTaskList == null || completedTaskList.isEmpty()) {
                //为查询到相关单据
                return null;
            }
            completedTaskList.forEach(taskOld -> {
                String businessKey = historyService.createHistoricProcessInstanceQuery()
//                    .orderByProcessInstanceStartTime()  【可添加时间查询范围】
//                    .orderByProcessInstanceEndTime()
                        .processInstanceId(taskOld.getProcessInstanceId())
                        .singleResult()
                        .getBusinessKey();
                businessList.add(businessKey);
            });
        }
        return businessList;
    }

    /**
     * 功能描述：驳回操作
     */
    @GetMapping("/turnTask")
    public String turnTask(@RequestParam(value = "userId") String userId,
                           @RequestParam(value = "businessKey") String businessKey,
                           @RequestParam(value = "type") String type,
                           @RequestParam(value = "remark") String remark) {
        Task task = queryTaskByBusinessKey(businessKey, userId);
        if (task == null) {
            return "未查询到对应的审核单据！";
        }
        if (!task.getAssignee().equalsIgnoreCase(userId)) {
            return "没用审核权限！";
        }
        //获取所有已办节点
        List<HistoricActivityInstance> userTaskList = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                //用户类型节点
                .activityType("userTask")
                .finished() //已经完成的节点
                .orderByHistoricActivityInstanceEndTime()
                .asc()
                .list();
        //流程实例的活动实例树
        /* ActivityInstance tree = runtimeService.getActivityInstance(task.getProcessInstanceId()); */

        if (userTaskList == null || userTaskList.isEmpty()) {
            return "当前任务无法驳回！";
        }
        switch (type) {
            //驳回第一任制单人
            case "1": {
                // 1.驳回提交已经无  ok2.驳回第一任还是驳回的上一任  3.会签节点上的驳回操作出现只结束了当前任务的驳回！
                if (userTaskList.size() < 2) {
                    return "第一个用户节点无法驳回！";
                }
                HistoricActivityInstance historicActivityInstance = userTaskList.get(0);
                String toActId = historicActivityInstance.getActivityId();
                String assignee = historicActivityInstance.getAssignee();
                //设置流程可变参数
                HashMap<String, Object> taskVariable = new HashMap<>();
                taskVariable.put("assignee", assignee);
                //流程审核+驳回
                //任务流程创建了提交模板Comment 但是没有提交 taskService.complete(taskId)。--所有节点也不会提交到后台去
                taskService.createComment(task.getId(), task.getProcessInstanceId(), "驳回原因：" + remark);
                //任务流程实例修改位置
                runtimeService.createProcessInstanceModification(task.getProcessInstanceId())
//                        .cancelActivityInstance(getInstanceIdForActivity(tree,task.getTaskDefinitionKey())) //关闭当前节点相关的任务
                        .cancelAllForActivity(task.getTaskDefinitionKey())
                        //暂时不清楚该内容提交到何处！
                        .setAnnotation("进行了驳回到第一任务节点操作！")
                        //启动目标活动节点
                        .startBeforeActivity(toActId)
                        .setVariables(taskVariable)
                        .execute();
                return "驳回到制单人成功！";
            }
            //驳回上一任
            case "2": {
                //判断当前节点是否为第一个节点
                HistoricActivityInstance historicActivityInstance = userTaskList.get(0);
                String activityId = historicActivityInstance.getActivityId();
                if (activityId.equals(task.getTaskDefinitionKey())) {
                    return "第一节点无法驳回！";
                }
                //获取上一个节点
                Map<String, String> lastNode = getLastNode(userTaskList, task.getTaskDefinitionKey());
                if (lastNode == null) {
                    return "退回节点异常！";
                }
                String toActId = lastNode.get("toActId");
                String assignee = lastNode.get("assignee");
                //设置流程中的可变参数
                HashMap<String, Object> taskVariable = new HashMap<>(2);
                taskVariable.put("leader", assignee);
                //进行驳回操作
                taskService.createComment(task.getId(), task.getProcessInstanceId(), "驳回：" + remark);
                runtimeService.createProcessInstanceModification(task.getProcessInstanceId())
                        //.cancelActivityInstance(getInstanceIdForActivity(tree,task.getTaskDefinitionKey())) //关闭相关任务！(当前节点就会被删除。但是之前审核过的节点还是会存在！)
                        //该方式关闭所有activityId相同的activity活动都会被取消暂停（会签节点）
                        .cancelAllForActivity(task.getTaskDefinitionKey())
                        .setAnnotation("进行驳回到上一任务节点操作！")
                        //启动目标活动节点
                        .startBeforeActivity(toActId)
                        //流程可变参数赋值
                        .setVariables(taskVariable)
                        .execute();
                return "驳回上一任成功！";
            }
            //驳回任一任
            case "3": {
                //
            }
            default: {
            }
        }
        return null;
    }

    private Map<String, String> getLastNode(List<HistoricActivityInstance> resultList, String currentActivityId) {
        HashMap<String, String> backNode = new HashMap<>();
        //新建一个有序不重复集合
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        for (HistoricActivityInstance his : resultList) {
            linkedHashMap.put(his.getActivityId(), his.getAssignee());
        }
        int originSize = resultList.size();
        //判断历史节点中是否已经存在过当前节点
        boolean flag = false;
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            if (currentActivityId.equalsIgnoreCase((String) entry.getKey())) {
                flag = true;
                break;
            }
        }
        //当前节点不在历史节点里面，最后一个节点是完成节点
        if (!flag) {
            HistoricActivityInstance historicActivityInstance = resultList.get(originSize - 1);
            backNode.put("toActId", historicActivityInstance.getActivityId());
            backNode.put("assignee", historicActivityInstance.getAssignee());
            return backNode;
            //当前节点在历史节点中（已经退回过）
        } else {
            ListIterator<Map.Entry<String, String>> li = new ArrayList<>(linkedHashMap.entrySet()).listIterator();
            while (li.hasNext()) {
                Map.Entry<String, String> entry = li.next();
                if (currentActivityId.equalsIgnoreCase(entry.getKey())) {
                    //光标上一到当前节点
                    li.previous();
                    //当前相同节点的上一节点
                    Map.Entry<String, String> previous = li.previous();
                    backNode.put("toActId", previous.getKey());
                    backNode.put("assignee", previous.getValue());
                    return backNode;
                }
            }
        }
        return null;
    }


    /**
     * 功能描述：审核日志查询
     * [注:日志顺序  1开始时间 相同顺延 2排列结束时间]
     * activityType：节点类型 null就不显示
     * taskId：taskId相同的为会签节点
     * state：completed审核完成  deleted驳回  null待审核
     * */
    @GetMapping("/queryProcessLog/{businessKey}")
    public List queryProcessLog(@PathVariable(value = "businessKey") String businessKey) {
        String processInstanceId = historyService.createHistoricProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult()
                .getRootProcessInstanceId();
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceStartTime() //这里开始时间相同的可以，只能前端在根据结束时间继续排序
                .asc()
                .list();
        List<Map<String,Object>> result=new ArrayList<>(list.size());
        System.out.println(list.size());
        for (HistoricActivityInstance historicActivityInstance : list) {
            Map<String,Object> map=new HashMap<>();
            String taskId = historicActivityInstance.getTaskId();
            List<Comment> taskComments = taskService.getTaskComments(taskId);
            System.out.println("taskId = " + taskId);
            System.out.println(taskComments.size());
            map.put("activityName",historicActivityInstance.getActivityName());
            System.out.println("historicActivityInstance.getActivityType() = " + historicActivityInstance.getActivityType());
            map.put("activityType",matching(historicActivityInstance.getActivityType()));
            map.put("assignee",historicActivityInstance.getAssignee()==null?"无":historicActivityInstance.getAssignee());
            map.put("taskId",historicActivityInstance.getTaskId());
            map.put("act_Id",historicActivityInstance.getActivityId());
            /*加入activity状态字段*/
            if (taskId != null) {
                map.put("state",historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult().getDeleteReason());
            }
            Date startTime = historicActivityInstance.getStartTime();
            if (startTime == null){
                map.put("starTime","");
            }
            else{
                map.put("startTime", DateFormatUtils.format(historicActivityInstance.getStartTime(),"yyyy-MM-dd HH:mm:ss") );
            }
            Date endTime = historicActivityInstance.getEndTime();
            if (endTime == null){
                map.put("endTime","");
                map.put("costTime","");
            }else {
                map.put("endTime",DateFormatUtils.format(historicActivityInstance.getEndTime(),"yyyy-MM-dd HH:mm:ss"));
                map.put("costTime",getDatePoor(historicActivityInstance.getEndTime(),historicActivityInstance.getStartTime()));

            }
            if (!taskComments.isEmpty()){
                map.put("message",taskComments.get(0).getFullMessage());
            }else {
                map.put("message","无");
            }
            result.add(map);
        }
        System.out.println("result = " + result);
        return result;
    }


    /** 时间差计算 */
    public  String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟"+ sec + "秒";
    }

    /** 日志log类型替换 */
    private String matching(String ActivityType){
        String value="";
        switch (ActivityType){
            case "startEvent":
                value="流程开始";
                break;
            case "userTask":
                value="用户处理";
                break;
            case "noneEndEvent":
                value="流程结束";
                break;
            default:
                value="未知节点";
                break;
        }
        return value;
    }

}
