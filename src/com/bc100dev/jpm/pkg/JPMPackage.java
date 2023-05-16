package com.bc100dev.jpm.pkg;

import java.io.IOException;

public class JPMPackage {

    /**
     * Installs the Java Package into a privileged location. On Linux/Unix systems, the location can be
     * <code>/opt/jpm/packages/[author]/[name]</code>, whereas on Windows systems, the location would be something like
     * <code>C:\Program Files\jpm\Packages\[package-name]</code>.
     *
     * @throws IOException May throw an exception, if the operation cannot complete. Would most likely throw, if
     * the program is not running with administrative privileges.
     */
    public void rootInstall() throws IOException {
    }

    /**
     * Installs the Java Package into the user location, whereas on Linux, the location of the installation would be
     * <code>/home/[username]/.local/jpm/packages/[package-name]</code>. On Windows systems, the location would prefer like
     * <code>C:\Users\[username]\AppData\Local\jpm\Packages\[package-name]</code>.
     * @throws IOException May throw an exception, if the operation cannot complete.
     */
    public void install() throws IOException {
    }

}
