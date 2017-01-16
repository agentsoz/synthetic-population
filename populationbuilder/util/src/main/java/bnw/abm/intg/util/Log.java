package bnw.abm.intg.util;

import org.slf4j.LoggerFactory;

import bnw.abm.intg.util.GlobalConstants.EXITCODE;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;

/**
 * 
 * @author Bhagya N. Wickramasinghe - Taken from Dhirendra Singh's LATCH code
 *
 */
public class Log {

    private static Logger logger;

    public static void info(String format, Object... args) {
        logger.info(format, args);
    }

    /**
     * See http://slf4j.org/faq.html#logging_performance for how to improve logging performance by upto 30x by adjusting the arguments
     * 
     * @param format
     * @param args
     */
    public static void trace(String format, Object... args) {
        logger.trace(format, args);
    }

    public static void warn(String s) {
        logger.warn(s);
    }

    public static void debug(String format, Object... args) {
        logger.debug(format, args);
    }

    public static void error(String s) {
        logger.error(s);
    }

    public static void error(String msg, Throwable thrown) {
        logger.error(msg, thrown);
    }

    public static void errorAndExit(String msg, EXITCODE exitCode) {
        logger.error(msg);
        System.out.println("EXIT CODE: " + exitCode.getCode() + " " + exitCode.getMsg());
        System.exit(exitCode.getCode());
    }

    public static void errorAndExit(String msg, Throwable thrown, EXITCODE exitCode) {
        logger.error(msg, thrown);
        System.out.println("EXIT CODE: " + exitCode.getCode() + " " + exitCode.getMsg());
        System.exit(exitCode.getCode());
    }

    public static boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public static boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    /**
     * Creates a logger and stores in an private static variable. So no need to assign returned Logger instance to a variable. Access logging
     * functions by calling Log.function()
     * 
     * @param name
     *            ID for the logger
     * @param level
     *            Log level
     * @param file
     *            Log file path
     * @return Logger instance
     */
    public static Logger createLogger(String name, Level level, String file) {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        PatternLayoutEncoder ple = new PatternLayoutEncoder();
        ple.setPattern("%date %level %logger{10} %msg%n");
        ple.setContext(lc);
        ple.start();
        FileAppender<ILoggingEvent> fileAppender = new FileAppender<ILoggingEvent>();
        fileAppender.setFile(file);
        fileAppender.setEncoder(ple);
        fileAppender.setAppend(false);
        fileAppender.setContext(lc);
        fileAppender.start();
        logger = (Logger) LoggerFactory.getLogger(name);
        logger.detachAndStopAllAppenders(); // detach console (doesn't seem to work)
        logger.addAppender(fileAppender); // attach file appender
        logger.setLevel(level);
        logger.setAdditive(true); // set to true if root should log too

        return logger;
    }

}
