package com.kunning.springboot.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

    /**
     * ID
     */
    private int id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度在6-20之间")
    private String password;

    /**
     * 性别：0保密、1男性、2女性
     */
    @Min(value = 0, message = "性别参数非法")
    @Max(value = 2, message = "性别参数非法")
    private int gender;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    /**
     * 手机号码。没有 @NotBlank 注解，表示可以为空，只有非空时才会用正则校验输入是否正确
     */
    @Pattern(regexp = "^[1][0-9]{10}$", message = "手机号码格式错误")
    private String telephone;

    /**
     * 邮箱。没有 @NotBlank 注解，表示可以为空，只有非空时才会用正则校验输入是否正确
     */
    @Email(message = "邮箱格式不合理")
    private String email;

}
