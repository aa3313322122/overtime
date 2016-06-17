package com.puban.apply;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.puban.framework.core.page.QueryResult;
import com.puban.overtime.apply.model.OverTime;
import com.puban.overtime.apply.service.IApplyService;
import com.puban.overtime.authority.model.User;
import com.puban.overtime.authority.service.IUserService;
import com.puban.overtime.task.model.NormalTask;
import com.puban.overtime.task.model.Task;
import com.puban.overtime.task.service.INormalService;
import com.puban.overtime.task.service.ITaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:config/*/applicationContext-*.xml" })
public class ApplyTest
{
	@Autowired
	IApplyService applyService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	ITaskService taskService;
	
	@Autowired
	INormalService normalService;
	
	/** 查询**/
	@Test
	public String queryTask()
	{
		QueryResult<Task> tasks = taskService.queryTaskList(1, 5);
		List<Task> list = new ArrayList<Task>();
		list = tasks.getResultlist();
		double ovetTime;
		double normalTime;
		for(Task task : list)
		{
			Integer taskId;
			if(null == task || null == (taskId = task.getFdId()))
			{
				return null;
			}
			ovetTime = applyService.findListByTaskId(taskId);
			User user = task.getUser();
			Integer userId;
			if(null == user || null == (userId = user.getFdId()))
			{
				return null;
			}
			normalTime = normalService.findNormalByTaskId(taskId,userId);
		}
		DecimalFormat df = new DecimalFormat("0.0");
		return null;
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
	
	/** 正常任务领取**/
	@Test
	public void saveNormal()
	{
		NormalTask normal = new NormalTask();
		Task task = taskService.findByPrimaryKey(1);
		normal.setTask(task);
		User user = userService.findByPrimaryKey(3);
		normal.setUser(user);
		normal.setFdNormalContent("234");
		normalService.save(normal);
	}
	
	@Test
	public void updateNormal()
	{
		NormalTask normal = normalService.findByPrimaryKey(1);
		normal.setFdNormalTaskTime("3.4");
		normalService.update(normal);
	}
	
	
	
	/**加班申请**/
	@Test
	public void addOverTime()
	{
		OverTime overTime = new OverTime();
		overTime.setFdStatus(1);
		overTime.setFdReason("ggg");
		overTime = selectUser(1, overTime);
		overTime = selectTask(1, overTime);
		if(null != overTime)
		{
			applyService.save(overTime);
		}
		
	}
	
	private OverTime selectUser(Integer userId, OverTime overTime)
	{
		User user = userService.findByPrimaryKey(userId);
		if(null != user)
		{
			overTime.setUser(user);
			return overTime;
		}
		return null;
	}
	
	private OverTime selectTask(Integer taskId, OverTime overTime)
	{
		Task task = taskService.findByPrimaryKey(taskId);
		if(null != task)
		{
			overTime.setTask(task);
			return overTime;
		}
		return null;
	}
	
	/**查找加班申请**/
	@Test
	public void findOverTime()
	{
		applyService.findByPrimaryKey(7);
	}
	
	/**回填审批意见**/
	@Test
	public void updateOverTime()
	{
		OverTime overTime = applyService.findByPrimaryKey(7);
		if(null != overTime)
		{
			overTime.setFdAdvice("no");
			applyService.update(overTime);
		}
	}
	
	/**批复加班申请**/
	@Test
	public void updateOverTimeByStatus()
	{
		OverTime overTime = applyService.findByPrimaryKey(7);
		if(null != overTime)
		{
			overTime.setFdStatus(1);
			applyService.update(overTime);
		}
	}
	
	
	/**根据申请人查询加班申请**/
	@Test
	public void queryOverTimeByUser()
	{
		int firstindex = 1;
		int maxresult = 5;
		String wheresql = "user_id = '1'";
		QueryResult<OverTime> queryResult = applyService.
				query(firstindex, maxresult, wheresql, null);
		queryResult.getResultlist();
		queryResult.getTotalrecord();
	}
	
	/**根据审批状态查询加班申请**/
	@Test
	public void queryOverTimeByStatus()
	{
		int firstindex = 1;
		int maxresult = 30;
		String hql = "fd_status = '1' AND fd_start_time IS NOT NULL";
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("fdStartTime", "desc");
		QueryResult<OverTime> queryResult = applyService.
				query(firstindex, maxresult, hql, null, orderby);
		queryResult.getResultlist();
		queryResult.getTotalrecord();
	}
	
	/**分页查询**/
	@Test
	public void queryOverTime()
	{
		int firstindex = 1;
		int maxresult = 5;
		QueryResult<OverTime> queryResult = applyService.query(firstindex, maxresult);
		queryResult.getResultlist();
		queryResult.getTotalrecord();
	}
}
