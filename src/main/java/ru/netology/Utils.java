package ru.netology;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utils {
    public static String getUrl(String nasaUrl) {

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();

        ObjectMapper mapper = new ObjectMapper();
        HttpGet request = new HttpGet(nasaUrl);

        try {
            CloseableHttpResponse response = httpClient.execute(request);
            Nasa answer = mapper.readValue(response.getEntity().getContent(), Nasa.class);
            return answer.url;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return "Ничего нет";
    }
}
