package com.kunning.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class User {

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * ID
     */
    @Setter
    @Getter
    private int id;

    /**
     * 用户名
     */
    @Setter
    @Getter
    private String username;

    /**
     * 密码
     */
    @Setter
    @Getter
    private String password;

    /**
     * 昵称
     */
    @Setter
    @Getter
    private String nickname;

    /**
     * 邮箱
     */
    @Setter
    @Getter
    private String email;

}
