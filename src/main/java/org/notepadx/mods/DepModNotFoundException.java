/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.notepadx.mods;

/**
 *
 * @author yangjz
 */
public class DepModNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>DepModNotFoundException</code> without
     * detail message.
     */
    public DepModNotFoundException() {
    }

    /**
     * Constructs an instance of <code>DepModNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DepModNotFoundException(String msg) {
        super(msg);
    }
}
