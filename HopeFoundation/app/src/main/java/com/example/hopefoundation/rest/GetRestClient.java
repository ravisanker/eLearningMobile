package com.example.hopefoundation.rest;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class GetRestClient {

    private final String DEBUG_TAG = getClass().getSimpleName().toString();

    private final ConnectionFactory connectionFactory;

    public GetRestClient() {
        this(new ConnectionFactory());
    }

    public GetRestClient(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public ResponseStatusMessage getResponse(String Url) {
        ResponseStatusMessage responseObject = new ResponseStatusMessage();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DefaultHttpClient client = connectionFactory.getConnection();
        //Logger.vlog(DEBUG_TAG, "get:: url:: " + Url);
        try {
            HttpGet httpGet = new HttpGet(Url);
            HttpResponse response = client.execute(httpGet);

            StatusLine statusLine = response.getStatusLine();
          //  Logger.vlog(DEBUG_TAG, "::Status Code::" + statusLine.getStatusCode() + "::Message::" + statusLine.getReasonPhrase());
            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                response.getEntity().writeTo(out);
                out.close();
                responseObject.setResponseMessage(out.toString());
                responseObject.setStatusCode(HttpStatus.SC_OK);
            } else {
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            //CrittericismUtils.logHandledException(e);
            responseObject.setResponseMessage(out.toString());
            responseObject.setStatusCode(0);
        } catch (IOException e) {
            e.printStackTrace();
            // CrittericismUtils.logHandledException(e);
            responseObject.setResponseMessage(out.toString());
            responseObject.setStatusCode(-1);
        } catch (Exception e) {
            e.printStackTrace();
            //CrittericismUtils.logHandledException(e);
            responseObject.setResponseMessage(out.toString());
            responseObject.setStatusCode(-2);
        }

        return responseObject;
    }

    public ResponseStatusMessage getResponseV2(String Url) {
        ResponseStatusMessage responseObject = new ResponseStatusMessage();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DefaultHttpClient client = connectionFactory.getConnection();
        //Logger.vlog(DEBUG_TAG, "v2:: " + Url);
        try {
            HttpGet httpGet = new HttpGet(Url);
            HttpResponse response = client.execute(httpGet);
            response.getEntity().writeTo(out);
            out.close();
            String responseMessage = out.toString();
            responseObject.setResponseMessage(responseMessage);
            responseObject.setStatusCode(response.getStatusLine().getStatusCode());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
          //  CrittericismUtils.logHandledException(e);
            responseObject.setResponseMessage(out.toString());
        } catch (Exception e) {
            e.printStackTrace();
            //CrittericismUtils.logHandledException(e);
            responseObject.setResponseMessage(out.toString());
        }

        return responseObject;
    }
}
