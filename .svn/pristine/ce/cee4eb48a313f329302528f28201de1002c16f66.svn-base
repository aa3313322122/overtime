package com.puban.overtime.task.service.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.puban.framework.core.page.PageView;
import com.puban.framework.core.page.QueryResult;
import com.puban.framework.core.service.impl.BaseServiceImpl;
import com.puban.overtime.apply.service.IApplyService;
import com.puban.overtime.authority.model.User;
import com.puban.overtime.authority.service.IUserService;
import com.puban.overtime.task.dao.ITaskDao;
import com.puban.overtime.task.model.Task;
import com.puban.overtime.task.service.INormalService;
import com.puban.overtime.task.service.ITaskService;


/*** 
* @author wangchunping 
* @date 2016年5月25日 上午9:40:43 
* <p>Title:TaskServiceImpl </p>
* <p>Description:任务实现类 </p>
*/
@Service
public class TaskServiceImpl extends BaseServiceImpl<Task> implements ITaskService
{
	private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm";
	
	@Autowired
	private ITaskDao taskDao;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IApplyService applyService;
	
	@Autowired
	INormalService normalService;

	/** 查询**/
	public Model queryOverNormalList(List<Task> tasks, Model model)
	{
		List<Task> list = new ArrayList<Task>();
		List<String> normalTimes = new ArrayList<String>();
		List<String> ovetTimes = new ArrayList<String>();
		DecimalFormat df = new DecimalFormat("0.0");
		list = tasks;
		double ovetTime;
		double normalTime;
		for(Task task : list)
		{
			Integer taskId;
			if(null == task || null == (taskId = task.getFdId()))
			{
				return model;
			}
			ovetTime = applyService.findListByTaskId(taskId);
			ovetTimes.add(df.format(ovetTime));
			User user = task.getUser();
			Integer userId;
			if(null == user || null == (userId = user.getFdId()))
			{
				return model;
			}
			normalTime = normalService.findNormalByTaskId(taskId, userId);
			normalTimes.add(df.format(normalTime));
		}
		model.addAttribute("ovetTimes", ovetTimes);
		model.addAttribute("normalTimes", normalTimes);
		return model;
	}
	
	
	/** 修改已分配任务**/
	@Override
	public boolean updateTaskByGive(Task task)
	{
		Integer taskId = task.getFdId();
		if(null == taskId)
		{
			return false;
		}
		Task t = this.findByPrimaryKey(taskId);
		if(null == t)
		{
			return false;
		}
		User user = task.getUser();
		if(null == user.getFdId())
		{
			return false;
		}
		user = userService.findByPrimaryKey(user.getFdId());
		t.setUser(user);
		if(null != task.getFdStartTime() && null != task.getFdEndTime()
				&& !"".equals(task.getFdStartTime()) && !"".equals(task.getFdEndTime()))
		{
			t.setFdStartTime(task.getFdStartTime());
			t.setFdEndTime(task.getFdEndTime());
			t.setFdTaskTime(minusTime(task.getFdStartTime(), 
					task.getFdEndTime(), TIME_FORMAT));
		}
		if(null != task.getFdTaskContent() && !"".equals(task.getFdTaskContent()))
		{
			t.setFdTaskContent(task.getFdTaskContent());
		}
		this.update(t);
		return true;
	}
	
	/** 分配任务**/
	@Override
	public boolean saveNewTask(Task task)
	{
		if(null == task)
		{
			return false;
		}
		User user = null;
		if(null == (user = task.getUser()))
		{
			return false;
		}
		Integer userId = null;
		if(null == user || null == (userId = user.getFdId()))
		{
			return false;
		}
		user = userService.findByPrimaryKey(userId);
		task.setUser(user);
		task.setFdTaskTime(minusTime(task.getFdStartTime(), 
				task.getFdEndTime(), TIME_FORMAT));
		this.save(task);
		return true;
		
	}
	
	/**
	 * 查询分配的全部任务
	 */
	public PageView<Task> queryGiveList(Integer currentpage, Integer maxresult)
	{
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("fdStartTime", "desc");
		QueryResult<Task> result = this.query((currentpage-1)*maxresult, maxresult, null, null, orderby);
		PageView<Task> pageView = new PageView<Task>(maxresult, currentpage);
		pageView.setQueryResult(result);
		return pageView;
	}
	

	/**
	* @author wangchunping
	* @date 2016年5月24日 上午11:21:45
	* <p>Description: 查询出所有任务</p>
	* <p>Paramter:currentPage 当前页, pageSzie每页显示记录数 </p>
	* <p>Return: 返回任务分页列表对象QueryResult<Task></p>
	*/
	@Override
	public QueryResult<Task> queryTaskList(Integer currentPage, Integer pageSzie)
	{
		QueryResult<Task> taskListPage = taskDao.query(Task.class, (currentPage - 1) * pageSzie, pageSzie);
		return taskListPage;
	}

	/**
	* @author wangchunping
	* @date 2016年5月24日 上午11:23:04
	* <p>Description:查询我的所有任务 </p>
	* <p>Paramter:currentPage 当前页, pageSzie每页显示记录数 ,userId当前用户ID </p>
	* <p>Return: 返回任务分页列表对象QueryResult<Task></p>
	*/
	@Override
	public QueryResult<Task> queryMyTaskList(Integer currentPage, Integer pageSzie, Integer userId)
	{
		List<Object> queryParams = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer("");
		sb.append(" 1=1 ");
		if (userId > 0)
		{
			int index = queryParams.size() + 1;
			sb.append(" and user.fdId=:p" + index).append(" ");
			queryParams.add(userId);
		}
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("fdStartTime", "desc");
		
		QueryResult<Task> myTaskListPage = taskDao.query(Task.class, (currentPage - 1) * pageSzie,
				pageSzie, sb.toString(), queryParams.toArray(), orderby);
		return myTaskListPage;
	}

	/**
	* @author wangchunping
	* @date 2016年5月24日 上午11:23:04
	* <p>Description:查询我的所有任务 </p>
	* <p>Paramter:currentPage 当前页, pageSzie每页显示记录数 ,userId当前用户ID </p>
	* <p>Return: 返回任务分页列表对象QueryResult<Task></p>
	*/
	@Override
	public List<Task> queryTaskList()
	{
		List<Task> taskList=taskDao.query(Task.class).getResultlist();
		return taskList;
	}

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
		return taskDao.queryMyTaskList(userId);
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
		return taskDao.queryMyTaskByCode(code);
	}
	
	public static Long minusTime(String startTime, String endTime, String format)
	{
		SimpleDateFormat sf = new SimpleDateFormat(format);
		Date st;
		Date et;
		try
		{
			st = sf.parse(startTime);
			et = sf.parse(endTime);
		}
		catch (ParseException e)
		{
			return null;
		}
		return getTime(et.getTime() - st.getTime());
	}
	
	public static Long getTime(long time)
	{
		Double dt = time / 60.0 / 60 / 1000;
		dt = chooseTime(dt);
		DecimalFormat df = new DecimalFormat("0.0");
		return (long) ((Double.parseDouble(df.format(dt)))*60*60*1000);
	}
	
	public static Double getRealTime(Double time)
	{
		Integer i = (Integer)(time.intValue()/24);
		int day = i*8;
		int d = i*24;
		double hour = time - d;
		hour = chooseTime(hour);
		return hour + day;
	}
	
	public static Double chooseTime(Double time)
	{
		if(time <= 8.0)
		{
			return time;
		}
		else
		{
			if(time/24 <= 1.0)
			{
				time = 8.0;
			}
			else
			{
				time = getRealTime(time);
			}
			return time;
		}
	}

}
