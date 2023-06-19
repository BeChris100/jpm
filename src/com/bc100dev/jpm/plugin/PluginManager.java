package com.bc100dev.jpm.plugin;

import com.jpm.commons.utils.io.FileUtil;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class PluginManager {

    private static boolean pluginMainEntry(String dirLocation, String classLocation) throws ClassNotFoundException {
        String fClassPath = classLocation;
        if (fClassPath.contains(dirLocation))
            fClassPath = fClassPath.replace(Matcher.quoteReplacement(dirLocation), "");

        if (fClassPath.contains("/"))
            fClassPath = fClassPath.replaceAll("/", ".");

        if (fClassPath.contains("\\"))
            fClassPath = fClassPath.replaceAll("\\\\", ".");

        if (fClassPath.endsWith(".class"))
            fClassPath = fClassPath.replace(".class", "");

        Class<?> mClass = Class.forName(fClassPath);
        Method[] methods = mClass.getDeclaredMethods();

        for (Method mMethod : methods) {
            if (mMethod.getName().equals("pluginMain") && mMethod.getParameterCount() == 2) {
                Parameter[] params = mMethod.getParameters();
                if (params.length != 2)
                    continue;

                if (params[0].getType() == String.class && params[1].getType() == String[].class)
                    System.out.println("Found class \"" + fClassPath + "\"");
            }
        }

        return false;
    }

    private static List<String> zipListClasses(String classloaderPath) throws IOException {
        List<String> entryClasses = new ArrayList<>();

        ZipFile zipFile = new ZipFile(classloaderPath);
        Enumeration<? extends ZipEntry> enumEntries = zipFile.entries();

        long count = 0;

        while (enumEntries.hasMoreElements()) {
            ZipEntry entry = enumEntries.nextElement();

            count++;
            System.out.println(classloaderPath + "(" + count + "): \"" + entry.getName() + "\"");

            /*
            if (entry.isDirectory())
                entryClasses.addAll(zipListClasses(classloaderPath, entry.getName(), zipFile));

            if (entry.getName().endsWith(".class")) {
                InputStream stream = zipFile.getInputStream(entry);
                DataInputStream dis = new DataInputStream(stream);

                boolean validClass = dis.readInt() == 0xCAFEBABE;

                dis.close();
                stream.close();

                if (validClass && zip_pluginMainEntry(zipFile, entry))
                    entryClasses.add()
            }
             */
        }

        zipFile.close();
        return entryClasses;
    }

    private static List<String> dirListClasses(String classloaderPath) throws IOException, ClassNotFoundException {
        List<String> classes = FileUtil.scanWithSpecificEnding(classloaderPath, ".class", false);
        for (String mClass : classes) {
            FileInputStream fis = new FileInputStream(mClass);
            DataInputStream dis = new DataInputStream(fis);

            boolean validClass = dis.readInt() == 0xCAFEBABE;

            dis.close();
            fis.close();

            if (validClass && pluginMainEntry(classloaderPath, mClass)) {
                String fClass = mClass;
                if (fClass.contains(classloaderPath))
                    fClass = fClass.replace(Matcher.quoteReplacement(classloaderPath), "");

                if (fClass.contains("/"))
                    fClass = fClass.replaceAll("/", ".");

                if (fClass.contains("\\"))
                    fClass = fClass.replaceAll("\\\\", ".");

                if (fClass.endsWith(".class"))
                    fClass = fClass.replace(".class", "");

                classes.add(fClass);
            }
        }

        return new ArrayList<>();
    }

    private static List<String> listClasses(String classloaderPath) throws IOException, ClassNotFoundException {
        List<String> classes = new ArrayList<>();

        File file = new File(classloaderPath);
        if (file.isFile() && (file.getName().endsWith(".jar") || file.getName().endsWith(".zip"))) {
            FileInputStream fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis);

            boolean validArchive = dis.readInt() == 0x504B0304;

            dis.close();
            fis.close();

            if (validArchive) {
                System.out.println("JAR Classpath for \"" + file.getAbsolutePath() + "\" verified");
                classes.addAll(zipListClasses(classloaderPath));
            }
        }

        if (file.isDirectory())
            classes.addAll(dirListClasses(classloaderPath));

        return classes;
    }

    public static List<Plugin> listPluginClasses() throws IOException, ClassNotFoundException {
        Enumeration<URL> roots = PluginManager.class.getClassLoader().getResources("");
        List<String> strPluginClasses = new ArrayList<>();

        while (roots.hasMoreElements()) {
            URL mUrl = roots.nextElement();
            String path = mUrl.getPath();
            System.out.println(path);

            strPluginClasses.addAll(listClasses(path));
        }

        List<Plugin> pluginClasses = new ArrayList<>();

        for (String strPluginClass : strPluginClasses)
            pluginClasses.add(new Plugin(strPluginClass));

        return pluginClasses;
    }

}
