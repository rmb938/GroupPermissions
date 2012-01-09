/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mineempire.gpermissions;

import com.mineempire.gpermissions.info.PlayerInfo;
import com.mineempire.gpermissions.info.WorldInfo;
import java.util.HashMap;
import java.util.Iterator;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Ryan
 */
public class GroupPermissions extends JavaPlugin {

    public HashMap<String, WorldInfo> worlds = new HashMap<String, WorldInfo>();
    public HashMap<String, PermissionAttachment> perms = new HashMap<String, PermissionAttachment>();

    @Override
    public void onDisable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        PluginDescriptionFile pdfFile = this.getDescription();
        pm.registerEvent(Type.PLAYER_CHANGED_WORLD, null, Priority.Monitor, this);
        pm.registerEvent(Type.BLOCK_BREAK, null, Priority.Lowest, this);
        pm.registerEvent(Type.BLOCK_PLACE, null, Priority.Lowest, this);
        System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
        /*
        Create plugin folder, config file, world folders, default.txt and users.txt in each world folder
         * 
         */
        //Automatically set worldname_nether and worldname_the_end parents as worldname in config.txt
    }

    public void loadWorlds() {
        //check world inheritance
        //get world folders
        //get groups and users per world/inheritance
    }
    
    //get rid of while loop?
    public void registerPlayer(Player p, String worldN) {
        PermissionAttachment att;
        if (perms.containsKey(p.getName().toLowerCase()) == true) {
            att = perms.get(p.getName().toLowerCase());
        } else {
            att = p.addAttachment(this);
        }
        WorldInfo wi = worlds.get(p.getWorld().getName());
        if (wi != null) {
            if (wi.getParent().equalsIgnoreCase("none") == false) {
                wi = worlds.get(wi.getParent());
            }
            if (wi != null) {
                PlayerInfo pi = wi.players.get(p.getName().toLowerCase());
                if (pi != null) {
                    Iterator<String> it = pi.permissions.iterator();
                    while (it.hasNext()) {
                        String perm = it.next();
                        if (perm.startsWith("-") == false) {
                            att.setPermission(perm, true);
                        } else {
                            att.setPermission(perm, false);
                        }
                    }
                } else {//player is not in users.txt so set default group permissions
                    Iterator<String> it = wi.groups.get("default").permissions.iterator();
                    while (it.hasNext()) {
                        String perm = it.next();
                        if (perm.startsWith("-") == false) {
                            att.setPermission(perm, true);
                        } else {
                            att.setPermission(perm, false);
                        }
                    }
                }
            }
        }
    }

    //get rid of while loop?
    public void playerSwitchedWorlds(Player p, String oldWorld) {
        PermissionAttachment att = perms.get(p.getName().toLowerCase());
        WorldInfo wi = worlds.get(oldWorld);
        if (wi != null) {
            if (wi.getParent().equalsIgnoreCase("none") == false) {
                wi = worlds.get(wi.getParent());
            }
            if (wi != null) {
                PlayerInfo pi = wi.players.get(p.getName().toLowerCase());
                if (pi != null) {
                    Iterator<String> it = pi.permissions.iterator();
                    while (it.hasNext()) {
                        String perm = it.next();
                        if (perm.startsWith("-") == false) {
                            att.unsetPermission(perm);
                        } else {
                            att.unsetPermission(perm);
                        }
                    }
                }
            }
        }
        registerPlayer(p, p.getWorld().getName());
    }
}
