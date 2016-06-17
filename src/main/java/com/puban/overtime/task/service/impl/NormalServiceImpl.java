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

import com.puban.framework.core.page.PageView;
import com.puban.framework.core.page.QueryResult;
import com.puban.framework.core.service.impl.BaseServiceImpl;
import com.puban.overtime.authority.model.User;
import com.puban.overtime.authority.service.IUserService;
import com.puban.overtime.task.dao.INormalDao;
import com.puban.overtime.task.model.NormalTask;
import com.puban.overtime.task.model.Task;
import com.puban.overtime.task.service.INormalService;
import com.puban.overtime.task.service.ITaskService;

@Service
public class NormalServiceImpl extends BaseServiceImpl<NormalTask> implements INormalService
{

	private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm";
	
	@Autowired
	private ITaskService taskService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private INormalDao normalDao;
	
	@Override
	public double findNormalByTaskId(Integer normalId, Integer userId)
	{
		List<Object> list = normalDao.findNormalByTaskId(normalId, userId);
		if(null != list && list.size() > 0)
		{
			double d = 0.0;
			for(Object o : list)
			{
				if(null != o)
				{
					d += new Double(o.toString());
				}
			}
			return d;
		}
		return 0;
	}
	
	/** 增加**/
	public boolean saveNormal(NormalTask normal)
	{
		if(null == normal)
		{
			return false;
		}
		Integer taskId;
		Task task;
		if(null == (task = normal.getTask()) || null == (taskId = task.getFdId()))
		{
			return false;
		}
		taskService.findByPrimaryKey(taskId);
		normal.setTask(task);
		Integer userId;
		User user;
		if(null == (user = normal.getUser()) || null == (userId = user.getFdId()))
		{
			return false;
		}
		user = userService.findByPrimaryKey(userId);
		normal.setUser(user);
		normal.setFdNormalTaskTime(minusTime(normal.getFdNormalStartTime(), 
				normal.getFdNormalEndTime(), TIME_FORMAT));
		this.save(normal);
		return true;
	}
	
	/** 修改**/
	@Override
	public boolean updateTaskByNormal(NormalTask normal)
	{
		Integer taskId = normal.getFdId();
		if(null == taskId)
		{
			return false;
		}
		NormalTask t = this.findByPrimaryKey(taskId);
		if(null == t)
		{
			return false;
		}
		
		if(null != normal.getFdNormalStartTime() && null != normal.getFdNormalEndTime()
				&& !"".equals(normal.getFdNormalStartTime()) && !"".equals(normal.getFdNormalEndTime()))
		{
			t.setFdNormalStartTime(normal.getFdNormalStartTime());
			t.setFdNormalEndTime(normal.getFdNormalEndTime());
			t.setFdNormalTaskTime(minusTime(normal.getFdNormalStartTime(), 
					normal.getFdNormalEndTime(), TIME_FORMAT));
		}
		if(null != normal.getFdNormalContent() && !"".equals(normal.getFdNormalContent()))
		{
			t.setFdNormalContent(normal.getFdNormalContent());
		}
		this.update(t);
		return true;
	}
	
	/** 分页查询当前用户**/
	@Override
	public QueryResult<NormalTask> queryNormalListByUser(Integer currentPage, Integer pageSzie, Integer userId)
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
		orderby.put("fdNormalStartTime", "desc");
		
		QueryResult<NormalTask> myTaskListPage = this.query((currentPage - 1) * pageSzie,
				pageSzie, sb.toString(), queryParams.toArray(), orderby);
		return myTaskListPage;
	}
	
	/**
	 * 分页查询所有
	 */
	public PageView<NormalTask> queryNormalList(Integer currentpage, Integer maxresult)
	{
		StringBuffer sb = new StringBuffer("");
		sb.append(" 1=1 ");
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("fdNormalStartTime", "desc");
		QueryResult<NormalTask> result = this.query((currentpage - 1)*maxresult, maxresult, sb.toString(), null, orderby);
		PageView<NormalTask> pageView = new PageView<NormalTask>(maxresult, currentpage);
		pageView.setQueryResult(result);
		return pageView;
	}
	
	public static String minusTime(String startTime, String endTime, String format)
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

	public static String getTime(long time)
	{
		Double dt = time / 60.0 / 60 / 1000;
		dt = chooseTime(dt);
		DecimalFormat df = new DecimalFormat("0.0");
		return df.format(dt);
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
