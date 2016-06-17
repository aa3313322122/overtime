package com.puban.overtime.authority.service;

import com.puban.framework.core.page.PageView;
import com.puban.framework.core.service.IBaseService;
import com.puban.overtime.authority.model.Role;


/**
 * @author zengyong@puban.com
 * @ClassName: IRoleService
 * @Description: 角色
 * @date: 2016/5/24
 */
public interface IRoleService extends IBaseService<Role> {

    /**
     * 分页带条件查询数据
     */
    public PageView<Role> queryBySerach(String roleName, Integer page);

    /**
     * 角色权限分配
     */
    public void addRoleRes(Integer roleId, Integer resId[]);

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

    /**
     * 删除角色
     * @param roleId
     */
    public void delete(Integer roleId);



}
