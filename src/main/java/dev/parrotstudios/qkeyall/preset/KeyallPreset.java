package dev.parrotstudios.qkeyall.preset;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class KeyallPreset {
    @Getter
    private String name;
    @Getter
    private final List<String> commands = new ArrayList<>();

    private KeyallPreset(String name, List<String> commandsList) {
        this.name = name;
        this.commands.addAll(commandsList);
    }

    private KeyallPreset() {
    }

    public static KeyallPreset build(String name, List<String> keyList) {
        return new KeyallPreset(name, keyList);
    }
}
