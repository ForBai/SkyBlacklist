package cc.polyfrost.example.hud;

import cc.polyfrost.example.config.MainConfig;
import cc.polyfrost.oneconfig.hud.SingleTextHud;

/**
 * An example OneConfig HUD that is started in the config and displays text.
 *
 * @see MainConfig#hud
 */
public class TestHud extends SingleTextHud {
    public TestHud() {
        super("Test", true);
    }

    @Override
    public String getText(boolean example) {
        return "I'm an example HUD";
    }
}
