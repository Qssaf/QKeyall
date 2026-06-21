package dev.parrotstudios.qkeyall;

import lombok.Getter;
import su.nightexpress.excellentcrates.key.CrateKey;

import java.util.List;
import java.util.Map;

public class KeyallPreset {
    @Getter
    private String name;
    @Getter
    private Map<CrateKey,Integer> keys;

    private KeyallPreset(String name, List<String> keyList) {
        this.name = name;
        keyList.forEach(key -> {
            this.keys.put(QKeyall.getKeyManager().getKeyById(key.split(":")[0]), Integer.parseInt(key.split(":")[1]));
        });
    }

    private KeyallPreset() {
    }

    public static KeyallPreset build(String name, List<String> keyList) {
        return new KeyallPreset(name, keyList);
    }
}
