package com.example.pojo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Deserializer for a dummy object
 *
 * Convert a JsonObject into a Dummy object.
 */
public class UsersRegistrationStatustDeserializer implements JsonDeserializer<UsersRegistrationStatus>
{
    @Override
    public UsersRegistrationStatus deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException
    {
        final UsersRegistrationStatus usersRegistrationStatus = new UsersRegistrationStatus();
        final JsonObject jsonObject = json.getAsJsonObject();

        usersRegistrationStatus.setStatusCode(jsonObject.get("statusCode").getAsString());
        usersRegistrationStatus.setStatusMessage(jsonObject.get("statusMessage").getAsString());

        return usersRegistrationStatus;
    }
}
