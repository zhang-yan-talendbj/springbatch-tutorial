package com.alaincieslik.springbatch.article.executionFlow.processor;

import org.springframework.batch.item.ItemProcessor;

import com.alaincieslik.springbatch.article.executionFlow.BaseElement;
import com.alaincieslik.springbatch.article.executionFlow.model.Sample;

public class SampleProcessor extends BaseElement implements ItemProcessor<String, Sample>{

	public Sample process(String value){
		Sample exemple=new Sample();
		exemple.setData(value);
		exemple.setType(1);
		return exemple;
	}
}
