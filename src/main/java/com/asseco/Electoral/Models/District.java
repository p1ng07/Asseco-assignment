package com.asseco.Electoral.Models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public enum District {
    MADEIRA, LISBON, PORTO
}

class CaseInsensitiveDistrict extends JsonDeserializer<Municipality> {
    @Override
    public Municipality deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String value = jsonParser.getText().toUpperCase(); // Convert to uppercase for case-insensitivity
        return Municipality.valueOf(value); // Convert string to enum
    }
}
