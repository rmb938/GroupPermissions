/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mineempire.gpermissions.info;

import java.util.HashSet;

/**
 *
 * @author Ryan
 */
public class GroupInfo {
    
    public HashSet<String> permissions = new HashSet<String>();
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
