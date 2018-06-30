package icons;

import core.SettingsConfigurator;
import javafx.scene.text.Font;

import java.util.Properties;

/**
 * project: jfx-control-builder
 * author:  kostrovik
 * date:    30/06/2018
 * github:  https://github.com/kostrovik/jfx-control-builder
 */
public enum SolidIcons {
    CARET_DOWN("\uf0d7");

    private final String character;
    private final Font font;

    private SolidIcons(String character) {
        this.character = character;
        this.font = prepareFont();
    }

    public String getSymbol() {
        return character;
    }

    public Font getFont() {
        return font;
    }

    private Font prepareFont() {
        Properties config = SettingsConfigurator.getInstance().getConfig();
        return Font.loadFont(config.getProperty("icons.font.path"), Double.parseDouble(config.getProperty("icons.font.size")));
    }
}
