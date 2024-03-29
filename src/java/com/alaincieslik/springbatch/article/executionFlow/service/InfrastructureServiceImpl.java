package com.alaincieslik.springbatch.article.executionFlow.service;

import java.util.Iterator;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("executionflow.infrastructure")
public class InfrastructureServiceImpl implements InfrastructureService{

	public void generateReport(JobExecution jobExecution){
		Iterator<StepExecution> stepExecutions=jobExecution.getStepExecutions().iterator();
		while(stepExecutions.hasNext()){
			StepExecution stepExecution=stepExecutions.next();
			System.out.println("StepName:"+stepExecution.getStepName());
			System.out.println("   ReadCount:"+stepExecution.getReadCount());
			System.out.println("   WriteCount:"+stepExecution.getWriteCount());
			System.out.println("   SkipCount:"+stepExecution.getSkipCount());
			System.out.println("");
		}
	}

}
