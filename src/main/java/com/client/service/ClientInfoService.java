package com.client.service;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.client.clientinfo.util.IDNumberData;
import com.client.clientinfo.util.IDNumberParser;
import com.client.data.ClientInfo;

@Service
public class ClientInfoService {
	
	private List<ClientInfo> clientInfoList = new ArrayList<>(Arrays.asList(
			
			new ClientInfo("0123474827482","abc","3334445555","joy","lane1"),
			new ClientInfo("2222","roy","3334445556","john","lane2"),
			new ClientInfo("3333","abc","3334445557","william","lane3")));
	

	
	public List<ClientInfo> getClientInfo(){
		return clientInfoList;
	}
	
	public ClientInfo addClient(ClientInfo clientInfo) {
		boolean isValid = false;
		if(Optional.ofNullable(clientInfo.getIdNumber()).isPresent() && Optional.ofNullable(clientInfo.getPhoneNumber()).isPresent())
			 isValid = validateData(clientInfo.getIdNumber(),clientInfo.getPhoneNumber());
		if(isValid && !clientInfoList.contains(clientInfo)) {
			clientInfoList.add(clientInfo);
			return clientInfo;
		}
		return null;
	}
	
	public ClientInfo updateClientDetails(String idNumber, ClientInfo clientInfo) {
		boolean isValid = false;
		if(Optional.ofNullable(clientInfo.getIdNumber()).isPresent() && Optional.ofNullable(clientInfo.getPhoneNumber()).isPresent())
			 isValid = validateData(clientInfo.getIdNumber(),clientInfo.getPhoneNumber());
		if(isValid) {
			List<ClientInfo> updatedList = clientInfoList.stream().filter(e -> idNumber.equals(e.getIdNumber())).toList();
			ClientInfo clientInfoUpdated = updatedList.get(0);
			clientInfoList.remove(updatedList.get(0));
			if(Optional.ofNullable(clientInfo.getFirstName()).isPresent())
				clientInfoUpdated.setFirstName(clientInfo.getFirstName());
			if(Optional.ofNullable(clientInfo.getAddress()).isPresent())
				clientInfoUpdated.setAddress(clientInfo.getAddress());
			if(Optional.ofNullable(clientInfo.getIdNumber()).isPresent())
				clientInfoUpdated.setIdNumber(clientInfo.getIdNumber());
			if(Optional.ofNullable(clientInfo.getPhoneNumber()).isPresent())
				clientInfoUpdated.setPhoneNumber(clientInfo.getPhoneNumber());
			if(Optional.ofNullable(clientInfo.getLastName()).isPresent())
				clientInfoUpdated.setLastName(clientInfo.getLastName());
			
			clientInfoList.add(clientInfoUpdated);
			return clientInfoUpdated;
		}
		return null;
	}
	
	private boolean validateData(String idNumber, String phoneNumber) {
		IDNumberParser idNumberParser = new IDNumberParser();
		IDNumberData idNumberData =null;;
		try {
			idNumberData = idNumberParser.parse(idNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("isValid: " + idNumberData.isValid());
		System.out.println("Date Of Birth: " + idNumberData.getBirthdate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
		System.out.println("Age: " + idNumberData.getAge());
		System.out.println("Gender: " + idNumberData.getGender());
		System.out.println("Citizenship: " + idNumberData.getCitizenShip());
		if(Optional.ofNullable(idNumberData).isPresent())
			return idNumberData.isValid();
		return Boolean.FALSE;
	}

}

