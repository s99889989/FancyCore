package com.daxton.fancycore.task;

import com.comphenix.protocol.ProtocolLibrary;
import com.daxton.fancycore.manager.TaskActionManager;
import com.daxton.fancycore.protocol.ProtocolMap;
import com.daxton.fancycore.task.location.Sound;
import com.daxton.fancycore.task.player.Title;

public class Start {

    //只在開服時執行的任務
    public static void execute(){
        //ProtocolLib
        ProtocolMap.protocolManager = ProtocolLibrary.getProtocolManager();
        //
        TaskActionManager.task_Action_Map.put("sound", new Sound());
        TaskActionManager.task_Action_Map.put("title", new Title());

    }

}
