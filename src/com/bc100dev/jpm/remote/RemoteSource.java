package com.bc100dev.jpm.remote;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.*;

import static com.jpm.commons.utils.RuntimeEnvironment.getOperatingSystem;

public class RemoteSource {

    private String url;
    private int port;

    public RemoteSource(String url, int port) {
        this.url = url;
        this.port = port;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void connect() throws IOException, URISyntaxException {
        URI uri = new URI(url);
        URL mUrl = uri.toURL();

        switch (mUrl.getProtocol()) {
            case "https" -> {
                HttpsURLConnection urlConnection = (HttpsURLConnection) mUrl.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("User-Agent", "BeChris100/jpm, " + getOperatingSystem());
                urlConnection.setRequestProperty("Accept", "*/*");
                urlConnection.connect();
            }
            case "http" -> {
                HttpURLConnection urlConnection = (HttpURLConnection) mUrl.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("User-Agent", "BeChris100/jpm, " + getOperatingSystem());
                urlConnection.setRequestProperty("Accept", "*/*");
                urlConnection.connect();
            }
            case "ftp" -> {
            }
        }
    }

}
