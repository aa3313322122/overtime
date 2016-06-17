package com.puban.framework.core.dao;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.puban.framework.core.page.QueryResult;

public interface IBaseDao<T>
{
	public void save(Object entity);

	public void saveOrUpdate(Object entity);

	public void update(Object entity);

	public void remove(Class<T> entityClass, Object entityid);

	public T find(Class<T> entityClass, Serializable entityId);

	public QueryResult<T> query(Class<T> entityClass);

	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult);

	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult, LinkedHashMap<String, String> orderby);

	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult, String wherejpql, Object[] queryParams);

	public QueryResult<T> query(Class<T> entityClass, int firstindex, int maxresult, String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby);

}
