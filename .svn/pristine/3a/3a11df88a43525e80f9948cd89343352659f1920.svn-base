package com.puban.overtime.authority.dao.impl;

import com.puban.framework.core.dao.impl.BaseDaoImpl;
import com.puban.framework.core.page.QueryResult;
import com.puban.overtime.authority.dao.IRoleDao;
import com.puban.overtime.authority.model.Role;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zengyong@puban.com
 * @ClassName: RoleDaoImpl
 * @Description:
 * @date: 2016/5/18
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements IRoleDao {


    @Override
    public Role getInfoById(List<Role> roles, int id) {
        for (Role role : roles) {
            if (role.getFdId().intValue() == id) {
                return role;
            }
        }
        return null;
    }

    /**
     * 根据角色KEY查询
     * @param key
     * @return
     */
    @Override
    public Role getRoleByKey(String key) {
        List<Object> paramterList = new ArrayList<Object>();
        /** where **/
        StringBuffer wherejpql = new StringBuffer(" fdRoleKey=:p1 ");
        paramterList.add(key);
        QueryResult<Role> queryResult = this.query(Role.class, -1, -1, wherejpql.toString(), paramterList.toArray());
        Role role = new Role();
        for (int i = 0; queryResult.getResultlist() != null && i < queryResult.getResultlist().size(); i++) {
            role = queryResult.getResultlist().get(i);
        }
        return role;
    }

    /**
     * 根据name查询角色
     * @param name
     * @return
     */
    @Override
    public Role getRoleByName(String name) {
        List<Object> paramterList = new ArrayList<Object>();
        /** where **/
        StringBuffer wherejpql = new StringBuffer(" fdRoleName=:p1 ");
        paramterList.add(name);
        QueryResult<Role> queryResult = this.query(Role.class, -1, -1, wherejpql.toString(), paramterList.toArray());
        Role role = new Role();
        for (int i = 0; queryResult.getResultlist() != null && i < queryResult.getResultlist().size(); i++) {
            role = queryResult.getResultlist().get(i);
        }
        return role;
    }
}
