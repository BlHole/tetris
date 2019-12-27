package com.huaxu.util;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * <p>project: tetris</p>
 * <p>description: </p>
 * <p>create: 2019/12/27 12:51</p>
 * <p>author：huaxu</p>
 */
public class FileUtil {

    public static String getPath(String resources) {
        return FileUtil.class.getClassLoader().getResource(resources).getPath();
    }

    public static InputStream getInputStream(String resources) {
        return FileUtil.class.getClassLoader().getResourceAsStream(resources);
    }

    public static FileOutputStream getOutputStream(String resources) throws FileNotFoundException {
        return new FileOutputStream(getPath(resources));
    }
}