package com.alaincieslik.springbatch.article.batchErrors.dao;

import org.springframework.stereotype.Repository;

import com.alaincieslik.springbatch.article.batchErrors.model.Sample;

/*
CREATE TABLE sample (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    data VARCHAR(100),
    type INT(100)
);


);
 */
@Repository("batchErrors.sampleDao")
public class SampleDaoImpl extends BaseDao implements SampleDao{

	private String insertStatement="INSERT INTO sample (data,type) VALUES (?, ?) ";
	
	public void save(Sample sample){
		simpleJdbcTemplate.update(insertStatement, new Object[]{sample.getData(), sample.getType()});
	}
	
}

