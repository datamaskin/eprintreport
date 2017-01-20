package edu.tamu.compassreport;

import javax.sql.DataSource;
import java.io.*;
import java.math.BigInteger;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by david on 1/18/17.
 * Changes to force push
 * Template for the memory only version
 */
public class WriteBlob {

    Connection conn = null;
    String url;
    String dbName;
    String driver;
    String userName;
    String passWord;
    String mimetype;
    ResultSet rs = null;
    public DataSource dataSource = null;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
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

    public int writeBlob(BigInteger seq) {
        int length = 0;
        try {
            DataSource ds = getDataSource();
//            Class.forName(driver).newInstance();
//            conn = DriverManager.getConnection(url+dbName,userName,passWord);
            conn = ds.getConnection();

            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select gw_rpts_blob, gw_rpts_mime FROM gw_rpts inner JOIN gw_rpts_def on gw_rpts.gw_rpts_sequence = '" + seq + "'");
            Blob lob = null;
            String mimetype = rs.getString("gw_rpts_mimetype");
            setMimetype(mimetype);
            while (rs.next()) {
                lob = rs.getBlob("gw_rpts_blob");
            }

            InputStream in = lob.getBinaryStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            OutputStream ops = new FileOutputStream(seq + "." + mimetype);

            int buffersize = 1024;
            length = (int)lob.length();

            byte[] buffer = new byte[buffersize];

            while((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }

            out.writeTo(ops);
            in.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger("WriteBlog").log(Level.ALL, "Reason for exception: " + e.getMessage());
        }

        return length;
    }

}
