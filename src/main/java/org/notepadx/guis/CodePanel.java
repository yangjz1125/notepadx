/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.notepadx.guis;

import org.notepadx.utils.IOUtil;
import java.io.*;
import java.util.logging.*;
import org.fife.ui.rsyntaxtextarea.*;

/**
 *
 * @author yangjz
 */
public class CodePanel extends RSyntaxTextArea{
    public CodePanel(File f){
        codeFile = f;
        
    }
    
    public void SetFileMode(){
        this.setAutoIndentEnabled(true);
        this.setCodeFoldingEnabled(true);
        String fileName = codeFile.getName();
        String fileExtention = fileName.substring(fileName.indexOf(".")+1, fileName.length());
        this.setSyntaxEditingStyle(getSyntaxStyle(fileExtention));
    }
    
    public static String getSyntaxStyle(String fileExtention){
        switch(fileExtention){
            case "js":
                return SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT;
            case "sh":
                return SyntaxConstants.SYNTAX_STYLE_UNIX_SHELL;
            case "yml":
                return SyntaxConstants.SYNTAX_STYLE_YAML;
            case "htm":
                return SyntaxConstants.SYNTAX_STYLE_HTML;
            case "l":
                return SyntaxConstants.SYNTAX_STYLE_LISP;
            case "txt":
                return SyntaxConstants.SYNTAX_STYLE_NONE;
        }
        return "text/" + fileExtention;
    }
    
    public void readFromFile(){
        this.setText(IOUtil.readToString(codeFile));
    }
    
    public void writeToFile(){
        try (FileWriter writer = new FileWriter(codeFile)){
            writer.write(this.getText());
        } catch (IOException ex) {
            Logger.getLogger(CodePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public File codeFile;
}
