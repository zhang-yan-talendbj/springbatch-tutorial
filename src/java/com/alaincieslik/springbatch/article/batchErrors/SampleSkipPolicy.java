package com.alaincieslik.springbatch.article.batchErrors;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.stereotype.Service;

@Service("batchErrors.skipPolicy")
public class SampleSkipPolicy implements SkipPolicy{

	public boolean shouldSkip(Throwable t, int skipCount) throws SkipLimitExceededException {
		return SampleSkipException.class.isAssignableFrom(t.getClass());
	}

}

