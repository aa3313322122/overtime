package com.puban.overtime.task.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.puban.framework.core.model.BaseModel;
import com.puban.overtime.authority.model.User;

@Entity
@Table(name = "t_normal")
public class NormalTask extends BaseModel
{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer fdId;

	/** 任务内容 **/
	@Column(name = "fd_normal_content")
	private String fdNormalContent;
	
	/** 任务实际非加班开始时间 **/
	@Column(name = "fd_normal_start_time")
	private String fdNormalStartTime;
	
	/** 任务实际非加班结束时间 **/
	@Column(name = "fd_normal_end_time")
	private String fdNormalEndTime;
	
	/** 任务实际非加班消耗时长 **/
	@Column(name = "fd_normal_task_time")
	private String fdNormalTaskTime;

	/** 任务领取者 **/
	@JoinColumn(name = "fd_user_id", updatable=false)
	@ManyToOne
	private User user;
	
	/** 多个申请来自于一个任务 **/
	@JoinColumn(name = "task_id", updatable=false)
	@ManyToOne
	private Task task;

	public Integer getFdId()
	{
		return fdId;
	}

	public void setFdId(Integer fdId)
	{
		this.fdId = fdId;
	}

	public String getFdNormalContent()
	{
		return fdNormalContent;
	}

	public void setFdNormalContent(String fdNormalContent)
	{
		this.fdNormalContent = fdNormalContent;
	}

	public String getFdNormalStartTime()
	{
		return fdNormalStartTime;
	}

	public void setFdNormalStartTime(String fdNormalStartTime)
	{
		this.fdNormalStartTime = fdNormalStartTime;
	}

	public String getFdNormalEndTime()
	{
		return fdNormalEndTime;
	}

	public void setFdNormalEndTime(String fdNormalEndTime)
	{
		this.fdNormalEndTime = fdNormalEndTime;
	}

	public String getFdNormalTaskTime()
	{
		return fdNormalTaskTime;
	}

	public void setFdNormalTaskTime(String fdNormalTaskTime)
	{
		this.fdNormalTaskTime = fdNormalTaskTime;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
	
	public Task getTask()
	{
		return task;
	}

	public void setTask(Task task)
	{
		this.task = task;
	}

	@Override
	public String toString()
	{
		return "NormalTask [fdId=" + fdId + ", fdNormalContent=" + fdNormalContent + ", fdNormalStartTime=" + fdNormalStartTime + ", fdNormalEndTime=" + fdNormalEndTime + ", fdNormalTaskTime=" + fdNormalTaskTime + ", user=" + user + "]";
	}

}
