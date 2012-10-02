package com.alaincieslik.springbatch.article.xmlstreaming.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Repository;

import com.alaincieslik.springbatch.article.xmlstreaming.model.Item;
import com.alaincieslik.springbatch.article.xmlstreaming.model.Sample;


@Repository("xmlstreaming.sampleProcessor")
public class SampleProcessor implements ItemProcessor<Item, Sample>{
	private int i=0;
	
	public Sample process(Item value){
		Sample exemple=new Sample();
		exemple.setId(i++);
		exemple.setData(value.getData());
		exemple.setType(value.getType());
		if(i==3){
			throw new SampleSkipException();
		}
		return exemple;
	}


}
