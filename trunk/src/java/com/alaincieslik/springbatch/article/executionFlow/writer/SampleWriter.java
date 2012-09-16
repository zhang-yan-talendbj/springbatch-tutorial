package com.alaincieslik.springbatch.article.executionFlow.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alaincieslik.springbatch.article.executionFlow.BaseElement;
import com.alaincieslik.springbatch.article.executionFlow.dao.SampleDao;
import com.alaincieslik.springbatch.article.executionFlow.model.Sample;

@Service("executionflow.sampleWriter")
public class SampleWriter extends BaseElement implements ItemWriter<Sample> {

	StringBuilder sb = new StringBuilder();
	@Autowired
	private SampleDao sampleDao;
	
	public void write(List<? extends Sample> items) throws Exception {
		for(Sample exemple: items){
			Integer id=new Integer(exemple.getData().substring(5));
			sampleDao.save(exemple);
		}
	}
	
	public SampleDao getSampleDao() {
		return sampleDao;
	}

	public void setSampleDao(SampleDao sampleDao) {
		this.sampleDao = sampleDao;
	}
}

