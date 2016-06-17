package com.puban.overtime.authority.model;

import com.puban.framework.core.model.BaseModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author zengyong@puban.com
 * @ClassName: Role
 * @Description:角色
 * @date: 2016/5/18
 */
@Entity
@Table(name = "t_role_info")
public class Role extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fdId;

    //角色名称
    @Column(name = "fd_role_name")
    private String fdRoleName;

    //角色描述
    @Column(name = "fd_role_key")
    private String fdRoleKey;

    //角色使用状态
    @Column(name = "fd_role_status")
    private Integer fdRoleStatus;
    /**
     * 关联权限
     * @return
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "role")
    private Set<RolePermission> rolePermissions;// 权限集合

    @Transient
    private List<Permission> permissions;  //权限集合

    @Transient
    private List<String> permissionsName;
    public List<String> getPermissionsName() {
        List<String> list = new ArrayList<String>();
        List<Permission> perlist = getPermissions();
        if(null != perlist)
        {
        	for (Permission per : perlist) {
                list.add(per.getFdPermissionKey());
            }
        }
        return list;
    }

    public Role() {
    }

    public Role(String fdRoleKey, Integer fdRoleStatus) {
        this.fdRoleKey = fdRoleKey;
        this.fdRoleStatus = fdRoleStatus;
    }

    public Integer getFdId() {
        return fdId;
    }

    public void setFdId(Integer fdId) {
        this.fdId = fdId;
    }

    public String getFdRoleName() {
        return fdRoleName;
    }

    public void setFdRoleName(String fdRoleName) {
        this.fdRoleName = fdRoleName;
    }

    public String getFdRoleKey() {
        return fdRoleKey;
    }

    public void setFdRoleKey(String fdRoleKey) {
        this.fdRoleKey = fdRoleKey;
    }

    public Integer getFdRoleStatus() {
        return fdRoleStatus;
    }

    public void setFdRoleStatus(Integer fdRoleStatus) {
        this.fdRoleStatus = fdRoleStatus;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(Set<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    @Override
    public String toString() {
        return "Role [fdId=" + fdId + ", fdRoleName=" + fdRoleName + ", fdRoleStatus=" + fdRoleStatus + ", fdRoleKey=" + fdRoleKey + "]";
    }

}
