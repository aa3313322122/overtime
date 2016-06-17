package com.puban.overtime.apply.model;

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
import com.puban.overtime.task.model.Task;

@Table(name = "t_overtime")
@Entity
public class OverTime extends BaseModel
{
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer fdId;

	/** 多个加班申请来自于一个用户 **/
	@JoinColumn(name = "user_id", updatable=false)
	@ManyToOne
	private User user;

	/** 多个加班申请来自于一个任务 **/
	@JoinColumn(name = "task_id", updatable=false)
	@ManyToOne
	private Task task;

	/** 加班开始时间 **/
	@Column(name = "fd_start_time")
	private String fdStartTime;

	/** 加班结束时间 **/
	@Column(name = "fd_end_time")
	private String fdEndTime;

	/** 加班总时长 **/
	@Column(name = "fd_over_time")
	private String fdOverTime;

	/** 加班事由 **/
	@Column(name = "fd_reason")
	private String fdReason;

	/** 审批状态0未审批，1通过，2未通过 **/
	@Column(name = "fd_status")
	private Integer fdStatus = 0;

	/** 审批建议 **/
	@Column(name = "fd_advice")
	private String fdAdvice;

	public Integer getFdId()
	{
		return fdId;
	}

	public void setFdId(Integer fdId)
	{
		this.fdId = fdId;
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

	public String getFdOverTime()
	{
		return fdOverTime;
	}

	public void setFdOverTime(String fdOverTime)
	{
		this.fdOverTime = fdOverTime;
	}

	public String getFdReason()
	{
		return fdReason;
	}

	public void setFdReason(String fdReason)
	{
		this.fdReason = fdReason;
	}

	public Integer getFdStatus()
	{
		return fdStatus;
	}

	public void setFdStatus(Integer fdStatus)
	{
		this.fdStatus = fdStatus;
	}

	public String getFdAdvice()
	{
		return fdAdvice;
	}

	public void setFdAdvice(String fdAdvice)
	{
		this.fdAdvice = fdAdvice;
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
		return "OverTime [fdId=" + fdId + ", user=" + user + ", task=" + task + ", fdStartTime=" + fdStartTime + ", fdEndTime=" + fdEndTime + ", fdOverTime=" + fdOverTime + ", fdReason=" + fdReason + ", fdStatus=" + fdStatus + ", fdAdvice=" + fdAdvice + "]";
	}

}