package com.alaincieslik.springbatch.article.auditing;

import java.util.Date;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.stereotype.Service;

@Service("auditing.jobIncrementer")
public class SampleJobIncrementer implements JobParametersIncrementer{

	public static String RUN_ID="runId";
	public static String EXECUTION_DATE="executionDate";
	
    public JobParameters getNext(JobParameters parameters) {
    	long id=0;
        if (parameters==null || parameters.isEmpty()) {
            id=1;
        }else{
        	id = parameters.getLong(RUN_ID,1L) + 1;
        }
        org.springframework.batch.core.JobParametersBuilder builder = new JobParametersBuilder();

        builder.addLong(RUN_ID, id).toJobParameters();
        builder.addLong("EXECUTION_DATE", new Date().getTime());

        return builder.toJobParameters();
    }
	
}


