package com.puban.overtime.apply.service.impl;

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
import com.puban.overtime.apply.dao.IApplyDao;
import com.puban.overtime.apply.model.OverTime;
import com.puban.overtime.apply.service.IApplyService;
import com.puban.overtime.authority.model.User;
import com.puban.overtime.authority.service.IUserService;
import com.puban.overtime.task.model.Task;
import com.puban.overtime.task.service.ITaskService;

@Service
public class ApplyServiceImpl extends BaseServiceImpl<OverTime> implements IApplyService
{
	
	private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm";
	
	@Autowired
	IUserService userService;

	@Autowired
	ITaskService taskService;
	
	@Autowired
	IApplyDao applyDao;
	
	/** 根据任务ID查询**/
	public double findListByTaskId(Integer taskId)
	{
		List<Object> list = applyDao.findOverTimeByTaskId(taskId);
		if(null != list && list.size() > 0)
		{
			double d = 0.0;
			for(Object o : list)
			{
				d += new Double(o.toString());
			}
			return d;
		}
		return 0;
	}

	/** 加班申请 **/
	public boolean saveOverTime(OverTime overTime)
	{
		User user = null;
		Task task = null;
		if (null == (user = overTime.getUser()))
		{
			return false;
		}
		if (null == (task = overTime.getTask()))
		{
			return false;
		}

		String userName = user.getFdUserName();
		if ((null == userName || "".equals(userName)))
		{
			return false;
		}
		Integer taskId = task.getFdId();
		if (null == taskId)
		{
			return false;
		}
		user = userService.getUserInfoByName(userName);
		Integer userId = null;
		if (null == user || null == (userId = user.getFdId()))
		{
			return false;
		}
		task = taskService.findByPrimaryKey(taskId);
		if (null == task)
		{
			return false;
		}
		overTime = selectUser(userId, overTime);
		overTime = selectTask(taskId, overTime);
		if (null != overTime)
		{
			overTime.setFdOverTime(minusTime(overTime.getFdStartTime(), overTime.getFdEndTime(), TIME_FORMAT));
			this.save(overTime);
			return true;
		}
		return false;
	}

	public PageView<OverTime> queryOverTime(Integer currentpage, Integer maxresult)
	{
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("fdStartTime", "desc");
		QueryResult<OverTime> result = this.query((currentpage - 1) * maxresult, maxresult, null, null, orderby);
		PageView<OverTime> pageView = new PageView<OverTime>(maxresult, currentpage);
		pageView.setQueryResult(result);
		return pageView;
	}

	/** 根据申请人查询加班申请 **/
	public PageView<OverTime> queryOverTimeByUser(Integer userId, Integer currentpage, Integer maxresult)
	{
		List<Object> queryParams = new ArrayList<Object>();
		StringBuffer wheresql = new StringBuffer("");
		wheresql.append(" 1=1 ");
		if (userId > 0)
		{
			int index = queryParams.size() + 1;
			wheresql.append(" and user.fdId=:p" + index).append(" ");
			queryParams.add(userId);
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("fdStartTime", "desc");

		QueryResult<OverTime> result = this.query((currentpage - 1) * maxresult, maxresult, wheresql.toString(), queryParams.toArray(), orderby);

		PageView<OverTime> pageView = new PageView<OverTime>(maxresult, currentpage);
		pageView.setQueryResult(result);
		return pageView;
	}

	/** 根据审批状态查询加班申请 **/
	public PageView<OverTime> queryOverTimeByStatus(Integer status, Integer userId, Integer currentpage, Integer maxresult)
	{
		StringBuffer sb = new StringBuffer("");
		sb.append(" 1=1 ");
		
		if (userId > 0)
		{
			sb.append(" and user.fdId=" + userId).append(" ");
		}
		if (status >= 0)
		{
			sb.append("and fd_status = " + status + "");
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("fdStartTime", "desc");

		QueryResult<OverTime> result = this.query((currentpage - 1) * maxresult, maxresult, sb.toString(), null, orderby);
		PageView<OverTime> pageView = new PageView<OverTime>(maxresult, currentpage);
		pageView.setQueryResult(result);
		return pageView;
	}

	private OverTime selectUser(Integer userId, OverTime overTime)
	{
		User user = userService.findByPrimaryKey(userId);
		if (null != user)
		{
			overTime.setUser(user);
			return overTime;
		}
		return null;
	}

	private OverTime selectTask(Integer taskId, OverTime overTime)
	{
		Task task = taskService.findByPrimaryKey(taskId);
		if (null != task)
		{
			overTime.setTask(task);
			return overTime;
		}
		return null;
	}
	
	@Override
	public void updateOverTime(OverTime overTime)
	{
		overTime.setFdOverTime(minusTime(overTime.getFdStartTime(), overTime.getFdEndTime(), TIME_FORMAT));
		this.update(overTime);
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