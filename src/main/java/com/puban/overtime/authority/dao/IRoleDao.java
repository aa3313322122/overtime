package com.puban.overtime.authority.dao;

import com.puban.framework.core.dao.IBaseDao;
import com.puban.overtime.authority.model.Role;

import java.util.List;

/**
 * @author zengyong@puban.com
 * @ClassName: IRoleDao
 * @Description:
 * @date: 2016/5/18
 */
public interface IRoleDao extends IBaseDao<Role> {
    /**
     * 根据角色ID获取角色
     *
     * @param roles
     * @param id
     * @return
     */
    public Role getInfoById(List<Role> roles, int id);

    /**
     * 根据角色KEY查询
     *
     * @param key
     * @return
     */
    public Role getRoleByKey(String key);

    /**
     * 根据name查询角色
     *
     * @param name
     * @return
     */

    public Role getRoleByName(String name);

}
