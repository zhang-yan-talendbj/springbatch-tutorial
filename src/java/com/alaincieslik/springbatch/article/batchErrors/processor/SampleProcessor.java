package com.alaincieslik.springbatch.article.batchErrors.processor;

import org.springframework.batch.item.ItemProcessor;

import com.alaincieslik.springbatch.article.batchErrors.model.Sample;

public class SampleProcessor implements ItemProcessor<String, Sample>{

	public Sample process(String value){
		Sample exemple=new Sample();
		exemple.setData(value);
		exemple.setType(1);
		return exemple;
	}
}
