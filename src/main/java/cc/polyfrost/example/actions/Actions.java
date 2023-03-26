package cc.polyfrost.example.actions;

import cc.polyfrost.example.blacklist.BlackList;
import cc.polyfrost.example.config.MainConfig;
import cc.polyfrost.example.utils.NotifyUtil;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Collection;

import static cc.polyfrost.example.SkyBlacklist.mc;

public class Actions {
    @SubscribeEvent
    public void onChat(net.minecraftforge.client.event.ClientChatReceivedEvent event) {
        if (mc.thePlayer == null || mc.theWorld == null || !MainConfig.enableBlacklist) return;
        String regex = "\\[\\d{2}:\\d{2}\\]";
        String message = StringUtils.stripControlCodes(event.message.getUnformattedText()).replaceFirst(regex, "");
        // "USERNAME joined the party." -> joined party

        if (message.startsWith("Dungeons party") && MainConfig.enableAutoLeaveOnJoinParty) {
            String[] lines = message.split("\n");
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                //the name if before ➜
                String name = line.split("➜")[0].trim();
                System.out.println(name);
                if (BlackList.isBlacklistedByUsernameFast(name)) {
                    NotifyUtil.blackListNotif(name + " is blacklisted and in your Party!");
                    sendMessageAsPlayer("/p leave");
                }
            }
        }
        if (message.endsWith("joined the party.") && (MainConfig.enableAutoKickOnJoinParty || MainConfig.enableNotifyOnJoinParty)) {
            String name = message.split(" ")[0];
            System.out.println(name);
            if (BlackList.isBlacklistedByUsernameFast(name)) {
                NotifyUtil.blackListNotif(name + " is blacklisted and joined your Party!");
                if (MainConfig.enableAutoKickOnJoinParty) sendMessageAsPlayer("/p kick " + name);
            }
        }

    }

    @SubscribeEvent
    public void onTick(TickEvent event) {
        if (mc.thePlayer == null || mc.theWorld == null || !MainConfig.enableNotifyOnJoinLobby || !MainConfig.enableBlacklist)
            return;
        NetHandlerPlayClient netHandler = mc.getNetHandler();
        Collection<NetworkPlayerInfo> playerInfos = netHandler.getPlayerInfoMap();
        //what is following 1.8.9 mapping func_178845_
        playerInfos.stream().filter(player -> player.getResponseTime() > 0 && !player.getGameProfile().getName().startsWith("!")).forEach(player -> {
            String name = player.getGameProfile().getName();
            if (BlackList.isBlacklistedByUsernameFast(name)) {
                NotifyUtil.blackListNotif(name + " is blacklisted!");
                if (MainConfig.enableAutoLeaveOnJoinLobby) {
                    sendMessageAsPlayer("/lobby");
                }
            }
        });
    }


    public static void sendMessageAsPlayer(String message) {
        mc.thePlayer.sendChatMessage(message);
    }
}
