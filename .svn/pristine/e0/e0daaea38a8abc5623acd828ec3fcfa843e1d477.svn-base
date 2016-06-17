package com.puban.overtime.authority.service.impl;

import com.puban.framework.core.page.PageView;
import com.puban.framework.core.page.QueryResult;
import com.puban.framework.core.service.impl.BaseServiceImpl;
import com.puban.overtime.authority.dao.IPermissionDao;
import com.puban.overtime.authority.dao.IRoleDao;
import com.puban.overtime.authority.dao.IRolePermissionDao;
import com.puban.overtime.authority.dao.IUserRoleDao;
import com.puban.overtime.authority.model.Permission;
import com.puban.overtime.authority.model.Role;
import com.puban.overtime.authority.model.RolePermission;
import com.puban.overtime.authority.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author zengyong@puban.com
 * @ClassName: RoleServiceImpl
 * @Description:
 * @date: 2016/5/24
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IPermissionDao permissionDao;

    @Autowired
    private IRolePermissionDao rolePermissionDao;

    @Autowired
    private IUserRoleDao userRoleDao;
    /**
     * 分页带条件查询数据
     */
    @Override
    public PageView<Role> queryBySerach(String roleName, Integer currentPage) {
        PageView<Role> pageView = new PageView<Role>(5, currentPage);
        /** 查询条件 **/
        List<Object> paramterList = new ArrayList<Object>();
        if (currentPage == null) {
            currentPage = 1;
        }
        int firstindex = (pageView.getCurrentpage() - 1) * pageView.getMaxresult();

        LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
        orderby.put("fdId", "desc");

        StringBuffer whereHql = new StringBuffer();
        whereHql.append(" 1=1 ");
        List<Object> queryParams = new ArrayList<Object>();

        if (roleName != null && (!roleName.equals(""))) {
            int index = queryParams.size() + 1;
            whereHql.append(" and role.fdRoleName like :p" + index).append(" ");
            queryParams.add("%" + roleName + "%");
        }
        QueryResult<Role> queryResult = roleDao.query(Role.class, firstindex, pageView.getMaxresult(), whereHql.toString(), queryParams.toArray(), orderby);
        pageView.setQueryResult(queryResult);
        return pageView;
    }

    /**
     * 角色权限分配
     * @param roleId
     * @param resId
     */
    @Override
    public void addRoleRes(Integer roleId, Integer[] resId) {
        Role role = roleDao.find(Role.class,roleId);
        //先删除角色ID中间表的角色权限信息，再新增
        rolePermissionDao.deleteByRoldId(roleId);
        if (null != resId) {
            for (int i = 0; i < resId.length; i++) {
                Permission permission = permissionDao.find(Permission.class, resId[i]);
                RolePermission rolePermission = new RolePermission();
                rolePermission.setPermission(permission);
                rolePermission.setRole(role);
                rolePermissionDao.save(rolePermission);
            }
        }
    }

    @Override
    public Role getRoleByKey(String key) {
        return roleDao.getRoleByKey(key);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    @Override
    public void delete(Integer roleId) {
        Role role = this.findByPrimaryKey(roleId);
        //根据角色id删除角色权限关系
        rolePermissionDao.deleteByRoldId(roleId);
        //根据角色id删除用户角色关系
        userRoleDao.deleteByRoleId(roleId);
        //移除角色信息
        roleDao.remove(Role.class,role.getFdId());
    }

}
