package com.socmodder.android.rps;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Miller
 * Date: 1/6/14
 * Time: 4:23 PM
 */
public class SubmitThrowTask extends AsyncTask<String, Integer, String> {

    private static String url = "http://roshambo.herokuapp.com/throws/";

    @Override
    protected String doInBackground(String... strings) {
        HttpResponse response = null;
        HttpClient httpClient = new DefaultHttpClient();
        String toss = url + strings[0];
        HttpGet get = new HttpGet(toss);
        String stuff =null, stuff2 = null;

        try{
            response = httpClient.execute(get);

            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();

            if(statusCode == 200){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                while((stuff = bufferedReader.readLine()) != null){
                    stuff2 += stuff;
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return stuff2;
    }
}
