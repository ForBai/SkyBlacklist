package cc.polyfrost.example.utils;

import cc.polyfrost.example.SkyBlacklist;
import cc.polyfrost.example.config.MainConfig;
import cc.polyfrost.oneconfig.renderer.asset.Icon;
import cc.polyfrost.oneconfig.utils.Notifications;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

public class NotifyUtil {
    public static void blackListNotif(String message) {
        if (MainConfig.playSound) {
            playSound("random.orb", 1.0f);
        }
        switch (MainConfig.notifyMode) {
            case 0:
                ChatUtils.sendModMessage(message);
                break;
            case 1:
                //get the icon out of the resources folder(assets/skyblacklist/block.png)
                Icon icon = new Icon(Objects.requireNonNull(SkyBlacklist.class.getResource("/assets/skyblacklist/block.png")).getPath());
                Notifications.INSTANCE.send(SkyBlacklist.NAME, message, icon, 2000);
                break;
            case 2:
                //get the icon out of the resources folder(assets/skyblacklist/block.png)
                ChatUtils.sendModMessage(message);
                Icon icon2 = new Icon(Objects.requireNonNull(SkyBlacklist.class.getResource("/assets/skyblacklist/block.png")).getPath());
                Notifications.INSTANCE.send(SkyBlacklist.NAME, message, icon2, 2000);
                break;
        }
    }

    private static void playSound(String soundName, float pitch) {
        SoundHandler handler = Minecraft.getMinecraft().getSoundHandler();
        PositionedSoundRecord psr = PositionedSoundRecord.create(new ResourceLocation(soundName), pitch);
        handler.playSound(psr);
    }
}
