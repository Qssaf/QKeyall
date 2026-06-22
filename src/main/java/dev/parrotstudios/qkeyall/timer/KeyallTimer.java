package dev.parrotstudios.qkeyall.timer;

import dev.parrotstudios.qkeyall.QKeyall;
import dev.parrotstudios.qkeyall.config.MainConfig;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import lombok.Getter;

import java.util.List;

public class KeyallTimer {

    @Getter
    private static ScheduledTask task = null;

    private static final MainConfig config = MainConfig.getInstance();
    private static QKeyall plugin;
    @Getter
    private static long timerTime;

    private static List<String> timedCommands;

    @Getter
    private static TimerStatus timerStatus = TimerStatus.OFFLINE;

    public enum TimerStatus {
        RUNNING,
        OFFLINE
    }

    public static void StartUp() {
        plugin = QKeyall.getInstance();
        if(task != null) {
            task.cancel();
            timerStatus = TimerStatus.OFFLINE;
        }
        if(config.getTimedKeyallCommands().isEmpty()) {
            plugin.getLogger().warning("TimedKeyallCommands is not set in the config. KeyallTimer will not start.");
            return;
        }
        timedCommands = config.getTimedKeyallCommands();
        timerTime = config.getKeyallTime();
        timerStatus = TimerStatus.RUNNING;

        task = plugin.getServer().getGlobalRegionScheduler().runAtFixedRate(plugin, (scheduledTask) -> {
            if(timerTime <=0) {
                timerTime = config.getKeyallTime();
                timedCommands.forEach(command ->
                        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), command));
                return;
            }
            timerTime--;
        }, 0L, 20L);
    }

    public static void stop() {
        if(task != null) {
            task.cancel();
        }
    }

}
