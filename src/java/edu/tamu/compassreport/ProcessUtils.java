package edu.tamu.compassreport;

/**
 * Created by datamaskinaggie on 2/8/17.
 */
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessUtils {

    private final static Logger LOG = LoggerFactory.getLogger(ProcessUtils.class);

    public static void runSystemCommand(SystemCommand systemCommand) {

        CommandLine commandLine = CommandLine.parse(systemCommand.getCommand(), systemCommand.getParams());
        String commandRun = commandLine.toString();
        systemCommand.setCommandRun(commandRun);

        DefaultExecutor executor = new DefaultExecutor();
        int timeoutInSeconds = systemCommand.getTimeoutInSeconds();
        ExecuteWatchdog watchdog = new ExecuteWatchdog((timeoutInSeconds > 0) ? timeoutInSeconds * 1000 : -1);
        executor.setWatchdog(watchdog);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(baos);
        executor.setStreamHandler(streamHandler);

        File workingDirectory = systemCommand.getWorkingDirectory();
        executor.setWorkingDirectory(workingDirectory != null ? workingDirectory
                : TempDirUtils.getTempDirectory());
        systemCommand.setCommandRun(commandRun);

        int exitValue = -1;
        try {
            LOG.debug("Running command: " + commandRun);
            exitValue = executor.execute(commandLine);
        }
        catch (Exception e) {
            LOG.error("Command failed: " + commandRun, e);
            systemCommand.setException(e);
        }
        finally {
            systemCommand.setExitValue(exitValue);
            boolean failed = executor.isFailure(exitValue);
            systemCommand.setFailed(failed);
            boolean killed = failed && watchdog.killedProcess();
            systemCommand.setKilled(killed);
            systemCommand.setOutput(baos.toString());
        }

        IOUtils.closeQuietly(baos);
    }

}
