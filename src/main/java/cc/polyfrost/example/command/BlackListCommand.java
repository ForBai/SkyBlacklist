package cc.polyfrost.example.command;

import cc.polyfrost.example.SkyBlacklist;
import cc.polyfrost.example.blacklist.BlackList;
import cc.polyfrost.example.blacklist.BlackListConfig;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Greedy;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import cc.polyfrost.oneconfig.utils.commands.annotations.SubCommand;

/**
 * An example command implementing the Command api of OneConfig.
 * Registered in SkyBlacklist.java with `CommandManager.INSTANCE.registerCommand(new BlackListCommand());`
 *
 * @see Command
 * @see Main
 * @see SkyBlacklist
 */
@Command(value = SkyBlacklist.MODID, description = "Access the " + SkyBlacklist.NAME + " GUI.")
public class BlackListCommand {
    @Main
    private void handle() {
        SkyBlacklist.config.openGui();
    }

    @SubCommand()
    private void add(String username, @Greedy String reason) {
        BlackList.addPlayer(username, reason);
    }

    @SubCommand()
    private void remove(String username) {
        BlackList.removePlayerName(username);
    }

    @SubCommand()
    private void reload() {
        BlackListConfig.reload();
    }

}