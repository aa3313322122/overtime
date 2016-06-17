package com.puban.overtime.authority.dao;

import com.puban.framework.core.dao.IBaseDao;
import com.puban.overtime.authority.model.Permission;
import com.puban.overtime.authority.model.RolePermission;

import java.util.List;

/**
 * @author zengyong@puban.com
 * @ClassName: IRolePermissionDao
 * @Description:
 * @date: 2016/5/30
 */
public interface IRolePermissionDao extends IBaseDao<RolePermission> {
    /**
     * 根据角色id查询权限
     * @param roleId
     * @return
     */
    List<Permission> findByPermission(Integer roleId);

    /**
     * 根据角色ID删除中间表角色权限信息
     */
    void deleteByRoldId(Integer roleId);

    /**
     * 根据权限ID删除中间表相应的角色权限信息
     */
    void deleteByPermission(Integer permissionId);
}
