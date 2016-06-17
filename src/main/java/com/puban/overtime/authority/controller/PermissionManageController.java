package com.puban.overtime.authority.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.puban.framework.core.controller.BaseController;
import com.puban.framework.core.page.PageView;
import com.puban.overtime.authority.model.Permission;
import com.puban.overtime.authority.model.Role;
import com.puban.overtime.authority.service.IPermissionService;
import com.puban.overtime.authority.service.IRolePermissionService;
import com.puban.overtime.authority.service.IRoleService;
import com.puban.overtime.authority.shiro.ResultVO;
import com.puban.overtime.authority.shiro.UserRealm;
import com.puban.overtime.authority.shiro.tree.TreeObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zengyong@puban.com
 * @ClassName: PermissionController
 * @Description: 权限
 * @date: 2016/5/25
 */
@Controller
@RequestMapping(value = "/permission")
public class PermissionManageController extends BaseController {
    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private UserRealm userRealm;

    @Autowired
    private IRolePermissionService rolePermissionService;

    /**
     * 权限信息列表展示
     * @param model
     * @return
     */
    @RequestMapping(value = "/plist")
    public String plist( Model model) {
        /**分页数据 **/
        PageView<Permission> pageView = permissionService.queryBySerach(null,0,null);
        model.addAttribute("pageView", pageView);
        List<TreeObject> parentTree = permissionService.findToSelectTree();
        model.addAttribute("parentTree", parentTree);
        return "view/authority/permissionManage";
    }

    /**
     * 权限信息列表展示
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String permissionList(Integer currentPage, Integer fdPParentId,String fdPermissionKey,Model model) {
        fdPParentId = fdPParentId == null ? 0 : fdPParentId;
        String pname = "一级菜单";
        if (fdPParentId != 0) {
            //根据ID查询
            Permission pid = permissionService.findByPrimaryKey(fdPParentId);
            pname = pid.getFdPermissionName();
        }
        model.addAttribute("pid", fdPParentId);
        model.addAttribute("pname", pname);
        /**分页数据 **/
        PageView<Permission> pageView = permissionService.queryBySerach(fdPParentId, currentPage, fdPermissionKey);
        model.addAttribute("pageView", pageView);
        List<TreeObject> parentTree = permissionService.findToSelectTree();
        model.addAttribute("parentTree", parentTree);
        return "view/authority/permissionManage";
    }

    /**
     * 新增权限跳转页面
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Long pid, Model model) {
        List<TreeObject> parentTree = permissionService.findToSelectTree();
        model.addAttribute("parentTree", parentTree);
        model.addAttribute("action", "saveadd");
        model.addAttribute("pid", pid);
        return "view/authority/pedit";
    }

    /**
     * 保存权限操作
     */
    @RequestMapping(value = "/saveadd", method = RequestMethod.POST)
    public void saveadd(Permission permission, HttpServletResponse response) {
        ResultVO result = new ResultVO();
        Permission checkResourcesKey = permissionService.getResourcesByKey(permission.getFdPermissionKey());
        if (null != checkResourcesKey.getFdId()) {
            result.setMsg("新增权限失败,菜单Key重复");
            result.setOk(false);
        }
        Permission checkResourcesName = permissionService.getResourcesByName(permission.getFdPermissionName());
        if (null != checkResourcesName.getFdId()) {
            result.setMsg("新增权限失败,菜单名称重复");
            result.setOk(false);
        }
        if(result.isOk()){
            try {
                permissionService.save(permission);
                result.setMsg("新增权限成功");
            } catch (Exception e) {
                result.setMsg("新增权限失败" + e.getMessage());
                result.setOk(false);
            }
        }
        ajaxJson(JSON.toJSONString(result), response);
    }

    /**
     * 删除权限
     */

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(Integer id, HttpServletResponse response) {
        ResultVO result = new ResultVO();
        try {
            permissionService.delete(id);
            result.setMsg("删除成功");
        } catch (Exception e) {
            result.setMsg("删除失败");
            result.setOk(false);
        }
        ajaxJson(JSON.toJSONString(result), response);
    }

    /**
     * 编辑菜单页面
     *
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Integer id, Model model) {
        //根据ID查询信息
        Permission res = permissionService.findByPrimaryKey(id);
        model.addAttribute("res", res);
        model.addAttribute("action", "saveedit");
        model.addAttribute("pid", res.getFdPParentId());
        List<TreeObject> parentTree = permissionService.findToSelectTree();
        model.addAttribute("parentTree", parentTree);
        return"view/authority/pedit";
    }

    /**
     * 保存编辑操作
     */
    @RequestMapping(value = "/saveedit", method = RequestMethod.POST)
    public void saveedit(Permission permission,HttpServletResponse response) {
        ResultVO result = new ResultVO();
       /* Permission checkResourcesKey = permissionService.getResourcesByKey(permission.getFdPermissionKey());
        if (null != checkResourcesKey.getFdPermissionKey()) {
            result.setMsg("新增权限失败,权限Key重复");
            result.setOk(false);
        }
        Permission checkResourcesName = permissionService.getResourcesByName(permission.getFdPermissionName());
        if (null != checkResourcesName.getFdPermissionName()) {
            result.setMsg("新增权限失败,权限名称重复");
            result.setOk(false);
        }*/
        if (result.isOk()) {
            try {
                permissionService.update(permission);
                result.setMsg("修改权限成功");
            } catch (Exception e) {
                result.setMsg("修改权限失败" + e.getMessage());
                result.setOk(false);
            }
        }
        ajaxJson(JSON.toJSONString(result), response);
    }

    /**
     * 进入权限分配页面
     *
     * @param model
     * @return
     * @author tanghom
     */
    @RequestMapping("/authorization")
    public String permissions(Integer roleId, Model model) {
        List<TreeObject> list = permissionService.queryPermissionTree();
        model.addAttribute("permissions", list);
        Role role = roleService.findByPrimaryKey(roleId);
        model.addAttribute("role", role);
        return "view/authority/perAuthorization";
    }

    /**
     * 在权限分配页面获取角色权限
     * @param roleId
     */
    @RequestMapping(value = "/get_role_per", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getRoleResources(Integer roleId) {
        List<Permission> permissions = rolePermissionService.findByPermission(roleId);
        return JSONObject.toJSONString(permissions);
    }

    /**
     *角色分配权限
     * @param roleId
     * @param resId
     * @return
     * @throws Exception
     */
    @RequestMapping("/add_role_per")
    public void addRoleRes(Integer roleId, Integer resId[], HttpServletResponse response) throws Exception {
        ResultVO result = new ResultVO();
        try {
            roleService.addRoleRes(roleId, resId);
            Subject user = SecurityUtils.getSubject();
            userRealm.clearCache(user.getPrincipals());
            result.setMsg("角色分配成功");
        } catch (Exception e) {
            result.setMsg("角色分配失败");
            result.setOk(false);
        }
        ajaxJson(JSON.toJSONString(result), response);
    }
}
