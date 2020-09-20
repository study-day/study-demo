package com.sutdyday.studyfile;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

public class Filetools {


    /**
     * 查找所有的文件
     * @param fileList
     * @param parentDir
     */
    public static void findAllFiles(List<String> fileList,File parentDir) {
    	System.out.println("测试java代码里和依赖的jar中存在同名的Java类时会使用哪一个，如果如现此日志，则说明使用的是代码里的不是jar包里的");
    	System.out.println("studymvn3 test");
//      File parentFileDir = new File(parentDir);
//      String[] ChildFile = parentFileDir.list();
      if(parentDir.isFile()) {
    	  if(!parentDir.getName().endsWith("pom.xml")) {
    		  fileList.add(parentDir.getAbsoluteFile().toString());  
    	  }
    	  
      }else {
    	  for (int i = 0; i<parentDir.list().length;i++) {
        	  findAllFiles(fileList,new File(parentDir.getAbsolutePath()+File.separator+parentDir.list()[i]));
          }  
      }
    }
	
}
