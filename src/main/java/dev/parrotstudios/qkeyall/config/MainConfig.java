package dev.parrotstudios.qkeyall.config;

import dev.parrotstudios.qkeyall.preset.KeyallPreset;
import dev.parrotstudios.qkeyall.QKeyall;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;


import java.util.ArrayList;
import java.util.List;


@Getter
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class MainConfig {

    private FileConfiguration config;

    private Long KeyallTime;

    @Getter(AccessLevel.NONE)
    private static QKeyall plugin;
    @Getter(AccessLevel.NONE)
    private static final MainConfig mainConfig = new MainConfig();

    private MainConfig() {}

    private List<String> timedKeyallCommands;
    private final List<KeyallPreset> keyallPresets = new ArrayList<>();


    public static MainConfig getInstance() {
        return mainConfig;
    }

    public void load() {
        plugin = QKeyall.getInstance();
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
        KeyallTime = config.getLong("KeyallTime",120L);
        timedKeyallCommands = config.getStringList("TimedKeyallCommands");
        ConfigurationSection section =  config.getConfigurationSection("KeyallPresets");
        if(section == null) return;
        for(String presetName : section.getKeys(false)) {
            keyallPresets.add(KeyallPreset.build(presetName, config.getStringList("KeyallPresets." + presetName)));
        }

    }

}
