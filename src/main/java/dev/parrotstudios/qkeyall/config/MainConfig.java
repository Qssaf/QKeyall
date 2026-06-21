package dev.parrotstudios.qkeyall.config;

import dev.parrotstudios.qkeyall.QKeyall;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import su.nightexpress.excellentcrates.key.CrateKey;
import su.nightexpress.excellentcrates.key.KeyManager;


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

    private String crateKeyName;
    private CrateKey crateKey;


    public static MainConfig getInstance() {
        return mainConfig;
    }

    public void load() {
        plugin = QKeyall.getInstance();
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
        KeyallTime = config.getLong("KeyallTime",120L);
        crateKeyName = config.getString("CrateKeyName");
        if(crateKeyName == null ||  crateKeyName.isEmpty()) {
             return;
        }
        crateKey = QKeyall.getKeyManager().getKeyById(crateKeyName);

    }

}
