package com.uisrael.hikvision.backend.infraestructura.persistencia.integraciones.hikvision.cliente;

import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.Credentials;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.stereotype.Component;
import org.apache.hc.core5.util.Timeout;

import java.nio.charset.StandardCharsets;

@Component
public class HikvisionDigestClient {

    public String get(String baseUrl, String path, String username, String password) {
        String url = normalizeBaseUrl(baseUrl) + path;

        Credentials creds = new UsernamePasswordCredentials(username, password.toCharArray());
        BasicCredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(new AuthScope(null, -1), creds);

        RequestConfig requestConfig = RequestConfig.custom()
        	    .setConnectionRequestTimeout(Timeout.ofMilliseconds(5_000))
        	    .setResponseTimeout(Timeout.ofMilliseconds(10_000))
        	    .build();

        try (CloseableHttpClient http = HttpClients.custom()
                .setDefaultCredentialsProvider(provider)
                .setDefaultRequestConfig(requestConfig)
                .build()) {

            HttpGet req = new HttpGet(url);
            req.addHeader("Accept", "application/xml");

            try (CloseableHttpResponse resp = http.execute(req)) {
                int status = resp.getCode();
                HttpEntity entity = resp.getEntity();
                String body = (entity != null) ? EntityUtils.toString(entity, StandardCharsets.UTF_8) : "";

                if (status >= 200 && status < 300) return body;

                throw new RuntimeException("Hikvision call failed. status=" + status + ", body=" + body);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error calling Hikvision: " + url + " -> " + e.getMessage(), e);
        }
    }

    private String normalizeBaseUrl(String baseUrl) {
        if (baseUrl == null) return "";
        if (baseUrl.endsWith("/")) return baseUrl.substring(0, baseUrl.length() - 1);
        return baseUrl;
    }
}
