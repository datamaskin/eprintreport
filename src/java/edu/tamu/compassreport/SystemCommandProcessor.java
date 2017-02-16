package edu.tamu.compassreport;

/**
 * Created by datamaskinaggie on 2/8/17.
 */
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.RandomUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.nio.file.StandardCopyOption.*;

public class SystemCommandProcessor
        extends HtmlContentProcessor {

    private final static Logger LOG = LoggerFactory
            .getLogger(SystemCommandProcessor.class);

    private String commandDir = null;
    private int timeoutInSeconds = 60;
    private List<String> commands = new ArrayList<String>();

    public String getCommandDir() {
        return commandDir;
    }

    public void setCommandDir(String commandDir) {
        this.commandDir = commandDir;
    }

    public int getTimeoutInSeconds() {
        return timeoutInSeconds;
    }

    public void setTimeoutInSeconds(int timeoutInSeconds) {
        this.timeoutInSeconds = timeoutInSeconds;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    private File getWorkspace(String pathTemp) {

        File tempRoot = null;
        if (pathTemp==null)
            tempRoot = new File(TempDirUtils.getTempDirectory(), "__workspaces__");
        else tempRoot = new File(pathTemp, "__workspaces__");
        if (!tempRoot.exists()) {
            tempRoot.mkdirs();
        }
        int rand = RandomUtils.nextInt(1000000);
        File workspace = new File(tempRoot, System.currentTimeMillis() + "_" + rand);
        workspace.mkdir();
        return workspace;
    }

    @Override
    public boolean processContent(String filePath) {

        if (commands != null && commands.size() > 0) {

            for (String command : commands) {

                try {

                    Path pFile = Paths.get(filePath);
                    Path p = pFile.getParent();
                    String filename = pFile.getFileName().toString();
                    String path = p.toString();

                    File commandFile = new File(commandDir, command);
                    if (!commandFile.exists()) {
                        continue;
                    }

                    File workspace = getWorkspace(path);

                    workspace.setReadable(true);
                    workspace.setExecutable(true);
//                    boolean newfile = workspace.createNewFile();
                    Files.copy(pFile, Paths.get(workspace.getPath(), filename), COPY_ATTRIBUTES);
                    File inputFile = new File(workspace, filename);
                    inputFile.setReadable(true);
                    inputFile.setExecutable(true);

                    LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
                    params.put("command", commandDir + command);
                    params.put("inputPath", inputFile.getPath());
                    /*params.put("outputPath", outputFile.getPath());
                    params.put("metadataPath", metaFile.getPath());*/

                    SystemCommand systemCommand = new SystemCommand();
//                    String commandStr = "${command} \"${inputPath}\" \"${outputPath}\"" + " \"${metadataPath}\"";
                    String commandStr = "${command} \"${inputPath}\"";
                    systemCommand.setCommand(commandStr);
                    systemCommand.setWorkingDirectory(workspace);
                    systemCommand.setTimeoutInSeconds(timeoutInSeconds);
                    systemCommand.setParams(params);

                    // run the system command
                    ProcessUtils.runSystemCommand(systemCommand);
                    boolean failed = systemCommand.isFailed();

                    if (!failed) {
                        System.out.println("System command successful.");
                    }

                    // remove the workspace
                    FileUtils.deleteQuietly(workspace);
                }
                catch (Exception e) {
                    LOG.error("Error processing command: " + command, e);
                    continue;
                }
            }
        }

        return true;
    }
}
