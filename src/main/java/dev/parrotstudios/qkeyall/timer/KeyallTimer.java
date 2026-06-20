package dev.parrotstudios.qkeyall.timer;

import dev.parrotstudios.qkeyall.QKeyall;
import dev.parrotstudios.qkeyall.config.MainConfig;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import lombok.Getter;
import su.nightexpress.excellentcrates.key.CrateKey;

public class KeyallTimer {

    @Getter
    private static ScheduledTask task = null;

    private static final MainConfig config = MainConfig.getInstance();
    private static QKeyall plugin;
    private static long timer;
    private static CrateKey crateKey;

    @Getter
    private static TimerStatus timerStatus = TimerStatus.OFFLINE;

    public static enum TimerStatus {
        RUNNING,
        OFFLINE
    }

    public static void StartUp(){
        plugin = QKeyall.getInstance();
        if(task != null){
            task.cancel();
            timerStatus = TimerStatus.OFFLINE;
        }
        if(config.getCrateKey() == null){
            plugin.getLogger().warning("CrateKey is not set in the config. KeyallTimer will not start.");
            return;
        }
        crateKey = config.getCrateKey();
        timer = config.getKeyallTime();
        timerStatus = TimerStatus.RUNNING;
        task = plugin.getServer().getGlobalRegionScheduler().runAtFixedRate(plugin, (scheduledTask) -> {
            if(timer<=0){
                timer = config.getKeyallTime();
                plugin.getServer().getOnlinePlayers().forEach(player -> {
                    QKeyall.getKeyManager().giveKey(player, crateKey, 1);
                });
                return;
            }
            timer--;
        }, 0L, 20L);
    }

    public static void stop(){
        if(task != null){
            task.cancel();
        }
    }

}
