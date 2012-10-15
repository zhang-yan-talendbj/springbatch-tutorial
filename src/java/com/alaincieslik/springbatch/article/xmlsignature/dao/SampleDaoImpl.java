package com.alaincieslik.springbatch.article.xmlsignature.dao;


import org.springframework.stereotype.Repository;

import com.alaincieslik.springbatch.article.xmlsignature.model.Sample;


@Repository("xmlsignature.sampleDao")
public class SampleDaoImpl extends BaseDao implements SampleDao{

	private String insertStatement="INSERT INTO sample (data,type) VALUES (?, ?) ";
	
	public void save(Sample sample){
		simpleJdbcTemplate.update(insertStatement, new Object[]{sample.getData(), sample.getType()});
	}
	
}
