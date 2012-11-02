package com.alaincieslik.springbatch.article.xmlsignature.security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Collections;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

@SuppressWarnings("restriction")
public class SignXml extends BaseComponent{
	private String inputPath="mySample.xml";
	private String outputPath="mySample-signature.xml";

	public static void main(String[] args) throws Exception {
		SignXml genEnveloped=new SignXml();
		genEnveloped.createSignedXml();
	}
	
	public void createSignedXml() throws Exception {
		XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
		KeyPair kp = getAlainKeyPair();
		KeyInfo ki = createKeyInfo(fac, kp);
		Document doc = createDocument();
		sign(doc.getDocumentElement(), kp.getPrivate(), fac, ki);
		createOutputResult(doc, calculeOutputPath()+outputPath);
	}

	private Document createDocument() throws FileNotFoundException, SAXException, IOException, ParserConfigurationException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		Document doc = dbf.newDocumentBuilder().parse(new FileInputStream(calculeInputPath()+inputPath));
		return doc;
	}
	
	private KeyInfo createKeyInfo(XMLSignatureFactory fac, KeyPair kp) throws KeyException{
		KeyInfoFactory kif = fac.getKeyInfoFactory();
		KeyValue kv = kif.newKeyValue(kp.getPublic());
		KeyInfo ki = kif.newKeyInfo(Collections.singletonList(kv));
		return ki;
	}
	
	private void sign(Element element, PrivateKey key, XMLSignatureFactory fac, KeyInfo ki) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, MarshalException, XMLSignatureException{
		Reference ref = fac.newReference("", fac.newDigestMethod(
				DigestMethod.SHA1, null), Collections.singletonList(fac
				.newTransform(Transform.ENVELOPED,
						(TransformParameterSpec) null)), null, null);
		SignedInfo si = fac
				.newSignedInfo(fac.newCanonicalizationMethod(
						CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS,
						(C14NMethodParameterSpec) null), fac
						.newSignatureMethod(SignatureMethod.DSA_SHA1, null),
						Collections.singletonList(ref));
		DOMSignContext dsc = new DOMSignContext(key, element);
		XMLSignature signature = fac.newXMLSignature(si, ki);
		signature.sign(dsc);
	}
	
	private void createOutputResult(Document doc, String outputPath) throws FileNotFoundException, TransformerException{
		OutputStream os = new FileOutputStream(outputPath);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();
		trans.transform(new DOMSource(doc), new StreamResult(os));
	}
}
