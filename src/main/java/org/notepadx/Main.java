/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.notepadx;

import org.notepadx.guis.*;
import org.notepadx.mods.*;

/**
 *
 * @author yangjz
 */
public class Main {
    public static void main(String[] args){
        globalModLoader.getModFiles();
        globalModLoader.getModInformation();
        globalModLoader.LoadMods();
        MainFrame.start();
    }
    
    public static ModLoader globalModLoader = new ModLoader();
}
