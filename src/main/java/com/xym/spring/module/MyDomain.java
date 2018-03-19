package com.xym.spring.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author xym
 */
@XStreamAlias("domain")
public class MyDomain {
    private String id;
    @XStreamAlias("userName")
    private String username;
    @XStreamAlias("passWord")
    private String passpwd;
    @XStreamAlias("realName")
    private String realName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasspwd() {
        return passpwd;
    }

    public void setPasspwd(String passpwd) {
        this.passpwd = passpwd;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
