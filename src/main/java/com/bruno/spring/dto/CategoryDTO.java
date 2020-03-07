package com.bruno.spring.dto;

import java.io.Serializable;

import com.bruno.spring.domain.Category;

public class CategoryDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
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