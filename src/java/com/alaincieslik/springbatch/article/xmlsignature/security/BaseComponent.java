package com.alaincieslik.springbatch.article.xmlsignature.security;

import java.io.InputStream;
import java.net.URL;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.logging.Logger;

public abstract class BaseComponent {
    protected static Logger logger = Logger.getLogger("batch");   
    /*
	protected String keyStoreName="demokeystore";
	protected String sPass = "spdemo";
	protected String kPass = "kpdemo";
	protected String alias = "demo1";
	*/
	protected String company1="company1_keystore";
	protected String company2="company2_keystore";
	protected String storepass="storepass";
	protected String keypass="keypass";
	protected String alias_alain="alain_key";
	protected String alias_alice="alice_key";
	protected String alias_bob="bob_key";
		
	public KeyPair getAlainKeyPair() throws Exception{
		return getKeyPair(company1, storepass, keypass, alias_alain);
	}
	
	public KeyPair getAliceKeyPair() throws Exception{
		return getKeyPair(company1, storepass, keypass, alias_alice);
	}

	public KeyPair getBobKeyPair() throws Exception{
		return getKeyPair(company2, storepass, keypass, alias_bob);
	}
	
	private KeyPair getKeyPair(String store, String sPass, String kPass,
			String alias) throws Exception {
		KeyStore ks = loadKeyStore(store, sPass);
		KeyPair keyPair = null;
		if (ks.containsAlias(alias)) {
			Key key = ks.getKey(alias, kPass.toCharArray());
			if (key instanceof PrivateKey) {
				Certificate cert = ks.getCertificate(alias);
				PublicKey publicKey = cert.getPublicKey();
				PrivateKey privateKey = (PrivateKey) key;
				keyPair = new KeyPair(publicKey, privateKey);
			}
		}
		return keyPair;
	}

	private KeyStore loadKeyStore(String store, String sPass) throws Exception {
		KeyStore myKS = KeyStore.getInstance("JKS");
		InputStream fileKeyStore = ClassLoader.getSystemClassLoader()
				.getResourceAsStream(store);
		myKS.load(fileKeyStore, sPass.toCharArray());
		fileKeyStore.close();
		return myKS;
	}

	private String calculePath(){
		URL url = ClassLoader.getSystemClassLoader().getResource(company1);
		String path=url.getPath();
		path=path.substring(0, path.indexOf(company1));
		return path;
	}
	
	protected String calculeOutputPath(){
		return calculePath()+"xml/output/";
	}

	protected String calculeInputPath(){
		return calculePath()+"xml/input/";
	}
}
