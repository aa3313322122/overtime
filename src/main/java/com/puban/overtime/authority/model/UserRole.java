package com.puban.overtime.authority.model;

import javax.persistence.*;

/**
 * @author zengyong@puban.com
 * @ClassName: UserRole
 * @Description:
 * @date: 2016/5/30
 */
@Entity
@Table(name = "t_user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fdId;

    @JoinColumn(name = "user_id", updatable = false)
    @ManyToOne
    private User user;
    @JoinColumn(name = "role_id", updatable = false)
    @ManyToOne
    private Role role;

    public Integer getFdId() {
        return fdId;
    }

    public void setFdId(Integer fdId) {
        this.fdId = fdId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
