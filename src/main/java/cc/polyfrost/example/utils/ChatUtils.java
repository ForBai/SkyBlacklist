package cc.polyfrost.example.utils;

import net.minecraft.util.ChatComponentText;

import static cc.polyfrost.example.SkyBlacklist.mc;

public class ChatUtils {
    public static void sendModMessage(String message) {
        if (mc.thePlayer == null || mc.theWorld == null) return;
        //§6§lSky§r§eBlacklist §6§l»§r + message
        mc.thePlayer.addChatMessage(new ChatComponentText("§6§lSky§r§eBlacklist §6§l»§r " + message));
    }
}
