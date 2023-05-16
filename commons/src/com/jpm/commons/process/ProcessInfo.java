package com.jpm.commons.process;

import java.io.File;

public record ProcessInfo(long pid, String user, File execFile, String cmdLine, String[] args) {
}
