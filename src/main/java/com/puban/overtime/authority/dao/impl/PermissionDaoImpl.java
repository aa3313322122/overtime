package com.puban.overtime.authority.dao.impl;

import com.puban.framework.core.dao.impl.BaseDaoImpl;
import com.puban.framework.core.page.QueryResult;
import com.puban.overtime.authority.dao.IPermissionDao;
import com.puban.overtime.authority.model.Permission;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zengyong@puban.com
 * @ClassName: PermissionDaoImpl
 * @Description:
 * @date: 2016/5/18
 */
@Repository
public class PermissionDaoImpl extends BaseDaoImpl<Permission> implements IPermissionDao {

    @Override
    public Permission getPermissionById(List<Permission> permissionList, int id) {
        for (Permission permission : permissionList) {
            if (permission.getFdId().intValue() == id) {
                return permission;
            }
        }
        return null;
    }

    @Override
    public Permission getResourcesByKey(String key) {
        List<Object> paramterList = new ArrayList<Object>();
        /** where **/
        StringBuffer wherejpql = new StringBuffer(" fdPermissionKey=:p1 ");
        paramterList.add(key);
        QueryResult<Permission> queryResult = this.query(Permission.class, -1, -1, wherejpql.toString(), paramterList.toArray());
        Permission permission = new Permission();
        for (int i = 0; queryResult.getResultlist() != null && i < queryResult.getResultlist().size(); i++) {
            permission = queryResult.getResultlist().get(i);
        }
        return permission;
    }

    @Override
    public Permission getResourcesByName(String name) {
        List<Object> paramterList = new ArrayList<Object>();
        /** where **/
        StringBuffer wherejpql = new StringBuffer(" fdPermissionName=:p1 ");
        paramterList.add(name);
        QueryResult<Permission> queryResult = this.query(Permission.class, -1, -1, wherejpql.toString(), paramterList.toArray());
        Permission permission = new Permission();
        for (int i = 0; queryResult.getResultlist() != null && i < queryResult.getResultlist().size(); i++) {
            permission = queryResult.getResultlist().get(i);
        }
        return permission;
    }
}
