/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.notepadx.mods;

import java.util.Objects;

/**
 *
 * @author yangjz
 */
public class ModObject {
    ModInformation info;
    Class<?> mainClass;
    Object mainObject;
    
    @Override
    public boolean equals(Object obj){
        return this.info.modName.equals(((ModObject)obj).info.modName);
    } 

    @Override
    public int hashCode() {
        return info.modName.hashCode();
    }        
}
