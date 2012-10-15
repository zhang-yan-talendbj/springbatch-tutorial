package com.alaincieslik.springbatch.article.xmlsignature.batch;

import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

@Service("xmlsignature.validationTasket")
public class ValidationTasklet implements Tasklet, ApplicationContextAware{

	private Resource xmlFile;
	private Resource xsdFile;
	
	public void setApplicationContext(ApplicationContext context) throws BeansException {
	    xmlFile=context.getResource("classpath:xml/items.xml");
	    xsdFile=context.getResource("classpath:xml/items.xsd");
	}
	
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance(); 
		dbf.setNamespaceAware(true);  
		
	    DocumentBuilder parser = dbf.newDocumentBuilder();
	    Document document = parser.parse(xmlFile.getInputStream());

	    // create a SchemaFactory capable of understanding WXS schemas
	    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

	    // load a WXS schema, represented by a Schema instance
	    Source schemaFile = new StreamSource(xsdFile.getInputStream());
	    Schema schema = factory.newSchema(schemaFile);

	    // create a Validator instance, which can be used to validate an instance document
	    Validator validator = schema.newValidator();
	    validator.setErrorHandler(new MyErrorHandler());
	    // validate the DOM tree
	    try {
	        validator.validate(new DOMSource(document));
	    } catch (SAXException e) {
	    	throw new RuntimeException(e);
	    }
    	MyErrorHandler myErrorHandler= (MyErrorHandler)validator.getErrorHandler();
    	if(myErrorHandler.checkErrors()){
    		throw new RuntimeException("xml is not valid ! ");
    	}

 		return RepeatStatus.FINISHED;
	}
	
	public class MyErrorHandler implements ErrorHandler{

		List<SAXParseException> warnings=new ArrayList<SAXParseException>();
		List<SAXParseException> errors=new ArrayList<SAXParseException>();
		List<SAXParseException> fatalErrors=new ArrayList<SAXParseException>();
		public void warning(SAXParseException exception) throws SAXException {
			warnings.add(exception);
		}

		public void error(SAXParseException exception) throws SAXException {
			errors.add(exception);
		}

		public void fatalError(SAXParseException exception) throws SAXException {
			fatalErrors.add(exception);
		}
    	public boolean checkErrors(){
    		if(errors.size()>0||fatalErrors.size()>0){
    			return true;
    		}else{
    			return false;
    		}
    	}
		public String toString(){
			return 	"Warnings="+warnings.size()+
					"\n"+"Errors="+errors.size()+
					"\n"+"Fatal Errors="+fatalErrors.size()+"\n";
		}
    }
}
