package com.puban.overtime.authority.service.impl;

import com.puban.framework.core.service.impl.BaseServiceImpl;
import com.puban.overtime.authority.dao.IUserRoleDao;
import com.puban.overtime.authority.model.Role;
import com.puban.overtime.authority.model.UserRole;
import com.puban.overtime.authority.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zengyong@puban.com
 * @ClassName: UserRoleServiceImpl
 * @Description:
 * @date: 2016/5/30
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements IUserRoleService {

    @Autowired
    private IUserRoleDao userRoleDao;
    @Override
    public List<Role> findRoleByUserId(Integer userId) {
        return userRoleDao.findRoleByUserId(userId);
    }
}
