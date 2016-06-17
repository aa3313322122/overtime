package com.puban.overtime.authority.model;

import javax.persistence.*;

/**
 * @author zengyong@puban.com
 * @ClassName: Permission
 * @Description:权限
 * @date: 2016/5/18
 */
@Entity
@Table(name = "t_permission_info")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fdId;

    //权限名称
    @Column(name = "fd_permission_name")
    private String fdPermissionName;

    //权限名称对应的key
    @Column(name = "fd_permission_key")
    private String fdPermissionKey;

    //权限上级ID
    @Column(name = "fd_p_parent_id")
    private Integer fdPParentId;

    //排序
    @Column(name = "fd_orders")
    private Integer fdOrders;

    //地址
    @Column(name = "fd_url")
    private String fdUrl;

    /**
     * 关联角色
     *
     * @return
     */
/*    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "t_role_permission", joinColumns = {@JoinColumn(name = "permission_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles = new ArrayList<Role>();// 角色集合*/

    public Permission() {
    }

    public Permission(String fdPermissionName, String fdPermissionKey, Integer fdPParentId, Integer fdOrders) {
        this.fdPermissionName = fdPermissionName;
        this.fdPermissionKey = fdPermissionKey;
        this.fdPParentId = fdPParentId;
        this.fdOrders = fdOrders;
    }

    public Integer getFdId() {
        return fdId;
    }

    public void setFdId(Integer fdId) {
        this.fdId = fdId;
    }

    public String getFdPermissionName() {
        return fdPermissionName;
    }

    public void setFdPermissionName(String fdPermissionName) {
        this.fdPermissionName = fdPermissionName;
    }

    public String getFdPermissionKey() {
        return fdPermissionKey;
    }

    public void setFdPermissionKey(String fdPermissionKey) {
        this.fdPermissionKey = fdPermissionKey;
    }

    public Integer getFdPParentId() {
        return fdPParentId;
    }

    public void setFdPParentId(Integer fdPParentId) {
        this.fdPParentId = fdPParentId;
    }

    public Integer getFdOrders() {
        return fdOrders;
    }

    public void setFdOrders(Integer fdOrders) {
        this.fdOrders = fdOrders;
    }

    public String getFdUrl() {
        return fdUrl;
    }

    public void setFdUrl(String fdUrl) {
        this.fdUrl = fdUrl;
    }

    @Override
    public String toString() {
        return "Role [fdId=" + fdId + ", fdPermissionName=" + fdPermissionName + ", fdPermissionKey=" + fdPermissionKey + ", fdPParentId=" + fdPParentId + fdOrders + ", fdOrders" + "]";
    }
}
