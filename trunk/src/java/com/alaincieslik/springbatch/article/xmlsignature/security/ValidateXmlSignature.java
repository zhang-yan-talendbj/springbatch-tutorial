package com.alaincieslik.springbatch.article.xmlsignature.security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.PublicKey;
import java.util.Iterator;

import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This is a simple example of validating an XML Signature using the JSR 105
 * API. It assumes the key needed to validate the signature is contained in a
 * KeyValue KeyInfo.
 */
@SuppressWarnings("restriction")
public class ValidateXmlSignature extends BaseComponent{

	String fileToValidate = "mySample-signature.xml";

	public ValidateXmlSignature(){
	}
	
	public static void main(String[] args) throws Exception {
		ValidateXmlSignature validateXmlSignature = new ValidateXmlSignature();
		validateXmlSignature.validateXmlSignature();
	}

	private Document loadDocument() throws FileNotFoundException, SAXException,
			IOException, ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		Document doc = dbf.newDocumentBuilder().parse(
				new FileInputStream(calculeOutputPath()+fileToValidate));
		return doc;
	}

	private Node getSignatureElement(Document document) throws Exception {
		NodeList nl = document.getElementsByTagNameNS(XMLSignature.XMLNS,
				"Signature");
		if (nl.getLength() == 0) {
			throw new Exception("Cannot find Signature element");
		}
		return nl.item(0);
	}

	public void validateXmlSignature() throws Exception {
		PublicKey publicKey = null;
		DOMValidateContext valContext = null;
		// Instantiate the document to be validated
		Document document = loadDocument();
		// Find Signature element
		Node signatureNode = getSignatureElement(document);
		String path=calculeOutputPath();
		// Create a DOMValidateContext and specify a KeyValue KeySelector
		// and document context
		publicKey = getAlainKeyPair().getPublic();
		valContext = new DOMValidateContext(publicKey, signatureNode);

		// unmarshal the XMLSignature
		XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
		XMLSignature signature = fac.unmarshalXMLSignature(valContext);

		// Validate the XMLSignature (generated above)
		boolean coreValidity = signature.validate(valContext);

		// Check core validation status
		if (coreValidity == false) {
			logger.info("Signature failed core validation");
			boolean sv = signature.getSignatureValue().validate(valContext);
			logger.info("signature validation status: " + sv);
			// check the validation status of each Reference
			Iterator i = signature.getSignedInfo().getReferences().iterator();
			for (int j = 0; i.hasNext(); j++) {
				boolean refValid = ((Reference) i.next()).validate(valContext);
				logger.info("ref[" + j + "] validity status: "
						+ refValid);
			}
		} else {
			logger.info("Signature passed core validation");
		}
	}	
}
