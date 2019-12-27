package com.huaxu.ui.imgge;

import com.huaxu.util.FileUtil;

import javax.swing.*;

/**
 * <p>project: tetris</p>
 * <p>description: </p>
 * <p>create: 2019/12/27 12:56</p>
 * <p>author：huaxu</p>
 */
public class ImageIconDiy extends ImageIcon {

    public ImageIconDiy(String filename) {
        super(FileUtil.getPath(filename));
    }
}