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
@Table(name = "t_task")
public class Task extends BaseModel
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer fdId;

	/** 任务编号 **/
	@Column(name = "fd_task_code")
	private String fdTaskCode;

	/** 任务主题 **/
	@Column(name = "fd_task_name")
	private String fdTaskName;

	/** 任务内容 **/
	@Column(name = "fd_task_content")
	private String fdTaskContent;

	/** 任务预计开始时间 **/
	@Column(name = "fd_start_time")
	private String fdStartTime;
	
	/** 任务预计结束时间 **/
	@Column(name = "fd_end_time")
	private String fdEndTime;

	/** 任务预计消耗时长 **/
	@Column(name = "fd_task_time")
	private Long fdTaskTime;

	/** 任务实际完成时间 **/
	@Column(name = "fd_finish_time")
	private String fdFinishTime;

	/** 任务实际消耗时长 **/
	@Column(name = "fd_actual_time")
	private Long fdActualTime;

	/** 任务接受者(一个任务分配给一个用户) **/
	@JoinColumn(name = "fd_user_id")
	@ManyToOne
	private User user;

	public Integer getFdId()
	{
		return fdId;
	}

	public void setFdId(Integer fdId)
	{
		this.fdId = fdId;
	}

	public String getFdTaskCode()
	{
		return fdTaskCode;
	}

	public void setFdTaskCode(String fdTaskCode)
	{
		this.fdTaskCode = fdTaskCode;
	}

	public String getFdTaskName()
	{
		return fdTaskName;
	}

	public void setFdTaskName(String fdTaskName)
	{
		this.fdTaskName = fdTaskName;
	}

	public String getFdTaskContent()
	{
		return fdTaskContent;
	}

	public void setFdTaskContent(String fdTaskContent)
	{
		this.fdTaskContent = fdTaskContent;
	}

	public String getFdStartTime()
	{
		return fdStartTime;
	}

	public void setFdStartTime(String fdStartTime)
	{
		this.fdStartTime = fdStartTime;
	}

	public String getFdEndTime()
	{
		return fdEndTime;
	}

	public void setFdEndTime(String fdEndTime)
	{
		this.fdEndTime = fdEndTime;
	}

	public Long getFdTaskTime()
	{
		return fdTaskTime;
	}

	public void setFdTaskTime(Long fdTaskTime)
	{
		this.fdTaskTime = fdTaskTime;
	}

	public String getFdFinishTime()
	{
		return fdFinishTime;
	}

	public void setFdFinishTime(String fdFinishTime)
	{
		this.fdFinishTime = fdFinishTime;
	}

	public Long getFdActualTime()
	{
		return fdActualTime;
	}

	public void setFdActualTime(Long fdActualTime)
	{
		this.fdActualTime = fdActualTime;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

}
