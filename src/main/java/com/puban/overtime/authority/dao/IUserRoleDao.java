package com.puban.overtime.authority.dao;

import com.puban.framework.core.dao.IBaseDao;
import com.puban.overtime.authority.model.Role;
import com.puban.overtime.authority.model.UserRole;

import java.util.List;

/**
 * @author zengyong@puban.com
 * @ClassName: IUserRoleDao
 * @Description:
 * @date: 2016/5/30
 */
public interface IUserRoleDao extends IBaseDao<UserRole> {
    /**
     * 根据userId查询所有角色
     */
    List<Role> findRoleByUserId(Integer userId);

    /**
     * 根据用户ID删除中间表角色
     */
    void deleteByUserId(Integer userId);

    /**
     * 根据角色id删除用户角色关系
     * @param roleId
     */
    void deleteByRoleId(Integer roleId);
}
