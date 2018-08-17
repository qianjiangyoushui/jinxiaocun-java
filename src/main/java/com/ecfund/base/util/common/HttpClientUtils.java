package com.ecfund.base.util.common;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class HttpClientUtils {
    // 测试环境
    // private static final String APP_URL ="http://121.42.197.212:8082/api/";
    //客户环境
    //  private static final String APP_URL ="http://123.56.190.116:8082/api/";
//	  private static final String APP_URL = "http://123.56.190.116:8082/api/";// 开发环境/
    private static final String APP_URL = "http://119.23.224.146/api/";// 生产环境

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * @param requestPath
     *            请求路径
     * @param
     *
     * @param jsondata
     *            from里其它参数以json格式
     * @return
     */
    public static void postFromFileData(String requestPath, String[] fileNames, File[] files, String jsondata,
                                        String deviceToken, String version) {

        HttpClient httpclient = new DefaultHttpClient();
        MultipartEntity reqEntity = new MultipartEntity();
        try {
            HttpPost httppost = new HttpPost(APP_URL + requestPath);
            for(int i = 0; i < files.length; i ++){
                File file = files[i];
                if (file.exists()) {
                    FileBody headFile = new FileBody(file);
                    reqEntity.addPart(fileNames[i], headFile);// file为请求后台的File

                    // upload;属性
                }
            }
            String transdata = AESUtils.encAESCode(jsondata, "jingtum2017tudou");
            StringBody comment = new StringBody(transdata);
            reqEntity.addPart("data", comment);// filename为请求后台的普通参数;属性

            StringBody deviceTokens = new StringBody(deviceToken);
            StringBody versions = new StringBody(version);
            reqEntity.addPart("deviceToken", deviceTokens);
            reqEntity.addPart("version", versions);

            httppost.setEntity(reqEntity);
            HttpResponse response = httpclient.execute(httppost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String results = EntityUtils.toString(entity, "UTF-8");
                    System.out.println(results);
                    logger.debug("results:" + results);
                    // 解析请求数据
                    JSONObject dataObject = JSONObject.parseObject(results);
                    String error = dataObject.getString("code");
                    String msg = dataObject.getString("msg");
                    String callbackdata = dataObject.getString("data");
                    if (StringUtils.isNotBlank(callbackdata) && StringUtils.isNotEmpty(callbackdata)) {
                        String resultdata = AESUtils.desAESCode(callbackdata, "jingtum2017tudou");
                        System.out.println("decode-result:" + resultdata);
                        logger.debug(resultdata);
                    }
                    logger.debug("msg:" + msg + " code:" + error);
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.getConnectionManager().shutdown();
            } catch (Exception ignore) {

            }
        }
    }

    public static void postFromFileData(String requestPath, String fileName, File file, String jsondata,
                                        String deviceToken, String version) {
        postFromFileData(requestPath, new String[]{fileName}, new File[]{file}, jsondata, deviceToken, version);
    }

    /**
     * POST表单数据请求
     *
     * @param requestPath
     *            请求相对路径
     * @param jsondata
     *            参数data值
     * @return
     */
    public static String[]  postFormData(String requestPath, String jsondata, String deviceToken, String version) {
        // 创建默认的httpClient实例.
        String result[] = new String[2];
        HttpClient httpclient = new DefaultHttpClient();
        // 创建HTTPPOST
        HttpPost httppost = new HttpPost(APP_URL + requestPath);
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        String transdata = AESUtils.encAESCode(jsondata, "jingtum2017tudou");
        formparams.add(new BasicNameValuePair("data", transdata));
        formparams.add(new BasicNameValuePair("deviceToken", deviceToken));
        formparams.add(new BasicNameValuePair("version", version));

        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String results = EntityUtils.toString(entity, "UTF-8");
                System.out.println("encode-result:" + results);
                // 解析请求数据
                result[0] = results;
                JSONObject dataObject = JSONObject.parseObject(results);
                String callbackdata = dataObject.getString("data");
                if (StringUtils.isNotBlank(callbackdata) && StringUtils.isNotEmpty(callbackdata)) {
                    String resultdata = AESUtils.desAESCode(callbackdata, "jingtum2017tudou");
                    result[1] = resultdata;
                    System.out.println("decode-result:" + resultdata);
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            httpclient.getConnectionManager().shutdown();
        }
        return result;
    }

    /**
     * 发送 get请求
     *
     * @param requestPath
     *            请求相对路径 可带相应的参数
     */
    public static void getHttpRequestData(String requestPath) {

        HttpClient httpclient = new DefaultHttpClient();

        try {
            // 创建HTTPGET.
            HttpGet httpget = new HttpGet(APP_URL + requestPath);
            // 执行get请求.
            HttpResponse response = httpclient.execute(httpget);
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String results = EntityUtils.toString(entity, "UTF-8");
                // 解析请求数据
                JSONObject dataObject = JSONObject.parseObject(results);
                String error = dataObject.getString("code");
                String msg = dataObject.getString("msg");
                String callbackdata = dataObject.getString("data");
                if (StringUtils.isNotBlank(callbackdata) && StringUtils.isNotEmpty(callbackdata)) {
                    String resultdata = AESUtils.desAESCode(callbackdata, "jingtum2017tudou");
                    System.out.println(resultdata);
                }
                ;
                System.out.println("msg:" + msg + " code:" + error);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            httpclient.getConnectionManager().shutdown();
        }
    }

    /**
     * POST表单数据请求
     *
     * @param requestPath
     *            请求相对路径
     * @param
     *
     * @return
     */
    public static void postAliData(String requestPath, List<NameValuePair> formparams) {
        // 创建默认的httpClient实例.
        HttpClient httpclient = new DefaultHttpClient();
        // 创建HTTPPOST
        HttpPost httppost = new HttpPost(APP_URL + requestPath);
        // 创建参数队列
        // List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        // String transdata = AESUtils.encAESCode(jsondata,
        // Constants.SECRETKEY);
        // formparams.add(new BasicNameValuePair("data", transdata));
        // formparams.add(new BasicNameValuePair("deviceToken", deviceToken));
        // formparams.add(new BasicNameValuePair("version", version));

        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String results = EntityUtils.toString(entity, "UTF-8");
                System.out.println("encode-result:" + results);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            httpclient.getConnectionManager().shutdown();
        }
    }

}

