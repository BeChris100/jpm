package com.jpm.commons.process;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProcessData {

    public static final long RUNNING_PID = ProcessHandle.current().pid();

    public static ProcessInfo getInfoForCurrentPid() {
        ProcessHandle handle = ProcessHandle.current();

        return new ProcessInfo(handle.pid(),
                handle.info().user().orElse("-"),
                new File(handle.info().command().orElse("-")),
                handle.info().commandLine().orElse("-"),
                handle.info().arguments().orElse(new String[0]));
    }

    public static ProcessInfo getInfoFromPid(long pid) {
        List<ProcessInfo> processes = listProcesses();

        for (ProcessInfo proc : processes) {
            if (proc.pid() == pid)
                return proc;
        }

        throw new IndexOutOfBoundsException("Tried to find process id " + pid + ", but there was no such pid currently running.");
    }

    public static List<ProcessInfo> listProcesses() {
        List<ProcessInfo> processes = new ArrayList<>();

        ProcessHandle.allProcesses().forEach(process -> {
            ProcessInfo info = new ProcessInfo(
                    process.pid(),
                    process.info().user().orElse("-"),
                    new File(process.info().command().orElse("-")),
                    process.info().commandLine().orElse("-"),
                    process.info().arguments().orElse(new String[0])
            );
            processes.add(info);
        });

        return processes;
    }

}
