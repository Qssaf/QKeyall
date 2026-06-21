package dev.parrotstudios.qkeyall.command;


import dev.parrotstudios.qkeyall.KeyallPreset;
import dev.parrotstudios.qkeyall.config.MainConfig;
import org.jetbrains.annotations.NotNull;
import revxrsal.commands.autocomplete.SuggestionProvider;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;
import revxrsal.commands.node.ExecutionContext;

import java.util.Collection;

public final class PresetSuggestions implements SuggestionProvider<BukkitCommandActor> {

    @Override
    public @NotNull Collection<String> getSuggestions(@NotNull ExecutionContext<BukkitCommandActor> context) {
        return MainConfig.getInstance().getKeyallPresets().stream().map(KeyallPreset::getName).toList();
    }
}
