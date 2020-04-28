package com.bruno.spring.dto;

import java.io.Serializable;

import com.bruno.spring.domain.State;

public class StateDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;

	public StateDTO() {
		super();
	}

	public StateDTO(State state) {
		super();
		this.id = state.getId();
		this.name = state.getName();
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
