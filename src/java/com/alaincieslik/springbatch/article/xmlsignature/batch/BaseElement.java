package com.alaincieslik.springbatch.article.xmlsignature.batch;

public abstract class BaseElement {

	protected Long jobExecutionId;
	protected Long stepExecutionId;
	protected String jobName;
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Long getStepExecutionId() {
		return stepExecutionId;
	}
	public void setStepExecutionId(Long stepExecutionId) {
		this.stepExecutionId = stepExecutionId;
	}
	public Long getJobExecutionId() {
		return jobExecutionId;
	}
	public void setJobExecutionId(Long jobExecutionId) {
		this.jobExecutionId = jobExecutionId;
	}
	
}
