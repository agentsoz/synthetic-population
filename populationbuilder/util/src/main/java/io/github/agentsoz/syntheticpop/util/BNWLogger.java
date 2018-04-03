package io.github.agentsoz.syntheticpop.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Deprecated
/**
 * This class is being replaced by Log
 *
 * @author Bhagya N. Wickramasinghe
 *
 */
public class BNWLogger {
    private static FileHandler fh = null;
    private static String thispackage = null;
    private static Map<String, Integer> logOnceMap = new HashMap<>();

    @Deprecated
    /**
     * Use Log.create() method
     * @param thispackage
     * @return java.util.Logger instance
     */
    public static Logger getLogger(String thispackage) {

        /*
         * System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s |%1$tF %1$tT %2$s| %5$s%6$s%n" );
         */
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s | %5$s%6$s%n");
        BNWLogger.thispackage = thispackage;
        Logger logger = Logger.getLogger(thispackage);
        if (fh == null) {
            try {
                fh = new FileHandler(thispackage + ".log", false);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.addHandler(fh);
        }

        logger.setLevel(Level.ALL);
        fh.setFormatter(new SimpleFormatter());

        return logger;
    }

    @Deprecated
    /**
     * Use Log class instead. Log.create() will create a new Logging instance
     * @return java.util.Logger instance
     */
    public static Logger getLogger() {
        return BNWLogger.getLogger(thispackage);
    }

    @Deprecated
    public static void logonce(Level level, String msg) {
        if (logOnceMap.containsKey(msg)) {
            int count = logOnceMap.get(msg) + 1;
            logOnceMap.put(msg, count);
        } else {
            BNWLogger.getLogger().log(level, msg);
            logOnceMap.put(msg, 1);
        }

    }

    @Deprecated
    /**
     * Use Log class instead
     * @param level Log level
     * @param message Log message
     * @param thrown Log exception
     * @param exitCode Exit code
     */
    public void logAndExit(Level level, String message, Throwable thrown, GlobalConstants.ExitCode exitCode) {
        getLogger().log(level, message, thrown);
        System.exit(exitCode.getCode());
    }

    @Deprecated
    /**
     * Use Log class instead
     * @param level Log level
     * @param message Log message
     * @param exitCode Exit code
     */
    public void logAndExit(Level level, String message, GlobalConstants.ExitCode exitCode) {
        getLogger().log(level, message);
        System.exit(exitCode.getCode());
    }
}
