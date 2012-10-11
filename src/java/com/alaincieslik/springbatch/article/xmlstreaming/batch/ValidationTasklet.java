package com.alaincieslik.springbatch.article.xmlstreaming.batch;

import java.io.File;
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
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

@Service("validationTasket")
public class ValidationTasklet implements Tasklet{

	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		
	    DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    Document document = parser.parse(new File("C:/Project/article-on-web/com.alaincieslik.springbatch/src/resources/xml/items.xml"));

	    // create a SchemaFactory capable of understanding WXS schemas
	    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

	    // load a WXS schema, represented by a Schema instance
	    Source schemaFile = new StreamSource(new File("C:/Project/article-on-web/com.alaincieslik.springbatch/src/resources/xml/items.xsd"));
	    Schema schema = factory.newSchema(schemaFile);

	    // create a Validator instance, which can be used to validate an instance document
	    Validator validator = schema.newValidator();
	    validator.setErrorHandler(new MyErrorHandler());
	    // validate the DOM tree
	    try {
	        validator.validate(new DOMSource(document));
	    } catch (SAXException e) {
	    	e.printStackTrace();
	    }
    	MyErrorHandler myErrorHandler= (MyErrorHandler)validator.getErrorHandler();
    	System.out.println(myErrorHandler.toString());

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
    	
		public String toString(){
			return "Warnings="+warnings.size()+"\n"+"Errors="+errors.size()+"\n"+"Fatal Errors="+fatalErrors.size()+"\n";
		}
    }
}
