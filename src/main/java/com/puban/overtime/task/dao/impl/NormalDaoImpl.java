package com.puban.overtime.task.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.puban.framework.core.dao.impl.BaseDaoImpl;
import com.puban.overtime.task.dao.INormalDao;
import com.puban.overtime.task.model.NormalTask;

@Repository
public class NormalDaoImpl extends BaseDaoImpl<NormalTask> implements INormalDao
{
	@Override
	public List<Object> findNormalByTaskId(Integer taskId, Integer userId)
	{
		Criteria c = this.getSession().createCriteria(NormalTask.class);
		@SuppressWarnings("unchecked")
		List<Object> list = c.createAlias("task", "t")
				.add(Restrictions.eq("t.fdId", taskId))
				.createAlias("user", "u")
				.add(Restrictions.eq("u.fdId", userId))
				.setProjection(Projections.property("fdNormalTaskTime")).list();
		return list;
		
	}

}
