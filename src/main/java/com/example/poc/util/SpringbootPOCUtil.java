package com.example.poc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Properties;

import org.jasypt.properties.EncryptableProperties;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.util.StreamUtils;



public class SpringbootPOCUtil {

	public static String getCSVHeader(String[] data) {
		
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i< data.length; i++) {
			buffer.append(data[i]);
			if (i+1 < data.length )
				buffer.append(",");
			else
				buffer.append("\r\n");
		}
		return buffer.toString();
	}

	public static String getCSVFormat(String value) {
		// TODO Auto-generated method stub
		if(value.trim().length()== 0)
			return "\"\"";	
		
		String token = value;
		boolean quote = false;
		//double the internal quotes
		int j = token.indexOf('\"');
		while (j>=0) {
			quote = true;
			token = token.substring(0, j+1)+"\""+token.substring(j+1);
			j+=2;
			j=token.indexOf('\"',j);
		}
		// fields containing " or , must be surrounded by quotes
		if(token.indexOf(',') >= 0 || quote)
				token = "\"" + token + "\"";
		
		return token;

	}
	
	public static String ConvertStreamToString(InputStream inputStream) throws IOException {

		return StreamUtils.copyToString(inputStream, Charset.defaultCharset());

	}

	
	/**
	 * Decrypts any encrypted text based on the key given
	 * @param propertiesFile
	 * @param property
	 * @param key
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getPassword(File propertiesFile, String property, String key) throws FileNotFoundException, IOException{
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(key);                    
		
		Properties encProps = new EncryptableProperties(encryptor);  
		encProps.load(new FileInputStream(propertiesFile));
		return encProps.getProperty(property);
	}
}
