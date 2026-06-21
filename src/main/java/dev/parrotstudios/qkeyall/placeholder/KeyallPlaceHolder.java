package dev.parrotstudios.qkeyall.placeholder;

import dev.parrotstudios.qkeyall.timer.KeyallTimer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class KeyallPlaceHolder extends PlaceholderExpansion {


    @Override
    public @NotNull String getIdentifier() {
        return "qkeyall";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Qssaf";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        if (params.equalsIgnoreCase("time")) {
            return String.format("%02d:%02d", KeyallTimer.getTimerTime() / 60, KeyallTimer.getTimerTime() % 60);
        }
        return null;
    }

}