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
public class NotModException extends Exception {

    /**
     * Creates a new instance of <code>NotModException</code> without detail
     * message.
     */
    public NotModException() {
    }

    /**
     * Constructs an instance of <code>NotModException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public NotModException(String msg) {
        super(msg);
    }
}
