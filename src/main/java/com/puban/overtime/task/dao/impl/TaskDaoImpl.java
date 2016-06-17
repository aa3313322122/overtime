package com.puban.overtime.task.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.puban.framework.core.dao.impl.BaseDaoImpl;
import com.puban.overtime.task.dao.ITaskDao;
import com.puban.overtime.task.model.Task;

@Repository
public class TaskDaoImpl extends BaseDaoImpl<Task> implements ITaskDao
{

	/**
	* @author wangchunping
	* @date 2016年5月24日 上午11:23:04
	* <p>Description:查询我的所有任务 </p>
	* <p>Paramter:userId当前用户ID </p>
	* <p>Return: 返回任务列表List<Task></p>
	*/
	@Override
	public List<Task> queryMyTaskList(Integer userId)
	{
		Query query=getSession().createQuery(" from Task where user.fdId=:p1");
		query.setInteger("p1", userId);
		
		@SuppressWarnings("unchecked")
		List<Task> myTaskList=query.list();
		
		return myTaskList;
	}

	/**
	* @author wangchunping
	* @date 2016年5月24日 下午3:15:48
	* <p>Description:根据任务编号查找任务 </p>
	* <p>Paramter:code任务唯一code </p>
	* <p>Return: 返回任务Task</p>
	*/
	@Override
	public Task queryMyTaskByCode(String code)
	{
		Query query=getSession().createQuery(" from Task where fdTaskCode=:p1");
		query.setString("p1", code);
		Task  task=(Task) query.uniqueResult();
		return task;
	}
}
