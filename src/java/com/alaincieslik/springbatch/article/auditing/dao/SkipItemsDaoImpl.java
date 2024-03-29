package com.alaincieslik.springbatch.article.auditing.dao;

import org.springframework.stereotype.Repository;

import com.alaincieslik.springbatch.article.auditing.model.SkipItems;

/*
CREATE TABLE SKIP_ITEMS (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    jobExecutionId INT,
    stepExecutionId INT,
    jobName VARCHAR(100),
    type VARCHAR(100),
    item VARCHAR(100),
    msg VARCHAR(1000),
    runId INT
)
 */
@Repository("auditing.skipItemsDao")
public class SkipItemsDaoImpl extends BaseDao implements SkipItemsDao{

	private String insertStatement="INSERT INTO SKIP_ITEMS (type, item, msg,runId,jobExecutionId,stepExecutionId ) VALUES(?,?,?,?,?,?)";
	
	public void save(SkipItems skipElement){
		simpleJdbcTemplate.update(insertStatement, new Object[]{skipElement.getType(), skipElement.getItem(), skipElement.getMsg(), skipElement.getRunId(), skipElement.getJobExecutionId(), skipElement.getStepExecutionId()});
	}
	
}
