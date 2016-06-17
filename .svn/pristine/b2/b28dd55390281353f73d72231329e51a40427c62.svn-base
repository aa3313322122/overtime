package com.puban.overtime.authority.model;


import com.puban.framework.core.model.BaseModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zengyong@puban.com
 * @ClassName: User
 * @Description:用户表
 * @date: 2016/5/18
 */
@Entity
@Table(name = "t_user_info")
public class User extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fdId;

    @Column(name = "fd_user_name")
    private String fdUserName;

    @Column(name = "fd_password")
    private String fdPassword;

    @Column(name = "fd_email")
    private String fdEmail;

    @Column(name = "fd_mobile_phone")
    private String fdMobilePhone;
    /**
     * 关联角色
     * @return
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserRole> userRoles;// 角色集合

    @Transient
    private List<Role> roles;

    @Transient
    private Set<String> rolesName;
    public Set<String> getRolesName() {
        List<Role> roles = getRoles();
        Set<String> set = new HashSet<String>();
        if(null != roles)
        {
        	for (Role role : roles) {
                set.add(role.getFdRoleName());
            }
        }
        return set;
    }

    public void setRolesName(Set<String> rolesName) {
        this.rolesName = rolesName;
    }

    public User() {
    }

    public User(String fdPassword, String fdUserName, String fdEmail, String fdMobilePhone) {
        this.fdPassword = fdPassword;
        this.fdUserName = fdUserName;
        this.fdEmail = fdEmail;
        this.fdMobilePhone = fdMobilePhone;
    }

    public Integer getFdId() {
        return fdId;
    }

    public void setFdId(Integer fdId) {
        this.fdId = fdId;
    }

    public String getFdUserName() {
        return fdUserName;
    }

    public void setFdUserName(String fdUserName) {
        this.fdUserName = fdUserName;
    }

    public String getFdPassword() {
        return fdPassword;
    }

    public void setFdPassword(String fdPassword) {
        this.fdPassword = fdPassword;
    }

    public String getFdEmail() {
        return fdEmail;
    }

    public void setFdEmail(String fdEmail) {
        this.fdEmail = fdEmail;
    }

    public String getFdMobilePhone() {
        return fdMobilePhone;
    }

    public void setFdMobilePhone(String fdMobilePhone) {
        this.fdMobilePhone = fdMobilePhone;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User [fdId=" + fdId + ", fdUserName=" + fdUserName + ", fdPassword=" + fdPassword + ", fdEmail=" + fdEmail + ", fdMobilePhone=" + fdMobilePhone + "]";
    }
}
