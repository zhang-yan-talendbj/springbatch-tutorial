package com.alaincieslik.springbatch.article.executionFlow.service;

import org.springframework.batch.core.JobExecution;

public interface InfrastructureService {

	public void generateReport(JobExecution jobExecution);
}
