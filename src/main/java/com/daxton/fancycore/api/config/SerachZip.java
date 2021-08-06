package com.daxton.fancycore.api.config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class SerachZip {

    //讀取jar內的.yml檔案路徑名稱
    public static List<String> findFile(String file, String start, String end, boolean folder) throws Exception {
        ZipFile zf = new ZipFile(file);
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        ZipInputStream zin = new ZipInputStream(in);
        ZipEntry ze;
        List<String> stringList = new ArrayList<>();
        while ((ze = zin.getNextEntry()) != null) {
            if (!ze.isDirectory()) {
                long size = ze.getSize();
                if(!ze.getName().endsWith(".class")){
                    if(ze.getName().startsWith(start) && ze.getName().endsWith(end)){
                        //FancyPack.fancyPack.getLogger().info(ze.getName());
                        stringList.add(ze.getName());
                    }
                }
                if (size > 0) {
                    BufferedReader br = new BufferedReader( new InputStreamReader(zf.getInputStream(ze)));
                    br.close();
                }
            }else {
                if(folder){
                    if(ze.getName().startsWith(start) && ze.getName().endsWith(end)){
                        //FancyPack.fancyPack.getLogger().info(ze.getName());
                        stringList.add(ze.getName());
                    }
                }
            }
        }
        zin.closeEntry();
        return stringList;
    }


    //讀取jar內的.yml檔案路徑名稱
    public static List<String> findYML(String file) throws Exception {
        ZipFile zf = new ZipFile(file);
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        ZipInputStream zin = new ZipInputStream(in);
        ZipEntry ze;
        List<String> stringList = new ArrayList<>();
        while ((ze = zin.getNextEntry()) != null) {
            if (!ze.isDirectory()) {
                long size = ze.getSize();
                if(ze.getName().contains(".yml") && !(ze.getName().equals("plugin.yml")) && !(ze.getName().equals("bungee.yml"))){
                    stringList.add(ze.getName());
                }
                if (size > 0) {
                    BufferedReader br = new BufferedReader( new InputStreamReader(zf.getInputStream(ze)));
//                    String line;
//                    while ((line = br.readLine()) != null) {
//                    }
                    br.close();
                }
            }
        }
        zin.closeEntry();
        return stringList;
    }


}
