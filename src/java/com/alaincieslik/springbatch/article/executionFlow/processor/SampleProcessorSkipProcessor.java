package com.alaincieslik.springbatch.article.executionFlow.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import com.alaincieslik.springbatch.article.executionFlow.SampleSkipException;
import com.alaincieslik.springbatch.article.executionFlow.model.Sample;

@Service("executionflow.sampleProcessor")
public class SampleProcessorSkipProcessor extends SampleProcessor implements ItemProcessor<String, Sample>{

	public Sample process(String value){
		Integer id=new Integer(value.substring(5));
		Sample sample=super.process(value);
		//Filter this element
		if(id==45){
			return null;
		}
		//Skip this element
		if(id==95){
			throw new SampleSkipException(); 
		}
		return sample;
	}
}
