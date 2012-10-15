package com.alaincieslik.springbatch.article.xmlsignature.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(namespace="http://alain-cieslik.com/xmlstreaming")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class Sample {
	@XmlElement(namespace="http://alain-cieslik.com/xmlstreaming")	
	private Integer id;
	@XmlElement(namespace="http://alain-cieslik.com/xmlstreaming")	
	private String data;
	@XmlElement(namespace="http://alain-cieslik.com/xmlstreaming")	
	private String type;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}


