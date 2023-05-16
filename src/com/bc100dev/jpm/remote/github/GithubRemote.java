package com.bc100dev.jpm.remote.github;

import com.bc100dev.jpm.remote.RemoteSource;

public class GithubRemote extends RemoteSource {

    public GithubRemote(String url) {
        super(url, 443);
    }
}
