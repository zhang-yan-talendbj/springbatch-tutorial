package com.alaincieslik.springbatch.article.jobIncrementer.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import com.alaincieslik.springbatch.article.jobIncrementer.model.Sample;

@Service("jobIncrementer.sampleProcessor")
public class SampleProcessor implements ItemProcessor<String, Sample>{

	public Sample process(String value){
		Sample exemple=new Sample();
		exemple.setData(value);
		exemple.setType(1);
		return exemple;
	}
}
