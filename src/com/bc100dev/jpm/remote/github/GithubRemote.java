package com.bc100dev.jpm.remote.github;

import com.bc100dev.jpm.remote.RemoteSource;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static com.jpm.commons.utils.RuntimeEnvironment.getOperatingSystem;

public class GithubRemote extends RemoteSource {

    public GithubRemote(String user, String repo) {
        super("https://api.github.com/repos/" + user + "/" + repo);
    }

    @Override
    public void connect() throws IOException, URISyntaxException {
        URI uri = new URI(getUrl());
        URL mUrl = uri.toURL();
        HttpsURLConnection urlConnection = (HttpsURLConnection) mUrl.openConnection();
        urlConnection.setDoOutput(true);
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("User-Agent", "BeChris100/jpm, " + getOperatingSystem());
        urlConnection.setRequestProperty("Accept", "*/*");
        urlConnection.connect();

        DataInputStream dis = new DataInputStream(urlConnection.getInputStream());
        byte[] outputData = dis.readAllBytes();
        dis.close();

        JSONObject json = new JSONObject(new String(outputData));
        System.out.println(json.toString(2));
    }

}
