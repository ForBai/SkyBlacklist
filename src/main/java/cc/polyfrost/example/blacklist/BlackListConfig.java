package cc.polyfrost.example.blacklist;

import com.google.gson.GsonBuilder;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;

import static cc.polyfrost.example.SkyBlacklist.mc;

public class BlackListConfig {
    public static File blackListFolder = new File(mc.mcDataDir, "/config/SkyBlacklist/");
    public static File blackListFile = new File(blackListFolder, "blacklist.json");


    public static void init() {
        if (!blackListFolder.exists()) {
            blackListFolder.mkdirs();
        }
        if (!blackListFile.exists()) {
            try {
                blackListFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadFromFile(File file) {
        if (!file.exists()){
            return;
        }
        String json = "";
        //load json file
        try {
            json = new String(Files.readAllBytes(file.toPath()));
            //parse json file
            GsonBuilder builder = new GsonBuilder();
            HashMap<String, String[]> blackList = builder.create().fromJson(json, HashMap.class);
            BlackList.setBlackList(blackList);
            blackListFile = file;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void loadFromFile() {
        loadFromFile(blackListFile);
    }

    public static void loadFromFile(String name) {
        loadFromFile(new File(blackListFolder, name + ".json"));
    }


    public static void saveToFile(File file) {
        HashMap<String, String[]> blackList = BlackList.getBlackList();
        //save it as a json file
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        String json = builder.create().toJson(blackList);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //write to file
        try {
            java.io.FileWriter writer = new java.io.FileWriter(file);
            writer.write(json);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveToFile(String name) {
        saveToFile(new File(blackListFolder, name + ".json"));
    }

    public static void saveToFile() {
        saveToFile(blackListFile);
    }


    public static void reload() {
        saveToFile();
        loadFromFile();
    }
}
