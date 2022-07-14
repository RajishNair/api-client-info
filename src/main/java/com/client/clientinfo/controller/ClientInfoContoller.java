package com.client.clientinfo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.client.clientinfo.exception.ResourceNotFoundException;
import com.client.data.ClientInfo;
import com.client.service.ClientInfoService;

@ComponentScan("com.client.*")
@RestController
@RequestMapping("/api/v1")
public class ClientInfoContoller {
	
	@Autowired
	private ClientInfoService clientService;

		@GetMapping("/client/info")
		public ResponseEntity<?> getClientDetails(@RequestParam(value = "firstName",required = false) String firstName
				,@RequestParam(value = "idNumber",required = false) String idNumber,
				@RequestParam(value = "phoneNumber", required = false) String phoneNmber ) {
			List<ClientInfo> clientList = clientService.getClientInfo();
			if(Optional.ofNullable(firstName).isPresent())
				clientList = clientList.stream().filter(e -> e.getFirstName().equalsIgnoreCase(firstName)).toList();
			else if(Optional.ofNullable(idNumber).isPresent())
				clientList = clientList.stream().filter(e -> e.getIdNumber().equals(idNumber)).toList();
			else if(Optional.ofNullable(phoneNmber).isPresent())
				clientList = clientList.stream().filter(e -> e.getPhoneNumber().equals(phoneNmber)).toList();
			return ResponseEntity.ok().body(clientList);
		}

		
		@PostMapping("/client/addClientInfo")
		public ResponseEntity<ClientInfo> createEmployee(@Validated @RequestBody ClientInfo clientInfo) throws ResourceNotFoundException{
			ClientInfo clientInfoNew = clientService.addClient(clientInfo);
			if(null!= clientInfoNew)
				return ResponseEntity.ok(clientService.addClient(clientInfo));
			throw new ResourceNotFoundException(
					"Client already exists for idNumber or phoneNumber: "+clientInfo.getIdNumber()+" - "+clientInfo.getPhoneNumber());
		}
		
		@PutMapping("/client/updateClientInfo/{idNumber}")
		public ResponseEntity<ClientInfo> updateEmployee(@PathVariable(value = "idNumber") String idNumber,
				@Validated @RequestBody ClientInfo clientInfo) throws ResourceNotFoundException {
			ClientInfo clientInfoNew = clientService.updateClientDetails(idNumber,clientInfo);
			if(null!= clientInfoNew)
				return ResponseEntity.ok(clientInfoNew);
			throw new ResourceNotFoundException(
					"Client already exists for idNumber or phoneNumber: "+clientInfo.getIdNumber()+" - "+clientInfo.getPhoneNumber());
			
		}
}
