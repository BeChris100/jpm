package com.bc100dev.jpm;

import com.bc100dev.jpm.remote.RemoteSource;
import com.bc100dev.jpm.remote.Remotes;
import com.jpm.commons.AdminCheck;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class MainClass {

    private static void helpPage() {
        System.out.println("JPM (Java Package Manager)");
        System.out.println();
        System.out.println("supports:");
        System.out.println("   FTP -> Simple FTP connections and uploads/downloads");
        System.out.println("   GitHub -> Easier to manage releases and makes uploads/downloads easier");
        System.out.println("   GitLab -> Making secure connections over the internet");
        System.out.println();
        System.out.println("Packaging usage:");
        System.out.println("   install, -i [package...]");
        System.out.println("       Fetches the contents from the remote sources");
        System.out.println("   remove, -r [package...]");
        System.out.println("       Removes one or multiple packages");
        System.out.println();
        System.out.println("Remote usage:");
        System.out.println("   sync, -S");
        System.out.println("       Syncs all remote sources and fetches cached files");
        System.out.println("   update, -U");
        System.out.println("       Syncs all remote sources and fetches cached files");
        System.out.println("   add-src, -aS (-S) [type] [remote]");
        System.out.println("       Adds another remote source to the remote.list.json file");
    }

    private static void version() {
        System.out.println("JPM (Java Package Manager)");

        try {
            Properties props = new Properties();
            props.load(MainClass.class.getResourceAsStream("BuildConfig.cfg"));

            System.out.println("Version " + props.getProperty("build.version.name") + "-" + props.getProperty("build.version.code"));
            System.out.println(props.getProperty("build.type") + " build");
        } catch (IOException ignore) {
            System.out.println("Version " + BuildConfig.VERSION_NAME + "-" + BuildConfig.VERSION_CODE);
            System.out.println(BuildConfig.BUILD_TYPE + " build");
        }
    }

    public static void main(String[] args) {
        System.loadLibrary("jpm");

        if (args == null || args.length == 0) {
            helpPage();
            System.exit(1);
            return;
        }

        switch (args[0]) {
            case "help", "--help", "-h", "?" -> helpPage();
            case "version", "--version", "-v" -> version();
            case "sync", "-S" -> {
                try {
                    Remotes.sync();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        try {
            RemoteSource src = new RemoteSource("https://github.com/BeChris100/jpm", 443);
            src.connect();
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

}
