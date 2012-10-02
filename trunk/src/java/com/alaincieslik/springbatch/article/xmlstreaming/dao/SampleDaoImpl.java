package com.alaincieslik.springbatch.article.xmlstreaming.dao;


import org.springframework.stereotype.Repository;

import com.alaincieslik.springbatch.article.xmlstreaming.model.Sample;


@Repository("xmlstreaming.sampleDao")
public class SampleDaoImpl extends BaseDao implements SampleDao{

	private String insertStatement="INSERT INTO sample (data,type) VALUES (?, ?) ";
	
	public void save(Sample sample){
		simpleJdbcTemplate.update(insertStatement, new Object[]{sample.getData(), sample.getType()});
	}
	
}
