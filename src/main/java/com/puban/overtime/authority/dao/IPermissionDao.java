package com.puban.overtime.authority.dao;


import com.puban.framework.core.dao.IBaseDao;
import com.puban.overtime.authority.model.Permission;

import java.util.List;

/**
 * @author zengyong@puban.com
 * @ClassName: IPermissionDao
 * @Description:
 * @date: 2016/5/18
 */

public interface IPermissionDao extends IBaseDao<Permission> {
    /**
     * 根据权限ID获取权限
     *
     * @param permissionList
     * @param id
     * @return
     */
    public Permission getPermissionById(List<Permission> permissionList, int id);

    /**
     * 根据Key查询
     *
     * @param key
     * @return
     */
    public Permission getResourcesByKey(String key);

    /**
     * 根据name查询
     *
     * @param name
     * @return
     */

    public Permission getResourcesByName(String name);
}
