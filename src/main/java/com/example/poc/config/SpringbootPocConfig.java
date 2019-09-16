package com.example.poc.config;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import com.example.poc.trace.SpringbootPocRestClientLogger;







@Configuration
@EnableAsync
public class SpringbootPocConfig {
	
	@Autowired
	private Environment environment;
	
/*	@Value(value = "${http.client.ssl.key-store}")
	private Resource serverSSLKeyStore;*/
	
	@Value(value = "classpath:application-${spring.profiles.active:default}.properties")
	private Resource propertySource;
	
/*	@Value("${jsa.activemq.broker.url}")
	String brokerUrl;
	
	@Value("${jsa.activemq.borker.username}")
	String userName;
	
	@Value("${jsa.activemq.borker.password}")
	String password;*/
	
	@Bean(name = "asyncExecutor")
    public Executor asyncExecutor()
    {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("AsynchThread-");
        executor.initialize();
        return executor;
    }
	
	@Bean
	public RestTemplate restTemplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException {
		
		/*RestTemplate restTemplate = new RestTemplate();
		  final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
	      interceptors.add(new SpringbootPocRestClientLogger());
	      restTemplate.setInterceptors(interceptors);*/
	      
		//Adding logger interceptor within rest template
	      ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());	      
	      RestTemplate restTemplate = new RestTemplate(factory);
	      final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
	      interceptors.add(new SpringbootPocRestClientLogger());
	      restTemplate.setInterceptors(interceptors);
	
	      /*final List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	      messageConverters.add(new StringHttpMessageConverter());
	      messageConverters.add(new ByteArrayHttpMessageConverter());
	      restTemplate.setMessageConverters(messageConverters);*/
	
