package com.example.hopefoundation.rest;

import android.util.Log;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PutRestClient {

    private static final String DEBUG_TAG = PutRestClient.class.getSimpleName();

    private final ConnectionFactory connectionFactory;

    public PutRestClient() {
        this(new ConnectionFactory());
    }

    public PutRestClient(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @SuppressWarnings("null")
    public ResponseStatusMessage getResponse(String Url, String finaljson) {
        //Logger.vlog(DEBUG_TAG, "PUT:: url:: " + Url);
        //Logger.vlog(DEBUG_TAG, "PUT:: data:: " + finaljson);

        ResponseStatusMessage responseObject = new ResponseStatusMessage();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DefaultHttpClient client = connectionFactory.getConnection();
        try {
            HttpPut post = new HttpPut(Url);
            post.setHeader("Content-type", "application/json");
            post.setHeader("Accept", "application/json");
            StringEntity se = new StringEntity(finaljson, "UTF-8");
            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(se);
            HttpResponse response = client.execute(post);

            StatusLine statusLine = response.getStatusLine();
          //  Logger.mlog("status code", statusLine.getStatusCode() + "");
            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                responseObject.setStatusCode(HttpStatus.SC_OK);
            } else {
                responseObject.setStatusCode(statusLine.getStatusCode());
            }

            response.getEntity().writeTo(out);
            out.close();
            Log.d("response", out.toString() + "  ");
            responseObject.setResponseMessage(out.toString());

        } catch (ClientProtocolException e) {
            responseObject.setResponseMessage(out.toString());
            responseObject.setStatusCode(0);
        } catch (IOException e) {
            responseObject.setResponseMessage(out.toString());
            responseObject.setStatusCode(-1);
        } catch (Exception e) {
            responseObject.setResponseMessage(out.toString());
            responseObject.setStatusCode(-2);
        }
        return responseObject;
    }
}
