package dev.parrotstudios.qkeyall;

import dev.parrotstudios.qkeyall.config.MainConfig;
import dev.parrotstudios.qkeyall.timer.KeyallTimer;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import su.nightexpress.excellentcrates.CratesAPI;
import su.nightexpress.excellentcrates.key.KeyManager;

public final class QKeyall extends JavaPlugin {

    @Getter
    private static QKeyall instance;

    @Getter
    private static KeyManager keyManager;

    @Override
    public void onEnable() {
        keyManager = CratesAPI.getKeyManager();
        MainConfig.getInstance().load();
        KeyallTimer.StartUp();
    }

    @Override
    public void onDisable() {
       KeyallTimer.stop();
    }

    public void Reload(){
        MainConfig.getInstance().load();
        KeyallTimer.StartUp();
    }
}
