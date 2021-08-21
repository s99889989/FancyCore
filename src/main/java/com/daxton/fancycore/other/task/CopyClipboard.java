package com.daxton.fancycore.other.task;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class CopyClipboard {

    public static void copy(String copyString){
        try {
            StringSelection selection = new StringSelection(copyString);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        }catch (Exception exception){

        }

    }

}
