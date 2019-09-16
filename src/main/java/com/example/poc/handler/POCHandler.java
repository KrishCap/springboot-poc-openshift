package com.example.poc.handler;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.poc.api.RetreiveAPIRequest;
import com.example.poc.api.RetreiveAPIResponse;
import com.example.poc.client.ApiClient;
import com.example.poc.exception.SpringbootPOCException;
import com.example.poc.model.client.SampleClientResponse;
import com.example.poc.model.client.Todos;

@Component
public class POCHandler {


	
	@Autowired
	private ApiClient apiClient;
	
	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	
	@Autowired
	POCAsyncHandler pocAsyncHandler;
	

	 private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	

	public RetreiveAPIResponse generateAPIResponse(@Valid RetreiveAPIRequest retreiveAPIRequest) throws SpringbootPOCException, InterruptedException, ExecutionException {
		RetreiveAPIResponse retreiveAPIResponse = buildClientResponse();
		//RetreiveAPIResponse retreiveAPIResponse = convertSampleResponseToRetreiveAPIResponse(apiClient.retreiveResponse());
		return retreiveAPIResponse;
	}

	
	private RetreiveAPIResponse buildClientResponse() throws SpringbootPOCException, InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		CompletableFuture<List<com.example.poc.model.client.User>> users = pocAsyncHandler.retrieveUsers();
		CompletableFuture<List<Todos>> todos = pocAsyncHandler.retrieveUsersTodos();
		
		//wait for all async method to complete
		CompletableFuture.allOf(users, todos).join();
		SampleClientResponse sampleClientResponse = new SampleClientResponse();
		sampleClientResponse.setUsers(users.get());
		RetreiveAPIResponse retreiveAPIResponse = convertSampleResponseToRetreiveAPIResponse(sampleClientResponse);
		
		retreiveAPIResponse.getUsers().forEach(user->{
			try {
				todos.get().forEach(todo->{
					if(user.getId()==todo.getId()) {
						user.setTitle(todo.getTitle());
					}
				});
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}});
		return retreiveAPIResponse;
	}


	private RetreiveAPIResponse convertSampleResponseToRetreiveAPIResponse(SampleClientResponse sampleClientResponse) {
		return dozerBeanMapper.map(sampleClientResponse, RetreiveAPIResponse.class);
	}


/*	public void publishKafkaMessage(User request) {
		User user = new  User();
		user.setName("Krishnendu");
		user.setEmail("krish12187@gmail.com");
		user.setPhone("4696886024");
		
		kafkaTemplate.send("krishnendu-topic" , user) ;
		// TODO Auto-generated method stub

	}


	public void sendQueueMessage(@Valid Company request) {
		
		Company request1 = new Company();
		request1.setName("TCS");
		request1.setCatchPhrase("Experience Certainty");
		
		jmsAmqProducer.send(request1);
		
	}
	*/

}
