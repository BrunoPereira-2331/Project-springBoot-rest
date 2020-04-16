package com.bruno.spring.domain.enums;

public enum Profile {
	
	ADMIN(1, "ROLE_ADMIN"), CLIENT (2, "ROLE_CLIENT");

	private int cod;
	private String description;
	
	private Profile(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}
	
	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

	public static Profile toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (Profile p : Profile.values()) {
			if (cod.equals(p.getCod())) {
				return p;
			}
		}
		throw new IllegalArgumentException("Invalid id: " + cod);
	}
}
