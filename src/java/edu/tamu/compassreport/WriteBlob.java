package edu.tamu.compassreport;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import javax.sql.DataSource;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by david on 1/18/17.
 * Changes to force push
 * Template for the memory only version
 */
public class WriteBlob {

    private Connection conn = null;
    private String url;
    private String dbName;
    private String driver;
    private String userName;
    private String passWord;
    private String mimetype;

    private String location;

    private ResultSet rs = null;
    private DataSource dataSource = null;

    private DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public static void main(String[] args) {
    }

    public WriteBlob() {
    }

    public int writeBlob(BigDecimal seq) {
        int length = 0;
        int total = 0;
        Logger logger = Logger.getLogger("writeBlob");

        try {

            DataSource dataSource = getDataSource();
            final String location = getLocation();
            System.out.println("Location: " + location);
            conn = dataSource.getConnection();

            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select gw_rpts_blob, gw_rpts_mime FROM gw_rpts WHERE gw_rpts_sequence = '" + seq + "'");
            Blob lob = null;
            String mimetype = new String();
            while (rs.next()) {
                lob = rs.getBlob("GW_RPTS_BLOB");
                mimetype = rs.getString("GW_RPTS_MIME");
            }

            setMimetype(mimetype);

            InputStream in = lob.getBinaryStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
            //add owners permission
            perms.add(PosixFilePermission.OWNER_READ);
            perms.add(PosixFilePermission.OWNER_WRITE);
            perms.add(PosixFilePermission.OWNER_EXECUTE);
            //add group permissions
            perms.add(PosixFilePermission.GROUP_READ);
            perms.add(PosixFilePermission.GROUP_WRITE);
            perms.add(PosixFilePermission.GROUP_EXECUTE);
            //add others permissions
            perms.add(PosixFilePermission.OTHERS_READ);
            perms.add(PosixFilePermission.OTHERS_WRITE);
            perms.add(PosixFilePermission.OTHERS_EXECUTE);

//            Files.setPosixFilePermissions(Paths.get(getPath() + seq + "." + mimetype), perms);

            File file = new File(getPath() + seq + "." + mimetype);

//            OutputStream ops = new FileOutputStream(getPath() + seq + "." + mimetype);

            OutputStream ops = new FileOutputStream(file);

            logger.logp(Level.ALL, "WriteBlob", "writeBlob", "Filename: " + seq+"."+mimetype);

            int buffersize = 1024;
            length = (int)lob.length();

            byte[] buffer = new byte[buffersize];

            while((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
                total += length;
            }

            out.writeTo(ops);
            in.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger("WriteBlog").log(Level.ALL, "Reason for exception: " + e.getMessage());
        }

        return total;
    }

    private Collection getDirectory(File dir, String pattern) {
        Collection files = FileUtils.listFiles(
                dir,
                new RegexFileFilter("^(.*?)"),
                DirectoryFileFilter.DIRECTORY
        );
        return files;
    };

    private Map<String, File> getAllFiles(File dir) throws Exception {
        final Collection<File> expectedFiles = FileUtils.listFilesAndDirs(dir, TrueFileFilter.INSTANCE, DirectoryFileFilter.INSTANCE);

        final Map<String, File> result = new HashMap<String, File>();

        for (final File f : expectedFiles) {
            final String relativePath = org.apache.tools.ant.util.FileUtils.getRelativePath(dir, f);
            result.put(relativePath, f);
        }

        return result;
    }

    private String getPath() throws Exception {
        File f = new File("."); // current directory

        FileFilter directoryFilter = new FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory();
            }
        };

        File[] files = f.listFiles(directoryFilter);
        String canPath = null;
        String path = null;
        for (File file : files) {
            if (file.isDirectory()) {
                canPath = file.getCanonicalPath();
//                System.out.print("directory: ");
                Collection dirs = getDirectory(file, "*");

                Map<String, File> fileMap = getAllFiles(file);

                for (Map.Entry<String, File> entry : fileMap.entrySet()) {
                    if (entry.getKey().equalsIgnoreCase(location)) {
                        path = canPath + "/" + location + "/";
                        break;
//                        System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + ", Path = " + path);
                    }
                }
            }
        }
        return path;
    }

}
