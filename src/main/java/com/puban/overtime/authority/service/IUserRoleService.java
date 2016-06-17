package com.puban.overtime.authority.service;

import com.puban.overtime.authority.model.Role;

import java.util.List;

/**
 * @author zengyong@puban.com
 * @ClassName: IUserRoleService
 * @Description:
 * @date: 2016/5/30
 */
public interface IUserRoleService {
    /**
     * 根据userId查询所有角色
     */
    List<Role> findRoleByUserId(Integer userId);
}
