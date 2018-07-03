package controls.builders;

import core.SettingsConfigurator;
import icons.SolidIcons;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * project: jfx-control-builder
 * author:  kostrovik
 * date:    30/06/2018
 * github:  https://github.com/kostrovik/jfx-control-builder
 */
public class ButtonBuilder {
    private SettingsConfigurator config;

    public ButtonBuilder() {
        this.config = SettingsConfigurator.getInstance();
    }

    public Button createButton(String buttonLabel) {
        Button button = new Button();
        setLabel(button, buttonLabel);

        return button;
    }

    public Button createButton(SolidIcons buttonIcon) {
        Button button = new Button();
        setIcon(button, buttonIcon);

        return button;
    }

    public Button createButton(SolidIcons buttonIcon, String buttonLabel) {
        Button button = new Button();
        setIcon(button, buttonIcon);
        setLabel(button, buttonLabel);

        return button;
    }

    private void setLabel(Button button, String buttonLabel) {
        button.setText(buttonLabel);
    }

    private void setIcon(Button button, SolidIcons buttonIcon) {
        if (config.isUseIconsFont()) {
            Text icon = new Text(buttonIcon.getSymbol());
            icon.setFont(buttonIcon.getFont());
            button.setGraphic(icon);
        }
    }
}
