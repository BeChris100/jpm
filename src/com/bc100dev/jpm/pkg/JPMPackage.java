package com.bc100dev.jpm.pkg;

import com.jpm.commons.AdminCheck;
import com.jpm.commons.AdministratorException;

import java.io.File;
import java.io.IOException;

public class JPMPackage {

    private File pkgFile;

    public JPMPackage(File pkgFile) {
        this.pkgFile = pkgFile;
    }

    public File getPackageFile() {
        return pkgFile;
    }

    public void setPackageFile(File pkgFile) {
        this.pkgFile = pkgFile;
    }

    /**
     * Installs the Java Package into the Application directory, where it requires administrative
     * privileges.
     *
     * @throws IOException May throw an exception, if the operation cannot complete. Would most likely throw, if
     *                     the program is not running with administrative privileges.
     */
    public void rootInstall() throws IOException, AdministratorException {
        if (!AdminCheck.hasPrivileges())
            throw new AdministratorException("The current process does not have administrative privileges");

        JPMExtractor.extract(pkgFile);
    }

    /**
     * Installs the Java Package into the User Directory.
     *
     * @throws IOException May throw an exception, if the operation cannot complete.
     */
    public void install() throws IOException {
    }

}
