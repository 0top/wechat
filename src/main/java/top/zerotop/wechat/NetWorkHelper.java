package top.zerotop.wechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class NetWorkHelper {

    public String getHttpsResponse(String hsUrl, String requestMethod) {
        URL url;
        InputStream is = null;
        String resultData = "";
        try {
            url = new URL(hsUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        TrustManager[] tm = {xtm};

        SSLContext ctx = SSLContext.getInstance("TLS");
        try {
            ctx.init(null, tm, null);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        con.setSSLSocketFactory(ctx.getSocketFactory());
        con.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
        con.setDoInput(true);
        con.setDoOutput(false);
        con.setUseCaches(false);

        try {
            if (null != requestMethod && !requestMethod.equals("")) {
                con.setRequestMethod(requestMethod);
            } else {
                con.setRequestMethod("GET");
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        }


        try {
            is = con.getInputStream();

            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(isr);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {


            String inputLine = "";
            while ((inputLine = bufferReader.readLine()) != null) {
                resultData += inputLine + "\n";
            }
            Certificate[] certs = con.getServerCertificates();

            int certNum = 1;

            for (Certificate cert : certs) {
                X509Certificate xcert = (X509Certificate) cert;
            }
        } catch (Exception e) {
            System.out.println("In NetWorkHelper exception ");
        }
        return resultData;
    }

    X509TrustManager xtm = new X509TrustManager() {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            // TODO Auto-generated method stub

        }

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            // TODO Auto-generated method stub

        }
    };
}
