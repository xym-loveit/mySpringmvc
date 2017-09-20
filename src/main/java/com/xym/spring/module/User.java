package com.xym.spring.module;


import com.xym.spring.validator.Between;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * desc
 *
 * @author xym
 */
public class User {

    @NotEmpty(message = "{NotEmpty.userName}")
    private String userName;

    @Length(min = 2, max = 100, message = "{Length.realName}")
    private String realName;

    @Pattern(regexp = "[0-9a-zA-Z]{6,30}", message = "密码为6到30个字符，必须是数字或字母的组合")
    private String password;

    @NotNull(message = "请填写你的年龄")
    @Between(max = 48, min = 20)
    private int age;

    @Email(message = "请输入正确的邮件地址")
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}