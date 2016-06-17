package com.puban.overtime.authority.service.impl;

import com.puban.framework.core.service.impl.BaseServiceImpl;
import com.puban.overtime.authority.dao.IRolePermissionDao;
import com.puban.overtime.authority.model.Permission;
import com.puban.overtime.authority.model.RolePermission;
import com.puban.overtime.authority.service.IRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zengyong@puban.com
 * @ClassName: RolePermissionServiceImpl
 * @Description:
 * @date: 2016/5/30
 */
@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission> implements IRolePermissionService {

    @Autowired
    private IRolePermissionDao rolePermissionDao;

    @Override
    public List<Permission> findByPermission(Integer roleId) {
        return rolePermissionDao.findByPermission(roleId);
    }

    @Override
    public void deleteByRoldId(Integer roleId) {
        rolePermissionDao.deleteByRoldId(roleId);
    }
}
