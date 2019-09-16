package com.example.poc.web;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.poc.aop.TrackTime;
import com.example.poc.api.GenerateDocumentRequest;
import com.example.poc.api.GenerateDocumentResponse;
import com.example.poc.api.RetreiveAPIRequest;
import com.example.poc.api.RetreiveAPIResponse;
import com.example.poc.api.User;
import com.example.poc.exception.SpringbootPOCException;
import com.example.poc.handler.POCHandler;
import com.example.poc.model.client.Company;

@RestController
@RequestMapping("v1/logical-tech")
public class SpringbootPOCController {
	
	@Autowired
	private POCHandler pOCHandler;


/*	@TrackTime
	@RequestMapping(value = {"activity/retreive"},method = {org.springframework.web.bind.annotation.RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ApiMethod(description = "Generate PDF")
	public ResponseEntity<?> retreiveAPIResponse(@ApiBodyObject @Valid @RequestBody(required = true) RetreiveAPIRequest retreiveAPIRequest) throws SpringbootPOCException, InterruptedException, ExecutionException {
		RetreiveAPIResponse retreiveAPIResponse = pOCHandler.generateAPIResponse(retreiveAPIRequest);
		return new ResponseEntity<RetreiveAPIResponse>(retreiveAPIResponse,HttpStatus.OK);		
	}*/
	


	
	@TrackTime
	@RequestMapping(value = {"gcp/test"},method = {org.springframework.web.bind.annotation.RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiMethod(description = "Generate PDF")
	public ResponseEntity<?> retreiveAPIResponse() throws SpringbootPOCException, InterruptedException, ExecutionException {
		//RetreiveAPIResponse retreiveAPIResponse = pOCHandler.generateAPIResponse(retreiveAPIRequest);
		User user = new User();
		user.setId(2);
		user.setName("Krishnendu Ghosh");
		user.setPhone("4696886024");
		return new ResponseEntity<User>(user,HttpStatus.OK);		
	}
	
	
	@TrackTime
	@RequestMapping(value = {"opshift/test"},method = {org.springframework.web.bind.annotation.RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiMethod(description = "Generate PDF")
	public ResponseEntity<?> retreiveOPSAPIResponse() throws SpringbootPOCException, InterruptedException, ExecutionException {
		//RetreiveAPIResponse retreiveAPIResponse = pOCHandler.generateAPIResponse(retreiveAPIRequest);
		User user = new User();
		user.setId(2);
		user.setName("Krishnendu Ghosh");
		user.setPhone("4696886024");
		return new ResponseEntity<User>(user,HttpStatus.OK);		
	}
}
