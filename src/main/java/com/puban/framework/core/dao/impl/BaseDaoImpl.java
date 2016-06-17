package com.puban.framework.core.dao.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.puban.framework.core.dao.IBaseDao;
import com.puban.framework.core.page.QueryResult;

@SuppressWarnings("unchecked")
@Repository("baseDao")
public class BaseDaoImpl<T> implements IBaseDao<T>
{

	@Resource
	private SessionFactory sessionFactory;

	protected Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Object entity)
	{
		getSession().save(entity);
	}

	@Override
	public void saveOrUpdate(Object entity)
	{
		getSession().saveOrUpdate(entity);
	}

	@Override
	public void update(Object entity)
	{
		getSession().update(entity);
	}

	@Override
	public void remove(Class<T> entityClass, Object entityid)
	{
		this.getSession().delete(this.getSession().get(entityClass, (Serializable) entityid));
	}

	@Override
	public T find(Class<T> entityClass, Serializable entityId)
	{
		return ((T) this.getSession().get(entityClass, entityId));
	}

	@Override
	public QueryResult<T> query(Class<T> entityClass)
	{
		return this.query(entityClass, -1, -1);
	}

	@Override
	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult)
	{
		return this.query(entityClass, firstindex, maxresult, null, null, null);
	}

	@Override
	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult, LinkedHashMap<String, String> orderby)
	{
		return this.query(entityClass, firstindex, maxresult, null, null, orderby);
	}

	@Override
	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult, String wherejpql, Object[] queryParams)
	{
		return this.query(entityClass, firstindex, maxresult, wherejpql, queryParams, null);
	}

	@Override
	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult, String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby)
	{
		QueryResult<T> queryResult = new QueryResult<T>();

		String entiryName = this.getEntityName(entityClass);

		Query query = getSession().createQuery(" select o from " + entiryName + " o " + (wherejpql == null ? "" : " where " + wherejpql) + " " + buildOrderBy(orderby));

		this.setQueryParams(query, queryParams);

		if (firstindex != -1 && maxresult != -1)
		{
			query.setFirstResult(firstindex);
			query.setMaxResults(maxresult);
		}

		List<T> resultlist = query.list();

		queryResult.setResultlist(resultlist);

		query = getSession().createQuery(" select count(o) from " + entiryName + " o " + (wherejpql == null ? "" : " where " + wherejpql) + " " + buildOrderBy(orderby));

		this.setQueryParams(query, queryParams);

		queryResult.setTotalrecord((Long) query.uniqueResult());

		return queryResult;
	}

	protected String getEntityName(Class<T> entityClass)
	{
		String entiryName = entityClass.getSimpleName();

		return entiryName;
	}

	protected String buildOrderBy(LinkedHashMap<String, String> orderby)
	{
		StringBuffer orderbyhql = new StringBuffer(" ");

		if (orderby != null && orderby.size() > 0)
		{
			orderbyhql.append(" order by ");

			for (String key : orderby.keySet())
			{
				orderbyhql.append(" o.").append(key).append(" ").append(orderby.get(key)).append(",");
			}

			orderbyhql.deleteCharAt(orderbyhql.length() - 1);
		}

		return orderbyhql.toString();
	}

	protected void setQueryParams(Query query, Object[] queryParams)
	{
		if (queryParams != null && queryParams.length > 0)
		{
			for (int i = 0; i < queryParams.length; i++)
			{
				query.setParameter("p" + (i + 1), queryParams[i]);
			}
		}
	}

}
