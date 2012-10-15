package com.alaincieslik.springbatch.article.xmlsignature.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Service;

/**
 * 
 */
@Service("xmlsignature.xmlSignatureTasket")
public class XmlSignatureTasklet implements Tasklet{

	public RepeatStatus execute(StepContribution contribution,
								ChunkContext chunkContext) throws Exception {

		// Add Signature processing here 
		
		return RepeatStatus.FINISHED;
	}
	
}
