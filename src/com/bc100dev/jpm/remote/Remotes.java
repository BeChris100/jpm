package com.bc100dev.jpm.remote;

import com.bc100dev.jpm.JPMConstants;
import com.jpm.commons.utils.OperatingSystem;
import com.jpm.commons.utils.RuntimeEnvironment;
import com.jpm.commons.utils.io.FileUtil;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.jpm.commons.utils.RuntimeEnvironment.getOperatingSystem;

public class Remotes {

    private static File getSourceFile() {
        return switch (getOperatingSystem()) {
            case WINDOWS -> new File(JPMConstants.APPDIR.getAbsolutePath() + "\\rsc\\remote.list.json");
            case LINUX, MAC_OS -> new File(JPMConstants.APPDIR.getAbsolutePath() + "/rsc/remote.list.json");
            default -> throw new IllegalCallerException("Unknown operating system");
        };
    }

    public static void sync() throws IOException {
        File defSrcFile = getSourceFile();
        File userSrcFile;
        List<String> userSrcFiles;

        switch (getOperatingSystem()) {
            case WINDOWS -> {
                userSrcFile = new File(JPMConstants.getUserFiles().getAbsolutePath() + "\\remote.list.json");
                userSrcFiles = FileUtil.listDirectory(JPMConstants.getUserFiles().getAbsolutePath() + "\\remotes.list.d", true, false, false);
            }
            case LINUX, MAC_OS -> {
                userSrcFile = new File(JPMConstants.getUserFiles().getAbsolutePath() + "/remotes.list.json");
                userSrcFiles = FileUtil.listDirectory(JPMConstants.getUserFiles().getAbsolutePath() + "/remotes.list.d", true, false, false);
            }
            default -> throw new IllegalCallerException("Unknown operating system");
        }

        List<File> sourceFiles = new ArrayList<>();

        if (!defSrcFile.exists())
            throw new IllegalCallerException("The default remote source file does not exist");

        sourceFiles.add(defSrcFile);

        if (userSrcFile.exists())
            sourceFiles.add(userSrcFile);

        for (String mUserSrcFile : userSrcFiles)
            sourceFiles.add(new File(mUserSrcFile));

        for (File sourceFile : sourceFiles) {
            JSONObject json = new JSONObject(FileUtil.readString(sourceFile.getAbsolutePath()));
        }
    }

}
