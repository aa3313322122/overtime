package com.puban.overtime.task.service;

import java.util.List;

import org.springframework.ui.Model;

import com.puban.framework.core.page.PageView;
import com.puban.framework.core.page.QueryResult;
import com.puban.framework.core.service.IBaseService;
import com.puban.overtime.task.model.Task;

/*** 
* @author wangchunping 
* @date 2016年5月24日 上午11:18:04 
* <p>Title:ITaskService </p>
* <p>Description:任务接口 </p>
*/
public interface ITaskService extends IBaseService<Task>
{
	/**
	* @author wangchunping
	* @date 2016年5月24日 上午11:21:45
	* <p>Description: 分页查询出所有任务</p>
	* <p>Paramter:currentPage 当前页, pageSzie每页显示记录数 </p>
	* <p>Return: 返回任务分页列表对象QueryResult<Task></p>
	*/
	public QueryResult<Task> queryTaskList(Integer currentPage,Integer pageSzie);
	
	/**
	 * 添加新任务
	 */
	public boolean saveNewTask(Task task);
	
	/** 返回根据任务ID查找到的加班列表和正常任务列表**/
	public Model queryOverNormalList(List<Task> tasks, Model model);
	
	/**
	 * 修改分配任务
	 */
	
	public boolean updateTaskByGive(Task task);
	
	/**
	 * 查询所有分配任务
	 */
	public PageView<Task> queryGiveList(Integer currentpage, Integer maxresult);
	
	
	
	/**
	* @author wangchunping
	* @date 2016年5月24日 上午11:21:45
	* <p>Description: 无分页查询出所有任务</p>
	* <p>Paramter:无 </p>
	* <p>Return: 返回任务列表List<Task></p>
	*/
	public List<Task> queryTaskList();

	/**
	* @author wangchunping
	* @date 2016年5月24日 上午11:23:04
	* <p>Description:分页查询我的所有任务 </p>
	* <p>Paramter:currentPage 当前页, pageSzie每页显示记录数 ,userId当前用户ID </p>
	* <p>Return: 返回任务分页列表对象QueryResult<Task></p>
	*/
	public QueryResult<Task> queryMyTaskList(Integer currentPage,Integer pageSzie,Integer userId);
	
	/**
	* @author wangchunping
	* @date 2016年5月24日 上午11:23:04
	* <p>Description:无分页查询我的所有任务 </p>
	* <p>Paramter:userId当前用户ID </p>
	* <p>Return: 返回任务列表List<Task></p>
	*/
	public List<Task> queryMyTaskList(Integer userId);
	
	
	/**
	* @author wangchunping
	* @date 2016年5月24日 下午3:15:48
	* <p>Description:根据任务编号查找任务 </p>
	* <p>Paramter:code任务code </p>
	* <p>Return: 返回任务Task</p>
	*/
	public Task queryMyTaskByCode(String code);
}
