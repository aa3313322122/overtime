package com.puban.overtime.task.dao;

import java.util.List;

import com.puban.framework.core.dao.IBaseDao;
import com.puban.overtime.task.model.NormalTask;

public interface INormalDao extends IBaseDao<NormalTask>
{
	public List<Object> findNormalByTaskId(Integer taskId, Integer userId);
}
