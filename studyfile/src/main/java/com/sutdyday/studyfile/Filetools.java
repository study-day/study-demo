package com.sutdyday.studyfile;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

public class Filetools {


    /**
     * 查找指定路径下所有的文件
     *
     * @param fileList
     * @param parentDir
     */
    public static void findAllFiles(List<String> fileList, File parentDir) {
//      File parentFileDir = new File(parentDir);
//      String[] ChildFile = parentFileDir.list();
        if (parentDir.isFile()) {
            if (!parentDir.getName().endsWith("pom.xml")) {
                fileList.add(parentDir.getAbsoluteFile().toString());
            }

        } else {
            for (int i = 0; i < parentDir.list().length; i++) {
                findAllFiles(fileList, new File(parentDir.getAbsolutePath() + File.separator + parentDir.list()[i]));
            }
        }
    }

}
