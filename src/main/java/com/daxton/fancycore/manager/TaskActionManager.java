package com.daxton.fancycore.manager;

import com.daxton.fancycore.api.task.FancyAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskActionManager {

    public static Map<String, List<Map<String, String>>> action_SubAction_Map = new HashMap<>();

    public static Map<String, FancyAction> task_Action_Map = new HashMap<>();

}
