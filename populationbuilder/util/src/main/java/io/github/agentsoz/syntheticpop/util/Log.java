package io.github.agentsoz.syntheticpop.util;

/*-
 * #%L
 * Synthetic Population Construction for Australia
 * %%
 * Copyright (C) 2016 - 2019 by its authors. See AUTHORS file.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;

/**
 * 
 * @author Bhagya N. Wickramasinghe - Taken frm Dhirendra Singh's LATCH code
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

    public static void errorAndExit(String msg, GlobalConstants.ExitCode exitCode) {
        logger.error(msg);
        System.out.println("EXIT CODE: " + exitCode.getCode() + " " + exitCode.getMsg());
        System.exit(exitCode.getCode());
    }

    public static void errorAndExit(String msg, Throwable thrown, GlobalConstants.ExitCode exitCode) {
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

    /**
     * Creates a logger and stores in an private static variable. So no need to assign returned Logger instance to a variable. Access logging
     * functions by calling Log.function(). Intended to be used with -Dlogback system property e.g. java -Dlogback.debug=true main
     *
     * @param name
     *            ID for the logger
     * @param file
     *            Log file path
     * @return Logger instance
     */
    public static Logger createLogger(String name, String file){
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        PatternLayoutEncoder ple = new PatternLayoutEncoder();
        ple.setPattern("%date %level %logger{10} %msg%n");
        ple.setContext(lc);
        ple.start();
        FileAppender<ILoggingEvent> fileAppender = new FileAppender<>();
        fileAppender.setFile(file);
        fileAppender.setEncoder(ple);
        fileAppender.setAppend(false);
        fileAppender.setContext(lc);
        fileAppender.start();
        logger = (Logger) LoggerFactory.getLogger(name);
        logger.detachAndStopAllAppenders(); // detach console (doesn't seem to work)
        logger.addAppender(fileAppender); // attach file appender
        logger.setAdditive(true); // set to true if root should log too
        return logger;

    }

}
