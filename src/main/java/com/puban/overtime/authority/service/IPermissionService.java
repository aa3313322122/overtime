package com.puban.overtime.authority.service;

import com.puban.framework.core.page.PageView;
import com.puban.framework.core.service.IBaseService;
import com.puban.overtime.authority.model.Permission;
import com.puban.overtime.authority.shiro.tree.TreeObject;

import java.util.List;


/**
 * @author zengyong@puban.com
 * @ClassName: IPermissionService
 * @Description:权限
 * @date: 2016/5/24
 */
public interface IPermissionService extends IBaseService<Permission> {

    /**
     * 分页带条件查询数据
     */
    public PageView<Permission> queryBySerach(Integer parentId, Integer page,String fdPermissionKey);

    /**
     * 查询返回下拉选择框
     * @return
     */
    public List<TreeObject> findToSelectTree();

    /**
     * 根据KEY查询
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

    /**
     * 获取所有权限，返回tree
     *
     * @return
     */
    public List<TreeObject> queryPermissionTree();

    /**
     * 根据角色ID查询权限
     *
     * @param roleId
     * @return
     */
    public List<Permission> queryPermissionByRoleId(Integer roleId);

    /**
     * 保存编辑操作
     */
    public void saveUpdate(Permission permission);

    /**
     * 删除权限
     */
    public void delete(Integer permission);

}
