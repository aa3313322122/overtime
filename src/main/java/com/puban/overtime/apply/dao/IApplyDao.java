package com.puban.overtime.apply.dao;

import java.util.List;

import com.puban.framework.core.dao.IBaseDao;
import com.puban.overtime.apply.model.OverTime;

public interface IApplyDao extends IBaseDao<OverTime>
{
	public List<Object> findOverTimeByTaskId(Integer taskId);
}