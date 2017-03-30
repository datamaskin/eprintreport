package edu.tamu.compassreport;

/**
 * Created by datamaskinaggie on 2/8/17.
 */

public class ApacheCommonExec {

    /*public static void main(String[] args) {

        CommandLine commandLine = new CommandLine("ping");
        commandLine.addArgument("-c");
        commandLine.addArgument("5");
        commandLine.addArguments("-W 1000");
        commandLine.addArgument("127.0.0.1");

        // Executor
        DefaultExecutor executor = new DefaultExecutor();
        try {

            LogOutputStream output = new LogOutputStream() {
                @Override
                protected void processLine(String line, int level) {
                    System.out.println(line);
                }
            };

//            ExecuteWatchdog watchDog = new ExecuteWatchdog(ExecuteWatchdog.INFINITE_TIMEOUT);
            ExecuteWatchdog watchDog = new ExecuteWatchdog(ExecuteWatchdog.INFINITE_TIMEOUT);
            PumpStreamHandler streamHandler = new PumpStreamHandler(output);
            executor.setStreamHandler(streamHandler);
            executor.setWatchdog(watchDog);

            executor.setExitValue(0);

            DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
            executor.execute(commandLine, resultHandler);

            // TODO output.close()

        } catch (ExecuteException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }*/
}
