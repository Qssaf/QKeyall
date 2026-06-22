package dev.parrotstudios.qkeyall.command;

import dev.parrotstudios.qkeyall.preset.KeyallPreset;
import dev.parrotstudios.qkeyall.QKeyall;
import dev.parrotstudios.qkeyall.config.MainConfig;
import me.clip.placeholderapi.PlaceholderAPI;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.annotation.SuggestWith;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;

@Command({"qkeyall","qka","keyall"})
public class KeyallCommand {


    @Subcommand("reload")
    public void reloadPlugin(BukkitCommandActor commandActor) {
        QKeyall.getInstance().reload();
        commandActor.sender().sendMessage("Plugin reloaded!");
    }

    @Subcommand("time")
    public void timeCommand(BukkitCommandActor commandActor) {
        String message = PlaceholderAPI.setPlaceholders(commandActor.asPlayer(),"Time until next keyall: %qkeyall_time%");
        commandActor.reply(message);

    }

    @Subcommand("preset")
    public void presetCommand(BukkitCommandActor commandActor, @SuggestWith(PresetSuggestions.class) String preset) {
        KeyallPreset keyallPreset = MainConfig.getInstance().getKeyallPresets().stream().filter(p -> p.getName().equalsIgnoreCase(preset)).findFirst().orElse(null);
        if(keyallPreset == null) {
            commandActor.reply("Preset not found!");
            return;
        }

        commandActor.reply("Keyall started with preset: " + keyallPreset.getName());
    }

}
