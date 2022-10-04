package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    public static RequestSpecification req;

    public static PrintStream log;

    public RequestSpecification requestSpecification(String authType) throws IOException {

        if (authType.equalsIgnoreCase("Auth")) {
            if (req == null) {
                log = new PrintStream(new FileOutputStream("logging.txt"));
                req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                        .setContentType(ContentType.JSON).addHeader("Authorization", "Bearer " + getGlobalValue("token"))
                        .addHeader("Accept", "application/vnd.github+json")
                        .addFilter(RequestLoggingFilter.logRequestTo(log))
                        .addFilter(ResponseLoggingFilter.logResponseTo(log))
                        .build();
                return req;
            } else {
                req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                        .setContentType(ContentType.JSON).addHeader("Authorization", "Bearer " + getGlobalValue("token"))
                        .addHeader("Accept", "application/vnd.github+json")
                        .addFilter(RequestLoggingFilter.logRequestTo(log))
                        .addFilter(ResponseLoggingFilter.logResponseTo(log))
                        .build();
                return req;
            }

        } else if (authType.equalsIgnoreCase("No Auth")) {

            if (req == null) {
                log = new PrintStream(new FileOutputStream("logging.txt"));
                req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                        .setContentType(ContentType.JSON).addHeader("Accept", "application/vnd.github+json")
                        .addFilter(RequestLoggingFilter.logRequestTo(log))
                        .addFilter(ResponseLoggingFilter.logResponseTo(log))
                        .build();
                return req;
            } else {
                req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                        .setContentType(ContentType.JSON).addHeader("Accept", "application/vnd.github+json")
                        .addFilter(RequestLoggingFilter.logRequestTo(log))
                        .addFilter(ResponseLoggingFilter.logResponseTo(log))
                        .build();
                return req;
            }
        }
        req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                .setContentType(ContentType.JSON).addHeader("Authorization", "Bearer " + getGlobalValue("token"))
                .addHeader("Accept", "application/vnd.github+json")
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .build();
        return req;

    }

    // Method for setting the global value , this is being used for base url.
    public static String getGlobalValue(String key) throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/java/resources/global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }

}
