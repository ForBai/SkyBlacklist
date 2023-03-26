package cc.polyfrost.example;

import cc.polyfrost.example.command.ExampleCommand;
import cc.polyfrost.example.config.MainConfig;
import cc.polyfrost.oneconfig.events.event.InitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * The entrypoint of the Example Mod that initializes it.
 *
 * @see Mod
 * @see InitializationEvent
 */
@Mod(modid = SkyBlacklist.MODID, name = SkyBlacklist.NAME, version = SkyBlacklist.VERSION)
public class SkyBlacklist {
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";
    // Sets the variables from `gradle.properties`. See the `blossom` config in `build.gradle.kts`.
    @Mod.Instance(MODID)
    public static SkyBlacklist INSTANCE; // Adds the instance of the mod, so we can access other variables.
    public static MainConfig config;

    public static Minecraft mc = Minecraft.getMinecraft();

    public static String blackListFolder = mc.mcDataDir + "/config/SkyBlacklist/";

    // Register the config and commands.
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        config = new MainConfig();
        CommandManager.INSTANCE.registerCommand(new ExampleCommand());
        System.out.println(blackListFolder);
    }
}
