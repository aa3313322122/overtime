package com.puban.framework.core.service;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.puban.framework.core.page.QueryResult;

public interface IBaseService<T>
{
	public void save(Object entity);

	public void saveOrUpdate(Object entity);

	public void update(Object entity);

	public void remove(Object entityid);

	public T findByPrimaryKey(Serializable entityId);
	
	public QueryResult<T> query();

	public QueryResult<T> query(int firstindex, int maxresult);

	public QueryResult<T> query(int firstindex, int maxresult, LinkedHashMap<String, String> orderby);

	public QueryResult<T> query(int firstindex, int maxresult, String wherejpql, Object[] queryParams);

	public QueryResult<T> query(int firstindex, int maxresult, String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby);
}
