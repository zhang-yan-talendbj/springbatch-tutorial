package com.alaincieslik.springbatch.article.executionFlow;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alaincieslik.springbatch.article.executionFlow.service.InfrastructureService;

@Service("executionflow.skippedItemReportStep")
public class SkippedItemReportStep implements Tasklet {
    
	@Autowired
	private InfrastructureService infrastructureService;
	
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		infrastructureService.generateReport(chunkContext.getStepContext().getStepExecution().getJobExecution());
        return RepeatStatus.FINISHED;
	}

	public InfrastructureService getInfrastructureService() {
		return infrastructureService;
	}

	public void setInfrastructureService(InfrastructureService infrastructureService) {
		this.infrastructureService = infrastructureService;
	}
       
} 