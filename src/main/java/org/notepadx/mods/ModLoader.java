/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.notepadx.mods;

import java.io.File;
import java.lang.reflect.InvocationTargetException; 
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.notepadx.utils.PathUtil;

/**
 *
 * @author yangjz
 */
public class ModLoader {
    HashSet<ModObject> mods = new HashSet<>();
    File[] modFiles = null;
    URLClassLoader classLoader;
    public int modCounter = 0;
    
    public void getModFiles(String dir){
        modFiles = new File(dir).listFiles();
    }
    
    public void getModFiles(){
        File modDir = new File(PathUtil.getProgramPath() + "/mod");
        System.out.println("load from "+ modDir.getAbsolutePath());
        if(!modDir.exists()){
            modDir.mkdir();
        } else {
            
            modFiles = modDir.listFiles();
        }       
    } 
    
    public void getModInformation(){
        if(modFiles == null){
            return;
        }
        try {
            URL[] urls = new URL[modFiles.length];
            for(int i = 0; i < modFiles.length; ++i){
                    urls[i] = modFiles[i].toURI().toURL();
            }
            classLoader = new URLClassLoader(urls);
            for(File f:modFiles){
                String fname = f.getName();
                ModObject modObj = new ModObject();
                try{
                    modObj.mainClass = classLoader.loadClass(fname.substring(0, fname.lastIndexOf('.')) + ".Main");
                    Method method = modObj.mainClass.getMethod("getModInformation", File[].class);
                    modObj.mainObject = modObj.mainClass.newInstance();
                    modObj.info = (ModInformation)method.invoke(modObj.mainObject, (Object) modFiles);
                } catch (NoSuchMethodException | SecurityException 
                        | InstantiationException | IllegalAccessException 
                        | IllegalArgumentException | InvocationTargetException ex) {
                    System.err.println("Warning: Can not load mod:"+fname);
                }
            }
        } catch (MalformedURLException | ClassNotFoundException ex) {
            Logger.getLogger(ModLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void LoadMod(ModObject modObj) throws DepModNotFoundException, NotModException{
        for(String depMod:modObj.info.modDeps){
            ModObject mobj = new ModObject();
            mobj.info.modName = depMod;
            if(!mods.contains(mobj)){
                throw new DepModNotFoundException(modObj.info.modName);
            }
        }
        try {
            Method method = modObj.mainClass.getMethod("load");
            method.invoke(modObj.mainObject);
            ++modCounter;
        } catch (NoSuchMethodException | SecurityException
                | IllegalAccessException | IllegalArgumentException 
                | InvocationTargetException ex) {
            throw new NotModException(modObj.info.modName);
        }
    }
    
    public void LoadMods(){
        
        mods.forEach((modObj) -> {
            try {
                LoadMod(modObj);
            } catch (DepModNotFoundException ex) {
                System.err.println("Cannot find the depends of "+modObj.info.modName);
            } catch (NotModException ex) {
                System.err.println(modObj.info.modName + " is not a mod");
            }
        });
    }
}
