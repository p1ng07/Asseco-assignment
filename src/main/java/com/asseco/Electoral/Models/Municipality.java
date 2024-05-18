package com.asseco.Electoral.Models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;

public enum Municipality {
    FUNCHAL, CASCAIS, PENAFIEL
}

class CaseInsensitiveMunicipality extends JsonDeserializer<Municipality> {
    @Override
    public Municipality deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String value = jsonParser.getText().toUpperCase(); // Convert to uppercase for case-insensitivity
        return Municipality.valueOf(value); // Convert string to enum
    }
}