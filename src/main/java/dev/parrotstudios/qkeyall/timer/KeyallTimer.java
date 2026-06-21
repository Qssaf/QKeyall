package dev.parrotstudios.qkeyall.timer;

import dev.parrotstudios.qkeyall.QKeyall;
import dev.parrotstudios.qkeyall.config.MainConfig;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import lombok.Getter;
import su.nightexpress.excellentcrates.key.CrateKey;

import java.util.concurrent.TimeUnit;

public class KeyallTimer {

    @Getter
    private static ScheduledTask task = null;

    private static final MainConfig config = MainConfig.getInstance();
    private static QKeyall plugin;
    @Getter
    private static long timerTime;
    private static CrateKey crateKey;

    @Getter
    private static TimerStatus timerStatus = TimerStatus.OFFLINE;

    public static enum TimerStatus {
        RUNNING,
        OFFLINE
    }

    public static void StartUp() {
        plugin = QKeyall.getInstance();
        if(task != null) {
            task.cancel();
            timerStatus = TimerStatus.OFFLINE;
        }
        if(config.getCrateKey() == null) {
            plugin.getLogger().warning("CrateKey is not set in the config. KeyallTimer will not start.");
            return;
        }
        crateKey = config.getCrateKey();
        timerTime = config.getKeyallTime();
        timerStatus = TimerStatus.RUNNING;
        task = plugin.getServer().getAsyncScheduler().runAtFixedRate(plugin, (scheduledTask) -> {
            if(timerTime <=0) {
                timerTime = config.getKeyallTime();
                plugin.getServer().getOnlinePlayers().forEach(player
                        -> player.getScheduler().run(plugin, (playerTask)
                        -> QKeyall.getKeyManager().giveKey(player, crateKey, 1), null));
                return;
            }
            timerTime--;
        }, 0L, 1L, TimeUnit.SECONDS);
    }

    public static void stop() {
        if(task != null) {
            task.cancel();
        }
    }

}
