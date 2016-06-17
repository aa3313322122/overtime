package com.puban.framework.core.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.puban.framework.core.dao.IBaseDao;
import com.puban.framework.core.page.QueryResult;
import com.puban.framework.core.service.IBaseService;

@SuppressWarnings("unchecked")
@Service("baseService")
@Lazy(true)
public class BaseServiceImpl<T> implements IBaseService<T>
{
	private Class<T> clazz;

	@Resource
	private IBaseDao<T> baseDao;

	public void save(Object entity)
	{
		baseDao.save(entity);
	}

	public void saveOrUpdate(Object entity)
	{
		baseDao.saveOrUpdate(entity);
	}

	public void update(Object entity)
	{
		baseDao.update(entity);
	}

	public void remove(Object entityid)
	{
		baseDao.remove(clazz, entityid);
	}

	public T findByPrimaryKey(Serializable entityId)
	{
		return baseDao.find(clazz, entityId);
	}

	public QueryResult<T> query()
	{
		return baseDao.query(clazz);
	}

	public QueryResult<T> query(int firstindex, int maxresult)
	{
		return baseDao.query(clazz, firstindex, maxresult);
	}

	public QueryResult<T> query(int firstindex, int maxresult, LinkedHashMap<String, String> orderby)
	{
		return baseDao.query(clazz, firstindex, maxresult, orderby);
	}

	public QueryResult<T> query(int firstindex, int maxresult, String wherejpql, Object[] queryParams)
	{
		return baseDao.query(clazz, firstindex, maxresult, wherejpql, queryParams);
	}

	public QueryResult<T> query(int firstindex, int maxresult, String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby)
	{
		return baseDao.query(clazz, firstindex, maxresult, wherejpql, queryParams, orderby);
	}

	public BaseServiceImpl()
	{
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = ((Class<T>) type.getActualTypeArguments()[0]);
	}
}
