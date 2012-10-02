package com.alaincieslik.springbatch.article.xmlstreaming.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class BaseDao {

	protected SimpleJdbcTemplate simpleJdbcTemplate;
	@Autowired
	public void setDataSource(DataSource dataSource) {
	    this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
}