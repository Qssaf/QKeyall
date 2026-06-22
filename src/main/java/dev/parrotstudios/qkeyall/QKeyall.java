package dev.parrotstudios.qkeyall;

import dev.parrotstudios.qkeyall.command.KeyallCommand;
import dev.parrotstudios.qkeyall.config.MainConfig;
import dev.parrotstudios.qkeyall.placeholder.KeyallPlaceHolder;
import dev.parrotstudios.qkeyall.timer.KeyallTimer;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.Lamp;
import revxrsal.commands.bukkit.BukkitLamp;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;

public final class QKeyall extends JavaPlugin {

    @Getter
    private static QKeyall instance;


    @Override
    public void onEnable() {
        instance = this;
        Lamp<BukkitCommandActor> lamp = BukkitLamp.builder(this).build();
        lamp.register(new KeyallCommand());
        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new KeyallPlaceHolder().register();
        }
        MainConfig.getInstance().load();
        KeyallTimer.StartUp();
    }

    @Override
    public void onDisable() {
       KeyallTimer.stop();
    }

    public void reload() {
        MainConfig.getInstance().load();
        KeyallTimer.StartUp();
    }
}
