package com.alaincieslik.springbatch.article.auditing.processor;

import org.springframework.batch.item.ItemProcessor;

import com.alaincieslik.springbatch.article.auditing.BaseElement;
import com.alaincieslik.springbatch.article.auditing.model.Sample;

public class SampleProcessor extends BaseElement implements ItemProcessor<String, Sample>{

	public Sample process(String value){
		Sample exemple=new Sample();
		exemple.setData(value);
		exemple.setType(1);
		return exemple;
	}
}
