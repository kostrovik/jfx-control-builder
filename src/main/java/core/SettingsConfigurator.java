package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * project: jfx-control-builder
 * author:  kostrovik
 * date:    27/06/2018
 * github:  https://github.com/kostrovik/jfx-control-builder
 */
public final class SettingsConfigurator {
    private static Logger logger = LogManager.getLogger(SettingsConfigurator.class);

    private static volatile SettingsConfigurator configurator;
    private static Properties config;
    private final static String defaultConfigFilePath = "configurations/control_icons.properties";

    private SettingsConfigurator(Properties config) {
        SettingsConfigurator.config = parseConfig(config);
    }

    public static SettingsConfigurator getInstance() {
        return getInstance(new Properties());
    }

    public static SettingsConfigurator getInstance(Properties config) {
        if (configurator == null) {
            synchronized (SettingsConfigurator.class) {
                if (configurator == null) {
                    configurator = new SettingsConfigurator(config);
                }
            }
        }
        return configurator;
    }

    public Properties getConfig() {
        return  (Properties) config.clone();
    }

    private Properties parseConfig(Properties customSettings) {
        Properties result = getDefaultConfig();

        for (Object key: customSettings.keySet()) {
            if (result.containsKey(key)) {
                result.setProperty((String) key, customSettings.getProperty((String) key));
            }
        }

        return result;
    }

    private Properties getDefaultConfig() {
        Properties result = new Properties();

        try (InputStream inputStream = SettingsConfigurator.class.getClassLoader().getResourceAsStream(defaultConfigFilePath)) {
            result.load(inputStream);
        } catch (FileNotFoundException error) {
            System.out.println("Не найден файл конфигурации по умолчанию");
            logger.error("Не найден файл конфигурации по умолчанию", error);
        } catch (IOException error) {
            System.out.println("Не возможно загрузить настройки умолчанию");
            logger.error("Не возможно загрузить настройки умолчанию", error);
        }

        return result;
    }
}
