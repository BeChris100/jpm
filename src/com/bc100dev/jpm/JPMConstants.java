package com.bc100dev.jpm;

import com.jpm.commons.utils.RuntimeEnvironment;

import java.io.File;

import static com.jpm.commons.utils.RuntimeEnvironment.getOperatingSystem;

public class JPMConstants {

    public static final File APPDIR = new File(System.getProperty("jpm.app_dir"));
    public static final File BINDIR = new File(System.getProperty("jpm.bin_dir"));
    public static final File ROOTDIR = new File(System.getProperty("jpm.root_dir"));

    public static File getUserFiles() {
        return switch (getOperatingSystem()) {
            case WINDOWS -> new File(System.getenv("LocalAppData") + "\\jpm");
            case LINUX -> new File(RuntimeEnvironment.USER_HOME.getAbsolutePath() + "/.local/jpm");
            case MAC_OS -> new File(RuntimeEnvironment.USER_HOME.getAbsolutePath() + "/Library/jpm");
        };
    }

}
