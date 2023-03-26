package cc.polyfrost.example.command;

import cc.polyfrost.example.SkyBlacklist;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;

/**
 * An example command implementing the Command api of OneConfig.
 * Registered in SkyBlacklist.java with `CommandManager.INSTANCE.registerCommand(new ExampleCommand());`
 *
 * @see Command
 * @see Main
 * @see SkyBlacklist
 */
@Command(value = SkyBlacklist.MODID, description = "Access the " + SkyBlacklist.NAME + " GUI.")
public class ExampleCommand {
    @Main
    private void handle() {
        SkyBlacklist.config.openGui();
    }
}