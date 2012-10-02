package com.alaincieslik.springbatch.article.xmlstreaming.dao;

import org.springframework.stereotype.Repository;

import com.alaincieslik.springbatch.article.xmlstreaming.model.SkipItems;

@Repository("xmlstreaming.skipItemsDao")
public class SkipItemsDaoImpl extends BaseDao implements SkipItemsDao{

	private String insertStatement="INSERT INTO SKIP_ITEMS (type, item, msg,runId,jobExecutionId,stepExecutionId ) VALUES(?,?,?,?,?,?)";
	
	public void save(SkipItems skipElement){
		simpleJdbcTemplate.update(insertStatement, new Object[]{skipElement.getType(), skipElement.getItem(), skipElement.getMsg(), skipElement.getRunId(), skipElement.getJobExecutionId(), skipElement.getStepExecutionId()});
	}
	
}