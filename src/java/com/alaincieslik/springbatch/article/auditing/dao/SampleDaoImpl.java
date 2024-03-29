package com.alaincieslik.springbatch.article.auditing.dao;

import org.springframework.stereotype.Repository;

import com.alaincieslik.springbatch.article.auditing.model.Sample;

/*
CREATE TABLE sample (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    data VARCHAR(100),
    type INT(100)
);


);
 */
@Repository("auditing.sampleDao")
public class SampleDaoImpl extends BaseDao implements SampleDao{

	private String insertStatement="INSERT INTO sample (data,type) VALUES (?, ?) ";
	
	public void save(Sample sample){
		simpleJdbcTemplate.update(insertStatement, new Object[]{sample.getData(), sample.getType()});
	}
	
}

