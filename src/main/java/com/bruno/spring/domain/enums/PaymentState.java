package com.bruno.spring.domain.enums;

public enum PaymentState {
	
	PENDING(1, "Pending"), SETTLED(2, "Settled"), CANCELED(3, "Canceled");

	private int cod;
	private String description;
	
	private PaymentState(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}
	
	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

	public static PaymentState toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (PaymentState p : PaymentState.values()) {
			if (cod.equals(p.getCod())) {
				return p;
			}
		}
		throw new IllegalArgumentException("Invalid id: " + cod);
	}
}
