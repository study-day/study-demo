package com.sutdyday.studyfile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {

    //static List<String> files = new ArrayList<String>();
    public static void main(String[] args) {
//    	List<String> fileList = new ArrayList<String>();
//    	findAllFiles(fileList,new File("D:\\Users\\lq\\Desktop\\差异对比0914\\0.92\\dexg-basicconfig"));
//    	fileList.forEach(file->
//    	System.out.println(file.replace("D:\\Users\\lq\\Desktop\\差异对比0914\\0.92\\", ""))
//    	);
        String selectDir = "D:\\Users\\lq\\Desktop\\差异对比0914\\0.92";
        String targetDir = "D:\\test\\gitlab2\\yhdesp-0.91";
        selectBatch2Del(selectDir, targetDir);
    }

    /**
     * @param selectDir
     * @param targetDir
     */
    public static void selectBatch2Del(String selectDir, String targetDir) {
        //

        List<String> fileList = new ArrayList<String>();
        findAllFiles(fileList, new File(selectDir));
        fileList.forEach(file -> {
            String filePath = file.replace(selectDir, targetDir);
            File file2 = new File(filePath);
            if (file2.exists()) {
                System.out.println(filePath);
                file2.deleteOnExit();
            }
        });
    }

    /**
     * 查找所有的文件
     * @param parentDir
     */
//    public static void findAllFiles(File parentDir) {
////      File parentFileDir = new File(parentDir);
////      String[] ChildFile = parentFileDir.list();
//      if(parentDir.isFile()) {
//    	  files.add(parentDir.getAbsoluteFile().toString());
//      }else {
//    	  for (int i = 0; i<parentDir.list().length;i++) {
//        	  findAllFiles(new File(parentDir.getAbsolutePath()+File.separator+parentDir.list()[i]));
//          }  
//      }
//    }

    /**
     * 查找所有的文件
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

