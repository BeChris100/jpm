package com.bc100dev.jpm.download;

import java.util.List;

public class DownloadManager {

    private List<DownloadBuilder> downloadList;

    public DownloadManager() {
    }

    public void queue(DownloadBuilder builder) {
        downloadList.add(builder);
    }

    private void runDownload() {
    }

}
