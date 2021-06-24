package com.client.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.client.clientinfo.exception.ResourceNotFoundException;
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
			clientInfoList.remove(updatedList);
			clientInfoList.add(clientInfo);
			return clientInfo;
		}
		return null;
	}
	
	private boolean validateData(String idNumber, String phoneNumber) {
		boolean isValid = true;
		String regExpression = "([0-9][0-9])((?:[0][1-9])|(?:[1][0-2]))((?:[0-2][0-9])|(?:[3][0-1]))([0-9])([0-9]{3})([0-9])([0-9])([0-9])"; 
        Pattern pattern = Pattern.compile(regExpression);

        Matcher matcher = pattern.matcher(idNumber);

        if (matcher.matches()) {

            int tot1 = 0;
            for (int i = 0; i < idNumber.length() - 1; i += 2) {
                tot1 = tot1 + Integer.parseInt(idNumber.substring(i, i + 1));
            }

            StringBuffer field1 = new StringBuffer("");
            for (int i = 1; i < idNumber.length(); i += 2) {
                field1.append(idNumber.substring(i, i + 1));
            }

            String evenTotStr = (Long.parseLong(field1.toString()) * 2) + "";
            int tot2 = 0;
            for (int i = 0; i < evenTotStr.length(); i++) {
                tot2 = tot2 + Integer.parseInt(evenTotStr.substring(i, i + 1));
            }

            int lastD = (10 - ((tot1 + tot2) % 10)) % 10;
            int checkD = Integer.parseInt(idNumber.substring(12, 13));

            if (checkD == lastD) {
            	isValid = true;
                return isValid;
            } else {
                return isValid;
            }
        } else {
            return isValid;
        }
	}

}

