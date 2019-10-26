package com.kunning.commons.Test;

import java.util.ArrayList;
import java.util.List;

//问题：添加一个对象到集合中时，集合里面存放的是对象的引用还是对象本身？
//答：对象的引用。以下代码可以证明：
public class Test5 {    
    public static void main(String args[]) {    
        List<User> userList1 = new ArrayList<User>();           
        List<User> userList2 = new ArrayList<User>();       
        User user1 = new User();//将同一个user对象放进两个List列表里。
        userList1.add(user1);
        userList2.add(user1);
        
        System.out.println("SET VALUE FOR USERLIST2:");         
        for(User user: userList2){    
            user.setName("name");    
            user.setPassword("password");           
        }    
        System.out.println("PRINT VALUE FOR USERLIST1:");    
        for(User user: userList1){    
            System.out.println(user.getName());             
            System.out.println(user.getPassword());             
        }           
    }    
        
    public static class User { //静态内部类
        private String name;    
        private String password;    
            
        public String getName() {    
            return name;    
        }    
        public void setName(String name) {    
            this.name = name;    
        }    
        public String getPassword() {    
            return password;    
        }    
        public void setPassword(String password) {    
            this.password = password;    
        }    
    }    
} 

