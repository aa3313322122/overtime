package com.puban.overtime.authority.service;

import com.puban.framework.core.page.PageView;
import com.puban.framework.core.service.IBaseService;
import com.puban.overtime.authority.model.Role;
import com.puban.overtime.authority.model.User;

import java.util.List;

/**
 * @author zengyong@puban.com
 * @ClassName: IUserService
 * @Description:
 * @date: 2016/5/18
 */
public interface IUserService extends IBaseService<User> {

    /**
     * 根据账号查询信息
     *
     * @param name
     * @return
     */
    public User getUserInfoByName(String name);

    /**
     * 更新保存
     *
     * @param user
     * @param roleId
     */
    public void update(User user, Integer roleId[]);

    /**
     * 保存用户
     *
     * @param user
     * @param roleId
     */
    public void save(User user, Integer roleId[]);

    /**
     * 删除用户信息
     */
    public void delete(Integer userId);


    /**
     * 分页带条件查询数据
     */
    public PageView<User> queryBySerach(String username,Integer page);

    /**
     * 更新用户roles
     */
    public void saveRoles(User userId, List<Role> roles);

}