	      return restTemplate;
	}
	
	//@Bean
	/*public RestTemplate restTemplate2() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException {
		setTrustStore();
		final RestTemplate restTemplate = new RestTemplate(requestFactory());
	
	      final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
	      interceptors.add(new SpringbootPocRestClientLogger());
	      restTemplate.setInterceptors(interceptors);
	
	      final List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	      messageConverters.add(new StringHttpMessageConverter());
	      messageConverters.add(new ByteArrayHttpMessageConverter());
	      restTemplate.setMessageConverters(messageConverters);
	
	      return restTemplate;
	}*/
	
	//@Bean
   /* public ClientHttpRequestFactory requestFactory()
                  throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {

           final ConnectionKeepAliveStrategy keepAliveStrategy = new ConnectionKeepAliveStrategy() {
                  @Override
                  public long getKeepAliveDuration(final HttpResponse response, final HttpContext context) {
                        return 1;
                  }
           };

           final javax.net.ssl.SSLSocketFactory socketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();

           final SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(socketFactory,
                        new NoopHostnameVerifier());

           final Registry<ConnectionSocketFactory> r = RegistryBuilder.<ConnectionSocketFactory> create()
                        .register("https", csf).build();

           final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(r);

           connectionManager.setMaxTotal(100);
           connectionManager.setDefaultMaxPerRoute(100);

           final CloseableHttpClient httpClient = HttpClients.custom().setKeepAliveStrategy(keepAliveStrategy)
                        .setConnectionManager(connectionManager).build();

           final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

           requestFactory.setHttpClient(httpClient);

           requestFactory.setReadTimeout(NumberUtils.toInt("15000"));
           requestFactory.setConnectTimeout(NumberUtils.toInt("10000"));

           return new BufferingClientHttpRequestFactory(requestFactory);
    }

	
	private void setTrustStore() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException, KeyManagementException{
		KeyStore ks = KeyStore.getInstance("JKS");
        InputStream is = new FileInputStream(new File(serverSSLKeyStore.getFile().getAbsolutePath()));
        
        String storePassword = SpringbootPOCUtil.getPassword(propertySource.getFile(), "http.client.ssl.key-store-password", environment.getProperty("KEYSTORE_ENCRYPT_KEY"));
        
        char[] ksp = storePassword.toCharArray();
        ks.load(is, ksp);
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        char[] kp = storePassword.toCharArray();
        
        kmf.init(ks, kp);
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        TrustManagerFactory tmf = TrustManagerFactory
             .getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);
        TrustManager[] tm = tmf.getTrustManagers();
     
        sslContext.init(kmf.getKeyManagers(), tm, null);

        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);

	}*/
	
	@Bean(name = "org.dozer.Mapper")
	public DozerBeanMapper dozerBean() {
		List<String> mappingFiles = Arrays.asList(
				/*"dozer-mapping/onbcasestatus_crmcasestatus-mapping.xml",
                "dozer-mapping/onbtat_crmtat-mapping.xml",
                "dozer-mapping/onbcomment_crmcomment-mapping.xml",
                "dozer-mapping/onbtask_crmtask-mapping.xml",*/
                "dozer-mapping/sample_api-response_mapping.xml");

		DozerBeanMapper dozerBean = new DozerBeanMapper();
		dozerBean.setMappingFiles(mappingFiles);
		
		/*Map<String,CustomConverter> converters = new HashMap<String,CustomConverter>();
        converters.put("epochToDateString", new EpochToDateStringCustomConverter());
        converters.put("dateStringToEpoch", new DateStringToEpochConverter());
        dozerBean.setCustomConvertersWithId(converters);*/
		
		return dozerBean;
	}
	
	
	// @Bean
	    /*public ProducerFactory<String, User> producerFactory() {
	        Map<String, Object> config = new HashMap<>();

	        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
	        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
	        config.put(ProducerConfig.GROUP_ID_CONFIG, groupId);
	        config.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
	        config.put(ProducerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
	        config.put(ProducerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
	        config.put(ProducerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeout);
	        config.put(ProducerConfig.MAX_POLL_RECORDS_CONFIG, maxRecords);
	        config.put(ProducerConfig.AUTO_OFFSET_RESET_CONFIG, offSet);
	        if (sslEnabled) {
	        	config.put("security.protocol", "SSL");
	        	config.put("ssl.truststore.location", trustStoreLocation);
	        	config.put("ssl.truststore.password", trustStorePassword);

	        	config.put("ssl.key.password", keyStorePassword);
	        	config.put("ssl.keystore.password", keyStorePassword);
	        	config.put("ssl.keystore.location", keyStoreLocation);
	        }
	        
	        return new DefaultKafkaProducerFactory<>(config);
	    }*/


	    /*@Bean
	    public KafkaTemplate<String, User> kafkaTemplate() {
	        return new KafkaTemplate<>(producerFactory());
	    }
*/
	//Active MQ configuration
	   // @Bean
	  /*  public ConnectionFactory connectionFactory(){
	        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
	        connectionFactory.setBrokerURL(brokerUrl);
	        connectionFactory.setUserName(userName);
	        connectionFactory.setPassword(password);
	        return connectionFactory;
	    }*/
	    
	   /* @Bean // Serialize message content to json using TextMessage
		public MessageConverter jacksonJmsMessageConverter() {
		    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		    converter.setTargetType(MessageType.TEXT);
		    converter.setTypeIdPropertyName("_type");
		    return converter;
		}*/
	    
	    /*
	     * Used for Receiving Message
	     */
	  /*  @Bean
	    public JmsListenerContainerFactory<?> jsaFactory(ConnectionFactory connectionFactory,
	                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
	        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	        factory.setMessageConverter(jacksonJmsMessageConverter());
	        configurer.configure(factory, connectionFactory);
	        return factory;
	    }*/
	    
	    /*
	     * Used for Sending Messages.
	     */
	  /*  @Bean
	    public JmsTemplate jmsTemplate(){
	        JmsTemplate template = new JmsTemplate();
	        template.setMessageConverter(jacksonJmsMessageConverter());
	        template.setConnectionFactory(connectionFactory());
	        return template;
	    }*/
}
