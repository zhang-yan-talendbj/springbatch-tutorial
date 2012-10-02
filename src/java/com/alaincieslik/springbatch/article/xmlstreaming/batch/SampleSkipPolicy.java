package com.alaincieslik.springbatch.article.xmlstreaming.batch;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.stereotype.Repository;

@Repository("xmlstreaming.sampleSkipPolicy")
public class SampleSkipPolicy implements SkipPolicy{

	public boolean shouldSkip(Throwable t, int skipCount) throws SkipLimitExceededException {
		return SampleSkipException.class.isAssignableFrom(t.getClass());
	}

}
