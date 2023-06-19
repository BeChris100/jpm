package com.jpm.plugin.aosp;

import com.jpm.plugin.aosp.download.AOSPDownload;

import java.io.IOException;

public class AOSP {

    private static int ACTION_THREADS = Runtime.getRuntime().availableProcessors();
    private static boolean ACTION_BACKUP = false;

    private static void helpPage() {
        System.out.println("AOSP (Android Open Source Project) Development");
        System.out.println();
        System.out.println("usage:");
        System.out.println("   init, -i (-u url) (-b branch) (-r)");
        System.out.println("       Initializes a fresh copy of the aosp development. If '-r' is specified,");
        System.out.println("       it will also initialize via the repo command for backwards compatibility.");
        System.out.println("   select, -s [type] [value]");
        System.out.println("       Selects a new value for a specific type (see \"Settings selection\")");
        System.out.println("   list, -l (type)");
        System.out.println("       Lists all possible values (see \"Settings selection\")");
        System.out.println("   list-installed, -ll (type)");
        System.out.println("       Lists all installed values (see \"Settings selection\")");
        System.out.println("   info, -I");
        System.out.println("       Get info on the manifest branch, current branch or unmerged branches");
        System.out.println("   diff, -D");
        System.out.println("       Shows changes between the last commit and the working tree");
        System.out.println("   download, -d (-j[threads]) (-b)"); // -b: Backup
        System.out.println("       Download the source of the selected branch for the AOSP");
        System.out.println("       The '-b' flag is ignored, if not yet downloaded. Otherwise, if a branch gets switched,");
        System.out.println("       this may take a whole lot of storage, depending on what branch just got switched into.");
        System.out.println("       The default value of '-j[threads]' will apply the amount of available cores from the processor.");
        System.out.println("   update, -u (-j[threads]) (-b)"); // -b: Backup
        System.out.println("       Checks a specific branch for updates, if there are any, and applies.");
        System.out.println("       If '-b' is specified, a backup will be made before overwriting any files.");
        System.out.println("   upload, -U");
        System.out.println("       Uploads the changes for code review");
        System.out.println();
        System.out.println("Switches:");
        System.out.println("   -b");
        System.out.println("       Runs a backup on the current branch / ");
        System.out.println();
        System.out.println("Settings selection (type):");
        System.out.println("   branch");
        System.out.println("       With just a few commands away, you can easily switch branches now.");
        System.out.println("   git-info-username");
        System.out.println("       Username for the GitHub initialization");
        System.out.println("   git-info-email");
        System.out.println("       E-Mail for the GitHub initialization");
    }

    public void pluginMain(String command, String[] cmdArgs) {
        if (!(command.equals("aosp") || command.equals("jpm-aosp"))) {
            System.err.println("Unknown command for \"" + command + "\"");
            System.exit(1);
            return;
        }

        if (cmdArgs == null || cmdArgs.length == 0) {
            helpPage();
            System.exit(1);
            return;
        }

        switch (cmdArgs[0]) {
            case "download", "-d" -> {
                if (cmdArgs.length >= 2) {
                    for (int i = 1; i < cmdArgs.length; i++) {
                        if (cmdArgs[i].startsWith("-j"))
                            ACTION_THREADS = Integer.parseInt(cmdArgs[i].substring("-j".length()));

                        if (cmdArgs[i].equals("-b")) {
                            if (!ACTION_BACKUP)
                                ACTION_BACKUP = true;
                        }
                    }
                }

                try {
                    AOSPDownload.download(ACTION_THREADS, ACTION_BACKUP);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            case "init", "-i" -> {
                String url = "";

                if (cmdArgs.length >= 2) {
                }
            }
        }

        for (String cmdArg : cmdArgs) {
            if (cmdArg.equals("download") || cmdArg.equals("-d")) {

            }
        }
    }

    public static void start(String[] cmdArgs) {
        if (cmdArgs == null || cmdArgs.length == 0) {
            helpPage();
            System.exit(1);
            return;
        }

        switch (cmdArgs[0]) {
            case "download", "-d" -> {
                if (cmdArgs.length >= 2) {
                    for (int i = 1; i < cmdArgs.length; i++) {
                        if (cmdArgs[i].startsWith("-j"))
                            ACTION_THREADS = Integer.parseInt(cmdArgs[i].substring("-j".length()));

                        if (cmdArgs[i].equals("-b")) {
                            if (!ACTION_BACKUP)
                                ACTION_BACKUP = true;
                        }
                    }
                }

                try {
                    AOSPDownload.download(ACTION_THREADS, ACTION_BACKUP);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            case "init", "-i" -> {
                String url = "";

                if (cmdArgs.length >= 2) {
                }
            }
        }
    }

}
