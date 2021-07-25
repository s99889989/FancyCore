package com.daxton.fancycore.task;

import com.daxton.fancycore.config.FileConfig;

public class Reload {

    //重新讀取的一些任務
    public static void execute(){
        //建立設定檔
        FileConfig.execute();
    }

}
