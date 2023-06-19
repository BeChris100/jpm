package com.jpm.plugin.aosp.download;

import com.jpm.commons.utils.RuntimeEnvironment;

import java.io.File;
import java.io.IOException;

public class AOSPDownload {

    public static void download(int threads, boolean backup) throws IOException {
        System.out.println("WorkDir: " + RuntimeEnvironment.WORKING_DIRECTORY.getAbsolutePath());
        System.out.println("Threads: " + threads);
        System.out.println("Backup: " + backup);
    }

}
