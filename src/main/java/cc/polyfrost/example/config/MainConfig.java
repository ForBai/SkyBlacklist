package cc.polyfrost.example.config;

import cc.polyfrost.example.SkyBlacklist;
import cc.polyfrost.example.blacklist.BlackList;
import cc.polyfrost.example.blacklist.BlackListConfig;
import cc.polyfrost.example.utils.ChatUtils;
import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.annotations.Button;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.data.OptionSize;

import java.awt.*;
import java.io.IOException;

public class MainConfig extends Config {

    //enable/disable blacklist
    @Switch(name = "Enable Blacklist", description = "Enable the blacklist", category = "Blacklist", subcategory = "Blacklist", size = OptionSize.DUAL)
    public static boolean enableBlacklist = true;

    @Dropdown(name = "Notify Mode", description = "Choose the notify mode", category = "Blacklist", subcategory = "Notify", size = OptionSize.DUAL, options = {"Chat", "Notification", "Both"})
    public static int notifyMode = 1;

    @Switch(name = "Play Sound", description = "Play a sound when a player is blacklisted", category = "Blacklist", subcategory = "Notify", size = OptionSize.DUAL)
    public static boolean playSound = true;

    //enable/disable notify on join Lobby
    @Switch(name = "Enable Notify on Join Lobby", description = "Enable the notify on join lobby", category = "Notify", subcategory = "Notify", size = OptionSize.DUAL)
    public static boolean enableNotifyOnJoinLobby = true;

    //enable/disable notify on join Party
    @Switch(name = "Enable Notify on Join Party", description = "Enable the notify on join party", category = "Notify", subcategory = "Notify", size = OptionSize.DUAL)
    public static boolean enableNotifyOnJoinParty = true;

    //enable/disable notify on join Guild
    //@Switch(name = "Enable Notify on Join Guild", description = "Enable the notify on join guild", category = "Notify", subcategory = "Notify", size = OptionSize.DUAL)
    //public static boolean enableNotifyOnJoinGuild = true;

    //enable/disable auto kick on join Party
    @Switch(name = "Enable Auto Kick on Join Party", description = "Enable the auto kick on join party", category = "Auto Kick", subcategory = "Auto Kick", size = OptionSize.DUAL)
    public static boolean enableAutoKickOnJoinParty = true;

    //enable/disable auto kick on join Guild
    //@Switch(name = "Enable Auto Kick on Join Guild", description = "Enable the auto kick on join guild", category = "Auto Kick", subcategory = "Auto Kick", size = OptionSize.DUAL)
    //public static boolean enableAutoKickOnJoinGuild = false;

    //enable/disable auto leave on join Party
    @Switch(name = "Enable Auto Leave on Join Party", description = "Enable the auto leave on join party", category = "Auto Leave", subcategory = "Auto Leave", size = OptionSize.DUAL)
    public static boolean enableAutoLeaveOnJoinParty = false;

    //enable/disable auto leave on join Lobby
    @Switch(name = "Enable Auto Leave on Join Lobby", description = "Enable the auto leave on join lobby", category = "Auto Leave", subcategory = "Auto Leave", size = OptionSize.DUAL)
    public static boolean enableAutoLeaveOnJoinLobby = false;

    //inputs to add/remove players to blacklist by uuid/username and reason
    //3 inputs for username and reason also add a button to add/remove player to blacklist
    @Text(name = "Name", description = "Name of the player", category = "Manage", subcategory = "Add", placeholder = "Username", size = OptionSize.DUAL)
    public static String nameAdd = "";

    @Text(name = "Reason", description = "Reason for the player", category = "Manage", subcategory = "Add", placeholder = "Reason", size = OptionSize.DUAL)
    public static String reason = "";

    @Button(name = "Add Player", text = "Add", description = "Add player to blacklist", category = "Manage", subcategory = "Add", size = OptionSize.DUAL)
    public static Runnable addPlayer = () -> {
        //add player to blacklist
        if (nameAdd != null && !nameAdd.isEmpty()) {
            BlackList.addPlayer(nameAdd, reason);
            BlackListConfig.saveToFile();
        } else ChatUtils.sendModMessage("Name is empty");
    };

    @Text(name = "Name", description = "Name of the player", category = "Manage", subcategory = "Remove", placeholder = "Username", size = OptionSize.DUAL)
    public static String nameRemove = "";

    @Button(name = "Remove Player", text = "Remove", description = "Remove player from blacklist", category = "Manage", subcategory = "Remove", size = OptionSize.DUAL)
    public static Runnable removePlayer = () -> {
        //remove player from blacklist
        if (nameRemove != null && !nameRemove.isEmpty()) {
            BlackList.removePlayerName(nameRemove);
            BlackListConfig.saveToFile();
        } else ChatUtils.sendModMessage("Name is empty");
    };

    //input for blacklist file name
    @Text(name = "File Name", description = "Name of the blacklist file", category = "File", subcategory = "Save", placeholder = "blacklist.json", size = OptionSize.DUAL)
    public static String fileNameSave = "blacklist";

    //button to save blacklist to file
    @Button(name = "Save Blacklist", text = "Save", description = "Save blacklist to file", category = "File", subcategory = "Save", size = OptionSize.DUAL)
    public static Runnable saveBlacklist = () -> {
        //save blacklist to file
        if (fileNameSave != null && !fileNameSave.isEmpty())
            BlackListConfig.saveToFile(fileNameSave);
        else ChatUtils.sendModMessage("File name is empty");
    };


    //input for blacklist file name
    @Text(name = "File Name", description = "Name of the blacklist file", category = "File", subcategory = "Load", placeholder = "blacklist.json", size = OptionSize.DUAL)
    public static String fileNameLoad = "blacklist";

    //button to load blacklist from file
    @Button(name = "Load Blacklist", text = "Load", description = "Load blacklist from file", category = "File", subcategory = "Load", size = OptionSize.DUAL)
    public static Runnable loadBlacklist = () -> {
        //load blacklist from file
        if (fileNameLoad != null && !fileNameLoad.isEmpty()) {
            BlackListConfig.loadFromFile(fileNameLoad);

        } else ChatUtils.sendModMessage("File name is empty");

    };

    //reload blacklist from file
    //button to reload blacklist from file
    @Button(name = "Reload Blacklist", text = "Reload", description = "Reload blacklist from file", category = "File", subcategory = "Reload", size = OptionSize.DUAL)
    public static Runnable reloadBlacklist = BlackListConfig::reload;

    //button to open blacklist file
    @Button(name = "Open Blacklist File", text = "Open File", description = "Open blacklist file", category = "File", subcategory = "Open", size = OptionSize.DUAL)
    public static Runnable openBlacklistFile = () -> {
        //open blacklist file
        try {
            Desktop.getDesktop().open(BlackListConfig.blackListFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };

    //button to open blacklist folder
    @Button(name = "Open Blacklist Folder", text = "Open Folder", description = "Open blacklist folder", category = "File", subcategory = "Open", size = OptionSize.DUAL)
    public static Runnable openBlacklistFolder = () -> {
        //open blacklist folder BlackListConfig.blackListFolder
        try {
            Desktop.getDesktop().open(BlackListConfig.blackListFolder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    };

    public MainConfig() {
        super(new Mod(SkyBlacklist.NAME, ModType.HYPIXEL), SkyBlacklist.MODID + ".json");
        initialize();
    }
}

