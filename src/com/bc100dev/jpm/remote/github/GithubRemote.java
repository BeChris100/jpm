package com.bc100dev.jpm.remote.github;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class GithubRemote {

    public final static String BRANCH_DEFAULT_MASTER = "master";
    public final static String BRANCH_DEFAULT_MAIN = "main";

    public String user, repository, branch;

    public GithubRemote(String user, String repository) {
        this.user = user;
        this.repository = repository;
        this.branch = "master";
    }

    public GithubRemote(String user, String repository, String branch) {
        this.user = user;
        this.repository = repository;
        this.branch = branch;
    }

    public boolean isBranchOnline() throws IOException, URISyntaxException {
        URI mUri = new URI("https://github.com/" + user + "/" + repository + "/tree/" + branch);
        URL url = mUri.toURL();
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setRequestProperty("User-Agent", "BeChris100/jpm, Version 1.0");
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();

        return urlConnection.getResponseCode() == 200;
    }

    public String fetchManifest() throws IOException, URISyntaxException {
        URI mUri = new URI("https://raw.githubusercontent.com/" + user + "/" + repository + "/" + branch + "/.jpm/manifest.xml");
        URL url = mUri.toURL();
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setRequestProperty("User-Agent", "BeChris100/jpm, Version 1.0");
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();

        if (urlConnection.getResponseCode() != 200)
            throw new IOException("\"JPMSource.json\" has not been found on GitHub at \"" + user + "/" + repository + ":" + branch + "\"");

        DataInputStream dis = new DataInputStream(urlConnection.getInputStream());
        byte[] data = dis.readAllBytes();
        dis.close();

        return new String(data);
    }

}
