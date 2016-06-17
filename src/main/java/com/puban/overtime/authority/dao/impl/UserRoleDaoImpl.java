package com.puban.overtime.authority.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.puban.framework.core.dao.impl.BaseDaoImpl;
import com.puban.overtime.authority.dao.IUserRoleDao;
import com.puban.overtime.authority.model.Role;
import com.puban.overtime.authority.model.UserRole;

/**
 * @author zengyong@puban.com
 * @ClassName: UserRoleDaoImpl
 * @Description:
 * @date: 2016/5/30
 */
@Repository
public class UserRoleDaoImpl extends BaseDaoImpl<UserRole> implements IUserRoleDao {
    @Override
    public List<Role> findRoleByUserId(Integer userId) {
        Query query = getSession().createQuery("select ur.role from UserRole ur where ur.user.fdId=:p1");
        query.setInteger("p1", userId);

        @SuppressWarnings("unchecked")
        List<Role> roles = query.list();
        return roles;
    }

    @Override
    public void deleteByUserId(Integer userId) {
        Query query = getSession().createQuery("delete from UserRole ur where ur.user.fdId=:p1");
        query.setInteger("p1", userId);
        query.executeUpdate();
    }

    @Override
    public void deleteByRoleId(Integer roleId) {
        Query query = getSession().createQuery("delete from UserRole ur where ur.role.fdId=:p1");
        query.setInteger("p1", roleId);
        query.executeUpdate();
    }
}