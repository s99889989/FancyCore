package com.daxton.fancycore.api.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SerachFile {

    //獲取資料夾下.yml檔案名稱
    public static List<File> findYML(String path){
        File root = new File(path);
        List<File> files = new ArrayList<>();
        if(!root.isDirectory()){
            if(root.getAbsolutePath().endsWith(".yml")){
                files.add(root);
            }
        }else{
            File[] subFiles = root.listFiles();
            assert subFiles != null;
            for(File f : subFiles){
                files.addAll(findYML(f.getAbsolutePath()));
            }
        }
        return files;
    }


}
