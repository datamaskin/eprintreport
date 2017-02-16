package edu.tamu.compassreport;

/**
 * Created by datamaskinaggie on 2/8/17.
 */
import java.io.File;

public class TempDirUtils {

    public static File getTempDirectory() {
        return new File(System.getProperty("java.io.tmpdir"));
    }
}
