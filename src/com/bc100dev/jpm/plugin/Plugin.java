package com.bc100dev.jpm.plugin;

import com.jpm.commons.utils.Utility;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class Plugin {

    private final String classFilePath;

    protected Plugin(String classFilePath) {
        this.classFilePath = classFilePath;
    }

    public String getClassFilePath() {
        return classFilePath;
    }

    public void run(String command, String[] arguments) throws Exception {
        Class<?> clazz = Class.forName(classFilePath);
        Class<?>[] argTypes = new Class[]{String.class, String[].class};
        Method pluginMain = clazz.getDeclaredMethod("pluginMain", argTypes);

        String[] sCmdArgs = Utility.toArray(new ArrayList<>(Arrays.asList(arguments)));
        pluginMain.invoke(null, command, sCmdArgs);
    }

}
