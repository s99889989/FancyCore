package com.daxton.fancycore.api.config;

import java.io.File;
import java.util.*;

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

    //獲取資料夾下檔案檔案名稱
    public static List<File> findC(String path, String startKey, String endKey, String containKey){
        File root = new File(path);
        List<File> files = new ArrayList<>();
        if(!root.isDirectory()){
            String patch = root.getAbsolutePath();
            if(patch.startsWith(startKey) && patch.endsWith(endKey) && patch.contains(containKey)){
                files.add(root);
            }
        }else{
            File[] subFiles = root.listFiles();
            assert subFiles != null;
            for(File f : subFiles){
                files.addAll(findC(f.getAbsolutePath(), startKey, endKey, containKey));
            }
        }
        return files;
    }

    //獲取插件資料夾下 檔案絕對路徑名稱
    public static List<String> pathNameList(String path, String startKey, String endKey, String containKey){
        File root = new File(path);
        List<String> files = new ArrayList<>();
        if(!root.isDirectory()){
            String patch = root.getAbsolutePath().replace("\\", "/");
            if(patch.startsWith(startKey) && patch.endsWith(endKey) && patch.contains(containKey)){

                files.add(patch);
            }
        }else{
            File[] subFiles = root.listFiles();
            assert subFiles != null;
            //List<File> fileList = Arrays.asList(subFiles);
            Arrays.sort(subFiles, new WindowsExplorerStringComparator());
            for(File f : subFiles){
                String runOut = f.getAbsolutePath();
                files.addAll(pathNameList(runOut, startKey, endKey, containKey));
            }
        }
        return files;
    }

    //獲取資料夾下檔案檔案名稱
    public static List<File> findFilePathList(String path, String startKey, String endKey, String containKey){
        File root = new File(path);
        List<File> files = new ArrayList<>();
        if(!root.isDirectory()){
            String patch = root.getAbsolutePath();
            if(patch.startsWith(startKey) && patch.endsWith(endKey) && patch.contains(containKey)){
                files.add(root);
            }
        }else{
            File[] subFiles = root.listFiles();
            assert subFiles != null;
            for(File f : subFiles){
                files.addAll(findC(f.getAbsolutePath(), startKey, endKey, containKey));
            }
        }
        return files;
    }

}
