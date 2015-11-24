package com.example.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.example.pojo.UsersRegistrationStatus;
import com.example.pojo.UsersRegistrationStatustDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.example.timepay.BuildConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Api requests
 */
public class ApiRequests
{
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(UsersRegistrationStatus.class, new UsersRegistrationStatustDeserializer())
            .create();
    /**
     * Returns a dummy object
     *
     * @param listener is the listener for the correct answer
     * @param errorListener is the listener for the error response
     *
     * @return {@link GsonGetRequest}
     */
    /*public static GsonGetRequest<OrgType> getDummyObject
    (
            @NonNull final Response.Listener<OrgType> listener,
            @NonNull final Response.ErrorListener errorListener
    )
    {
        //final String url = BuildConfig.apiDomainName + "/v2/55973508b0e9e4a71a02f05f";
        final String url = BuildConfig.apiDomainName + "/project-1.0.0-BUILD-SNAPSHOT/getOrgType";
        return new GsonGetRequest<>
                (
                        url,
                        new TypeToken<OrgType>() {}.getType(),
                        gson,
                        listener,
                        errorListener
                );
    }*/

    /**
     * Returns a dummy object's array
     *
     * @param listener is the listener for the correct answer
     * @param errorListener is the listener for the error response
     *
     * @return {@link GsonGetRequest}
     */
    /*public static GsonGetRequest<ArrayList<DummyObject>> getDummyObjectArray
    (
            @NonNull final Response.Listener<ArrayList<DummyObject>> listener,
            @NonNull final Response.ErrorListener errorListener
    )
    {
        final String url = BuildConfig.apiDomainName + "/v2/5597d86a6344715505576725";
//        final String url = BuildConfig.apiDomainName + "/project-1.0.0-BUILD-SNAPSHOT/getOrgType";
        return new GsonGetRequest<>
                (
                        url,
                        new TypeToken<ArrayList<DummyObject>>() {}.getType(),
                        gson,
                        listener,
                        errorListener
                );
    }*/


    /**
     * An example call (not used in this app example) to demonstrate how to do a Volley POST call
     * and parse the response with Gson.
     *
     * @param listener is the listener for the success response
     * @param errorListener is the listener for the error response
     *
     * @return {@link GsonPostRequest}
     */
   /* public static GsonPostRequest getDummyObjectArrayWithPost
    (
            @NonNull final Response.Listener<DummyObject> listener,
            @NonNull final Response.ErrorListener errorListener
    )
    {
        final String url = BuildConfig.apiDomainName + "/dummyPath";

        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "Ficus");
        jsonObject.addProperty("surname", "Kirkpatrick");

        final JsonArray squareGuys = new JsonArray();
        final JsonObject dev1 = new JsonObject();
        final JsonObject dev2 = new JsonObject();
        dev1.addProperty("name", "Jake Wharton");
        dev2.addProperty("name", "Jesse Wilson");
        squareGuys.add(dev1);
        squareGuys.add(dev2);

        jsonObject.add("squareGuys", squareGuys);

        final GsonPostRequest gsonPostRequest = new GsonPostRequest<>
                (
                        url,
                        jsonObject.toString(),
                        new TypeToken<DummyObject>() {}.getType(),
                        gson,
                        listener,
                        errorListener
                );

        gsonPostRequest.setShouldCache(false);

        return gsonPostRequest;
    }*/

    public static GsonPostRequest getPayObjectArrayWithPost
            (
                    @NonNull final Response.Listener<UsersRegistrationStatus> listener,
                    @NonNull final Response.ErrorListener errorListener,
                    final Map<String, String> mParams,
                    String registrationURL
            )
    {
        final String url = BuildConfig.apiDomainName + registrationURL;


        final JsonObject registerParams = new JsonObject();
        registerParams.addProperty("emil", mParams.get("emil"));
        registerParams.addProperty("mobileNumber", mParams.get("mobileNumber"));
        final GsonPostRequest gsonPostRequest = new GsonPostRequest<UsersRegistrationStatus>
                (
                        url,
                        registerParams.toString(),
                        new TypeToken<UsersRegistrationStatus>() {}.getType(),
                        gson,
                        listener,
                        errorListener
                ){
            @Override
            public Map<String, String> getHeaders () throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //Content-Type: application/json
                params.put("Content-Type", "application/json");
                //params.put("Accept-Language", "fr");

                return params;
            }

            @Override
            public Map<String, String> getParams() {
                return mParams;
            }
        };

        gsonPostRequest.setShouldCache(false);
        Log.i("RegistrationRes",gsonPostRequest.getBody().toString());
        return gsonPostRequest;
    }
}
