package com.client.data;

import javax.validation.constraints.Size;

import java.util.Objects;

import javax.validation.constraints.NotNull;


public class ClientInfo {
	
	@NotNull
	@Size(min = 13, message = "ID Number should have atleast 13 digits")
	private String idNumber;
	@NotNull
	private String firstName;
	@NotNull
	@Size(min = 10, message = "Phone Number should have atleast 10 digits")
	private String phoneNumber;
	private String lastName;
	private String address;
	
	

	public ClientInfo(String idNumber, String firstName, String phoneNumber, String lastName, String address) {
		super();
		this.idNumber = idNumber;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.lastName = lastName;
		this.address = address;
	}

	public ClientInfo() {
		super();
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, idNumber, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientInfo other = (ClientInfo) obj;
		return  Objects.equals(idNumber, other.idNumber)
				|| Objects.equals(phoneNumber, other.phoneNumber);
	}
	
	

}
