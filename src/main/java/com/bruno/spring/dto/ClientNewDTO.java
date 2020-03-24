package com.bruno.spring.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.bruno.spring.services.validation.ClientInsert;

@ClientInsert
public class ClientNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Required field!")
	@Length(min = 3, max = 150, message = "Name field must have between 3 & 150 characters")
	private String name;
	
	@NotEmpty(message = "Required field!")
	@Email(message = "Invalid email")
	private String email;
	
	@NotEmpty(message = "Required field!")
	private String cpfOrCnpj;
	
	private Integer clientType;

	@NotEmpty(message = "Required field!")
	private String logradouro;
	
	@NotEmpty(message = "Required field!")
	private String number;
	
	private String complement;
	private String neighborhood;
	
	@NotEmpty(message = "Required field!")
	private String postalCode;

	@NotEmpty(message = "Required field!")
	private String phone1;
	private String phone2;
	private String phone3;

	private Long cityId;

	public ClientNewDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

}