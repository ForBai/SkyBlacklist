package cc.polyfrost.example.blacklist;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class BlackList {
    private static HashMap<String, String[]> blackList = new HashMap<String, String[]>(); // <uuid, username[0] reason[1]>

    //generate everything i could need to add a player by uuid/username(get the uuid by using https://api.mojang.com/users/profiles/minecraft/<username>), remove a player by uuid/username, get uuid by username, get username by uuid, get reason by uuid, get reason by username check if player is blacklisted by uuid, check if player is blacklisted by username


    public static void addPlayer(String uuid, String username, String reason) {
        String[] userInfo = {username, reason};
        blackList.put(uuid, userInfo);
    }

    public static void addPlayer(String username, String reason) {
        String uuid = getUUIDByUsername(username);
        String[] userInfo = {username, reason};
        blackList.put(uuid, userInfo);
    }

    public static void removePlayerName(String username) {
        String uuid = getUUIDByUsername(username);
        blackList.remove(uuid);
    }

    public static void removePlayer(String uuid) {
        blackList.remove(uuid);
    }

    public static String getUUIDByUsername(String username) {
        try {
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + username);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            String[] responseParts = response.toString().split("\"");
            return responseParts[3];

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getUsernameByUUID(String uuid) {
        try {
            URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            String[] responseParts = response.toString().split("\"");
            return responseParts[3];

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getReasonByUUID(String uuid) {
        String[] userInfo = blackList.get(uuid);
        if (userInfo != null) {
            return userInfo[1];
        } else {
            return null;
        }
    }

    public static String getReasonByUsername(String username) {
        String uuid = getUUIDByUsername(username);
        String[] userInfo = blackList.get(uuid);
        if (userInfo != null) {
            return userInfo[1];
        } else {
            return null;
        }
    }

    public static boolean isBlacklistedByUUID(String uuid) {
        return blackList.containsKey(uuid);
    }

    public static boolean isBlacklistedByUsername(String username) {
        String uuid = getUUIDByUsername(username);
        return blackList.containsKey(uuid);
    }

    public static HashMap<String, String[]> getBlackList() {
        return blackList;
    }

    public static void setBlackList(HashMap<String, String[]> blackList) {
        BlackList.blackList = blackList;
    }
}
