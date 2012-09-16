package com.alaincieslik.springbatch.article.executionFlow.dao;

import org.springframework.stereotype.Service;

import com.alaincieslik.springbatch.article.executionFlow.model.Sample;

/*
CREATE TABLE sample (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    data VARCHAR(100),
    type INT(100)
);


);
 */
@Service("executionflow.sampleDao")
public class SampleDaoImpl extends BaseDao implements SampleDao{

	private String insertStatement="INSERT INTO sample (data,type) VALUES (?, ?) ";
	
	public void save(Sample sample){
		simpleJdbcTemplate.update(insertStatement, new Object[]{sample.getData(), sample.getType()});
	}
	
}

