/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mineempire.gpermissions.info;

import java.util.HashMap;

/**
 *
 * @author Ryan
 */
public class WorldInfo {
    
    public HashMap<String, GroupInfo> groups = new HashMap<String, GroupInfo>();
    public HashMap<String, PlayerInfo> players = new HashMap<String, PlayerInfo>();
    
    public void loadGroups(String worldN) {
        
    }
    
    private String parent;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
    
}
