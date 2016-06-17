package com.puban.overtime.authority.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.puban.framework.core.dao.impl.BaseDaoImpl;
import com.puban.overtime.authority.dao.IRolePermissionDao;
import com.puban.overtime.authority.model.Permission;
import com.puban.overtime.authority.model.RolePermission;

/**
 * @author zengyong@puban.com
 * @ClassName: RolePermissionDaoImpl
 * @Description:
 * @date: 2016/5/30
 */
@Repository
public class RolePermissionDaoImpl extends BaseDaoImpl<RolePermission> implements IRolePermissionDao {
    @Override
    public List<Permission> findByPermission(Integer roleId) {
        Query query = getSession().createQuery("select rp.permission from RolePermission rp where rp.role.fdId=:p1");
        query.setInteger("p1", roleId);

        @SuppressWarnings("unchecked")
        List<Permission> permissions = query.list();
        return permissions;
    }

    /**
     * 根据角色ID删除中间表角色
     * @param roleId
     */
    @Override
    public void deleteByRoldId(Integer roleId) {
        Query query = getSession().createQuery("delete from RolePermission rp where rp.role.fdId=:p1");
        query.setInteger("p1", roleId);
        query.executeUpdate();
    }

    @Override
    public void deleteByPermission(Integer permissionId) {
        Query query = getSession().createQuery("delete from RolePermission rp where rp.permission.fdId=:p1");
        query.setInteger("p1", permissionId);
        query.executeUpdate();
    }
}
