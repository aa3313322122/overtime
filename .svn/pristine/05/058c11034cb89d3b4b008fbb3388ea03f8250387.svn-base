package com.puban.overtime.authority.model;

import javax.persistence.*;

/**
 * @author zengyong@puban.com
 * @ClassName: RolePermission
 * @Description:
 * @date: 2016/5/30
 */
@Entity
@Table(name = "t_role_permission")
public class RolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fdId;

    @JoinColumn(name = "permission_id", updatable = false)
    @ManyToOne
    private Permission permission;

    @JoinColumn(name = "role_id", updatable = false)
    @ManyToOne
    private Role role;

    public Integer getFdId() {
        return fdId;
    }

    public void setFdId(Integer fdId) {
        this.fdId = fdId;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
