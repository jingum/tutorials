package com.baeldung.maptojson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MapToJsonUnitTest {
    final TypeAdapter<JsonElement> strictAdapter = new Gson().getAdapter(JsonElement.class);

    public boolean isValid(String json) {
        try {
            strictAdapter.fromJson(json);
        } catch (JsonSyntaxException | IOException e) {
            return false;
        }
        return true;
    }

    @Test
    public void given_HashMapData_whenUsingJackson_thenConvertToJson() throws JsonProcessingException {
        Map<String, String> data = new HashMap();
        data.put("CS", "Post1");
        data.put("Linux", "Post1");
        data.put("Kotlin", "Post1");
        ObjectMapper objectMapper = new ObjectMapper();
        String jacksonData = objectMapper.writeValueAsString(data);
        Assertions.assertTrue(isValid(jacksonData));
    }

    @Test
    public void given_HashMapData_whenUsingGson_thenConvertToJson() {
        Map<String, String> data = new HashMap<>();
        data.put("CS", "Post1");
        data.put("Linux", "Post1");
        data.put("Kotlin", "Post1");
        Gson gson = new Gson();
        Type typeObject = new TypeToken<HashMap>() {
        }.getType();
        String gsonData = gson.toJson(data, typeObject);
        Assertions.assertTrue(isValid(gsonData));
    }

    @Test
    public void given_HashMapData_whenOrgJson_thenConvertToJsonUsing() {
        Map<String, String> data = new HashMap<>();
        data.put("CS", "Post1");
        data.put("Linux", "Post1");
        data.put("Kotlin", "Post1");
        JSONObject jsonObject = new JSONObject(data);
        String orgJsonData = jsonObject.toString();
        Assertions.assertTrue(isValid(orgJsonData));
    }
}