package com.bruno.spring.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.bruno.spring.domain.Category;

public class CategoryDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message = "Field name must be filled")
	@Length(min = 5, max = 80, message = "Name field must have between 5 & 80 characters")
	private String name;
	
	public CategoryDTO() {
	}
	
	public CategoryDTO(Category obj) {
		this.id = obj.getId();
		this.name = obj.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}