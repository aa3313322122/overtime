package com.puban.overtime.task.dao;

import java.util.List;

import com.puban.framework.core.dao.IBaseDao;
import com.puban.overtime.task.model.Task;

public interface ITaskDao extends IBaseDao<Task>
{

	/**
	* @author wangchunping
	* @date 2016年5月24日 上午11:23:04
	* <p>Description:查询我的所有任务 </p>
	* <p>Paramter:userId当前用户ID </p>
	* <p>Return: 返回任务列表List<Task></p>
	*/
	public List<Task> queryMyTaskList(Integer userId);
	
	/**
	* @author wangchunping
	* @date 2016年5月24日 下午3:15:48
	* <p>Description:根据任务编号查找任务 </p>
	* <p>Paramter:code任务唯一code </p>
	* <p>Return: 返回任务Task</p>
	*/
	public Task queryMyTaskByCode(String code);

}
