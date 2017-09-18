/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DropboxClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author aleksi
 */
public class DropboxClient {
    
    public String accessToken(String codeStr) throws URISyntaxException, IOException {
        System.out.println("requesting access token for code " + codeStr);
        String code = ""+codeStr;
        String appKey = "tr9gsdtd5ftf2xo"; 
        String appSecret = "ha8pdfcxh6wgoir";
//        String redirectURI = "http://localhost:8084/DropboxClient/";
        
        StringBuilder tokenUri = new StringBuilder("code=");
        tokenUri.append(URLEncoder.encode(code,"UTF-8"));
        tokenUri.append("&grant_type=");
        tokenUri.append(URLEncoder.encode("authorization_code","UTF-8"));
        tokenUri.append("&client_id=");
        tokenUri.append(URLEncoder.encode(appKey,"UTF-8"));
        tokenUri.append("&client_secret=");
        tokenUri.append(URLEncoder.encode(appSecret,"UTF-8"));
//        tokenUri.append("&redirect_uri="+redirectURI.toString());
        
        URL url = new URL("https://api.dropbox.com/1/oauth2/token");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        try {
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", "" + tokenUri.toString().length());
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWriter.write(tokenUri.toString());
            outputStreamWriter.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
//            return response.toString();
            JSONParser parser = new JSONParser();
            JSONObject resp;
            try {
                resp = (JSONObject) parser.parse(response.toString());
                return (String) resp.get("access_token");
            } catch (ParseException ex) {
                Logger.getLogger(DropboxClient.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } finally {
            connection.disconnect();
        }
    }
    
    public void getAccountInfo(String tokenStr) throws  URISyntaxException, IOException {
        String access_token = ""+tokenStr; 
        StringBuilder accountInfoUri = new StringBuilder("https://api.dropbox.com/1/account/info");
        accountInfoUri.append("?access_token=");
        accountInfoUri.append(URLEncoder.encode(access_token,"UTF-8"));
        URL url = new URL(accountInfoUri.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print result
            System.out.println(response.toString());
        } finally {
            connection.disconnect();
        }
    }
    
    public void uploadFile(String token, String path) throws URISyntaxException, IOException {
        String access_token = ""+token; 
        String sourcePath = ""+path; 
        //required file path on local file system
        Path pathFile = Paths.get(sourcePath);
        byte[] data = Files.readAllBytes(pathFile);
        StringBuilder accountInfoUri =new StringBuilder("https://api-content.dropbox.com/1/files_put/dropbox/MyFirstDApp_files/images/image_initial_uploaded.png");
        accountInfoUri.append("?access_token=");
        accountInfoUri.append(URLEncoder.encode(access_token,"UTF-8"));
        URL url = new URL(accountInfoUri.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.setDoOutput(true);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "mime/type");
            connection.setRequestProperty("Content-Length", String.valueOf(data.length));
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(data);
            outputStream.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print result
            System.out.println(response.toString());
        } finally {
            connection.disconnect();
        }
    }
}

