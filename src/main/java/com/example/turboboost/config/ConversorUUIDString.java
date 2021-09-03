package com.example.turboboost.config;

import java.util.UUID;

import javax.persistence.AttributeConverter;

public class ConversorUUIDString implements AttributeConverter<UUID, String>{

	@Override
	public String convertToDatabaseColumn(UUID hash) {
		return hash.toString();
	}

	@Override
	public UUID convertToEntityAttribute(String hash) {
		return UUID.fromString(hash);
	}

}
