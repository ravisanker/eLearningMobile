package com.example.hopefoundation.rest;


import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PostRestClient {

    private static final String DEBUG_TAG = PostRestClient.class.getSimpleName();

    private final ConnectionFactory connectionFactory;

    public boolean overrideXhttp = false;
    public static final String XHTTP_METHOD_OVERRIDE = "X-HTTP-Method-Override";
    public static final String XHTTP_METHOD_PUT = "PUT";

    public PostRestClient() {
        this(new ConnectionFactory());
    }

    public PostRestClient(boolean override) {
        this(new ConnectionFactory());
        this.overrideXhttp = override;
    }

    public PostRestClient(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public ResponseStatusMessage getResponse(String Url, String finaljson) {
        // Logger.vlog(DEBUG_TAG, "post:: url:: " + Url);
        // Logger.vlog(DEBUG_TAG, "post:: data:: " + finaljson);
        ResponseStatusMessage responseObject = new ResponseStatusMessage();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DefaultHttpClient client = connectionFactory.getConnection();
        try {
            HttpPost post = new HttpPost(Url);
            post.setHeader("Content-type", "application/json");
            post.setHeader("Accept", "application/json");
            if (overrideXhttp) {
                post.addHeader(XHTTP_METHOD_OVERRIDE, XHTTP_METHOD_PUT);
            }
            StringEntity se = new StringEntity(finaljson, "UTF-8");
            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(se);
            HttpResponse response = client.execute(post);

            StatusLine statusLine = response.getStatusLine();
            // Logger.ilog(DEBUG_TAG, "::Status Code::" + statusLine.getStatusCode());

            responseObject.setStatusCode(statusLine.getStatusCode());
            response.getEntity().writeTo(out);
            out.close();
//            Logger.mlog(DEBUG_TAG, out.toString() + "  ");
            responseObject.setResponseMessage(out.toString());
        } catch (ClientProtocolException e) {
  //          CrittericismUtils.logHandledException(e);
            responseObject.setResponseMessage(out.toString());
            responseObject.setStatusCode(0);
        } catch (IOException e) {
   //         Logger.ilog(DEBUG_TAG, "::IO ExceptionError message::" + e.getMessage());
      //      CrittericismUtils.logHandledException(e);
            responseObject.setResponseMessage(out.toString());
            responseObject.setStatusCode(-1);
        } catch (Exception e) {
           // CrittericismUtils.logHandledException(e);
            responseObject.setResponseMessage(out.toString());
            responseObject.setStatusCode(-2);
        }
        return responseObject;
    }
}
