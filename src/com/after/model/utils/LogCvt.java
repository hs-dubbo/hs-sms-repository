package com.after.model.utils;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import java.io.File;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

	public class LogCvt {
		public static final String NEWLINE = System.getProperty("line.separator","\n");
		private static final Logger logger = LoggerFactory.getLogger(LogCvt.class);

		public Logger getLogger() {
			return logger;
		}

		public static void info(String paramString) {
			logger.info(paramString);
		}

		public static void info(String paramString, Throwable paramThrowable) {
			logger.info(paramString, paramThrowable);
		}

		public static void debug(String paramString) {
			logger.debug(paramString);
		}

		public static void debug(String paramString, Throwable paramThrowable) {
			logger.debug(paramString, paramThrowable);
		}

		public static void warn(String paramString) {
			logger.warn(paramString);
		}

		public static void warn(String paramString, Throwable paramThrowable) {
			logger.warn(paramString, paramThrowable);
		}

		public static void error(String paramString) {
			logger.error(paramString);
		}

		public static void error(String paramString, Throwable paramThrowable) {
			logger.error(paramString, paramThrowable);
		}

		public static void trace(String paramString) {
			logger.trace(paramString);
		}

		public static void trace(String paramString, Throwable paramThrowable) {
			logger.trace(paramString, paramThrowable);
		}

		static {
			LoggerContext localLoggerContext = (LoggerContext) LoggerFactory
					.getILoggerFactory();
			JoranConfigurator localJoranConfigurator = new JoranConfigurator();
			localJoranConfigurator.setContext(localLoggerContext);
			localLoggerContext.reset();
			try {
				String str1 = "logConfig.xml";
				URL localURL = Thread.currentThread().getContextClassLoader()
						.getResource(str1);
				if (null != localURL) {
					localJoranConfigurator.doConfigure(localURL);
					logger.info("===================================resource logConfig.xml Initing!===================================");
				} else {
					String str2 = "./config/" + str1;
					localJoranConfigurator.doConfigure(new File(str2));
					logger.info("===================================config logConfig.xml Initing!===================================");
				}
			} catch (JoranException localJoranException) {
				logger.error(localJoranException.getMessage(), localJoranException);
			}
			StatusPrinter.print(localLoggerContext.getStatusManager());
		}
		
		public static void main(String[] args) {
			LogCvt.debug("");
		}
}
