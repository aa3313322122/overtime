package com.puban.overtime.task.service;

import com.puban.framework.core.page.PageView;
import com.puban.framework.core.page.QueryResult;
import com.puban.framework.core.service.IBaseService;
import com.puban.overtime.task.model.NormalTask;

public interface INormalService extends IBaseService<NormalTask>
{

	public boolean updateTaskByNormal(NormalTask task);
	
	public PageView<NormalTask> queryNormalList(Integer currentpage, Integer maxresult);
	
	/**
	 * 根据用户查询正常任务
	 */
	public QueryResult<NormalTask> queryNormalListByUser(Integer currentPage, Integer pageSzie, Integer userId);

	public boolean saveNormal(NormalTask task);
	
	public double findNormalByTaskId(Integer normalId, Integer userId);

}
