package com.puban.overtime.apply.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.puban.framework.core.dao.impl.BaseDaoImpl;
import com.puban.overtime.apply.dao.IApplyDao;
import com.puban.overtime.apply.model.OverTime;

@Repository
public class ApplyDaoImpl extends BaseDaoImpl<OverTime> implements IApplyDao
{
	public List<Object> findOverTimeByTaskId(Integer taskId)
	{
		Criteria c = this.getSession().createCriteria(OverTime.class);
		@SuppressWarnings("unchecked")
		List<Object> list = c.add(Restrictions.eq("fdStatus", 1))
							.createAlias("task", "t")
							.add(Restrictions.eq("t.fdId", taskId))
							.setProjection(Projections.property("fdOverTime")).list();
		return list;
		
	}
}
