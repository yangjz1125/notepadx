/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.notepadx.utils;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author yangjz
 */
public class IOUtil {
     public static String readToString(File file) {
         try {
             Long filelength = file.length();     //获取文件长度
             byte[] filecontent = new byte[filelength.intValue()];
             try (FileInputStream in = new FileInputStream(file)) {
                 in.read(filecontent);
             }
             return new String(filecontent);//返回文件内容,默认编码
         } catch (IOException ex) {
             Logger.getLogger(IOUtil.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;
    }
}
