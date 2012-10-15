package com.alaincieslik.springbatch.article.xmlsignature.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Service;

/**
 * 
 */
@Service("xmlsignature.xmlValidationTasket")
public class XmlValidationTasklet implements Tasklet{

	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {

		// Add Validation processing here 
		
		return RepeatStatus.FINISHED;
	}
	
}
