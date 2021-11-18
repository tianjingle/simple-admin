//package leetcode;
//
//import com.alibaba.fastjson.JSONObject;
//import com.sogou.baike.mobile.common.CommonConfigManager;
//import com.sogou.baike.mobile.model.constants.CommonConstants;
//import org.apache.http.Consts;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpHost;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.config.Registry;
//import org.apache.http.config.RegistryBuilder;
//import org.apache.http.conn.DnsResolver;
//import org.apache.http.conn.socket.ConnectionSocketFactory;
//import org.apache.http.conn.socket.PlainConnectionSocketFactory;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.apache.http.impl.conn.SystemDefaultDnsResolver;
//import org.apache.http.ssl.SSLContexts;
//import org.apache.http.util.EntityUtils;
//
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.SSLContext;
//import java.io.IOException;
//import java.security.KeyManagementException;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by zhangyongguang on 2015/7/23.
// */
//public class HttpUtil {
//    // 传输普通http的HttpClient
//    private static CloseableHttpClient closeableDefaultHttpClient = null;
//    // 传输代理HttpClient
//    private static CloseableHttpClient closeableProxyClient = null;
//
//    static {
//        closeableDefaultHttpClient = initHttpClient(false);
//        closeableProxyClient = initHttpClient(true);
//    }
//
//    public static CloseableHttpClient getDefaultHttpClient() {
//        return closeableDefaultHttpClient;
//    }
//
//    public static CloseableHttpClient getProxyClient() {
//        return closeableProxyClient;
//    }
//
//    /**
//     * 获取传输普通http请求的HttpClient
//     */
//    private static CloseableHttpClient initHttpClient(boolean isProxy) {
//        DnsResolver dnsResolver = new SystemDefaultDnsResolver();
//        //需要通过以下代码声明对https连接支持
//        SSLContext sslcontext = null;
//        HttpClientBuilder httpClientBuilder;
//        CloseableHttpClient httpClient = null;
//
//        try {
//            sslcontext = SSLContexts.custom().loadTrustMaterial(null,
//                    new TrustSelfSignedStrategy())
//                    .build();
//        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
//        }
//
//        HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.getDefaultHostnameVerifier();
//
//        if (null != sslcontext) {
//            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
//                    sslcontext, hostnameVerifier);
//            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
//                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
//                    .register("https", sslsf)
//                    .build();
//            PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(
//                    socketFactoryRegistry, dnsResolver);
//            connManager.setMaxTotal(200);
//            connManager.setDefaultMaxPerRoute(100);
//            // 设置访问代理
//            HttpHost proxy;
//            RequestConfig requestConfig;
//
//            if (isProxy) {
//                proxy = new HttpHost(CommonConfigManager.PROXY_HOST, CommonConfigManager.PROXY_PORT);
//                requestConfig = RequestConfig.custom().setProxy(proxy).setSocketTimeout(3 * 1000).setConnectTimeout(1000).build();
//            } else {
//                requestConfig = RequestConfig.custom().setSocketTimeout(3 * 1000).setConnectTimeout(1000).build();
//            }
//
//            httpClientBuilder = HttpClients.custom();
//            httpClientBuilder.setConnectionTimeToLive(3000, TimeUnit.MILLISECONDS);
//            httpClientBuilder.setConnectionManager(connManager);
//            httpClientBuilder.setDefaultRequestConfig(requestConfig);
//
//            httpClient = httpClientBuilder.build();
//        }
//
//        return httpClient;
//    }
//
//    /**
//     * 获取HttpClient
//     */
//    public static CloseableHttpClient getHttpClient(boolean isProxy, boolean isHttps) {
//        CloseableHttpClient httpClient;
//
//        if (isProxy) {
//                httpClient = getProxyClient();
//        } else {
//            {
//                httpClient = getDefaultHttpClient();
//            }
//        }
//        return httpClient;
//    }
//
//    /**
//     * get string by httpEntity
//     *
//     * @param respEntity
//     * @return
//     */
//    public static String getStringByHttpEntity(HttpEntity respEntity) {
//        String result = null;
//
//        if (null != respEntity) {
//            try {
//                result = EntityUtils.toString(respEntity, CommonConfigManager.GLOBAL_ENCODE);
//            } catch (IOException e) {
//                CommonConstants.UTILS_LOGGER.error("getStringByHttpEntity error.", e);
//            }
//        }
//
//        return result;
//    }
//
//    /**
//     * post方法
//     *
//     * @param url
//     * @param params
//     * @param contentType
//     * @param isProxy
//     * @param isHttps
//     * @return
//     */
//    private static String httpPost(String url, String params, ContentType contentType, boolean isProxy, boolean isHttps) {
//        CommonConstants.UTILS_LOGGER.info("the request info for is: url = {}, params = {}, contentType = {}, isProxy = {}, isHttps = {}", url, params, contentType, isProxy, isHttps);
//        String result = null;
//        HttpEntity respEntity;
//        // 创建默认的httpClient实例.
//        CloseableHttpClient httpClient = getHttpClient(isProxy, isHttps);
//        // 创建HttpPost
//        HttpPost httppost = new HttpPost(url);
//        StringEntity stringEntity = new StringEntity(params, CommonConfigManager.GLOBAL_ENCODE);
//
//        try {
//            httppost.addHeader("Content-Type", contentType.toString());
//            httppost.setEntity(stringEntity);
//
//            try (CloseableHttpResponse response = httpClient.execute(httppost)) {
//                respEntity = response.getEntity();
//                if (null != respEntity) {
//                    result = getStringByHttpEntity(respEntity);
//                }
//            }
//        } catch (IOException e) {
//            CommonConstants.UTILS_LOGGER.error("httpPost error.", e);
//        }
//
//        CommonConstants.UTILS_LOGGER.info("the response info for {} with params = {}, contentType = {}, isProxy = {}, isHttps = {} is: result = {}", url, params, contentType, isProxy, result);
//        return result;
//    }
//
//    /**
//     * post方法(表单提交)
//     *
//     * @param url
//     * @param params
//     * @param isProxy
//     * @param isHttps
//     * @return
//     */
//    public static String httpPost(String url, String params, boolean isProxy, boolean isHttps) {
//        return httpPost(url, params, ContentType.create("application/x-www-form-urlencoded", Consts.UTF_8), isProxy, isHttps);
//    }
//
//    /**
//     * post方法(json格式提交)
//     *
//     * @param url
//     * @param jsonObject
//     * @param isProxy
//     * @param isHttps
//     * @return
//     */
//    public static String httpPost(String url, JSONObject jsonObject, boolean isProxy, boolean isHttps) {
//        return httpPost(url, jsonObject.toJSONString(), ContentType.create("application/json", Consts.UTF_8), isProxy, isHttps);
//    }
//}