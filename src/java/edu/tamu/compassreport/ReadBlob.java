package edu.tamu.compassreport;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by datamaskinaggie on 1/12/17.
 */
public class ReadBlob {
    public static void main(String args[])
    {
        System.out.println("Oracle Connect START.");
        Connection conn = null;
        String url = "jdbc:oracle:thin:@lola.tamu.edu:2521:";
        String dbName = "DEVL";
        String driver = "oracle.jdbc.OracleDriver";
        String userName = "datamaskinaggie2";
        String password = "l9uQHGXOE+dxcLvtolsePjn-=";
        ResultSet rs = null;
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName,userName,password);

            Statement stmt = conn.createStatement();
//            rs =stmt.executeQuery("select distinct gw_rpts_blob from gw_rpts inner join gw_rpts_def on gw_rpts.gw_rpts_sequence = '364'");
            // can't use distinct with blob data.
            rs =stmt.executeQuery("select gw_rpts_blob from gw_rpts inner join gw_rpts_def on gw_rpts.gw_rpts_sequence = '364'");
            Blob lob = null;
            while (rs.next()) {
                lob=rs.getBlob("gw_rpts_blob");
            }

            InputStream in = lob.getBinaryStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            OutputStream outputStream = new FileOutputStream("tgphold_364.pdf");

            int bufferSize = 1024;
            int length = (int) lob.length();

            byte[] buffer = new byte[bufferSize];

            while((length = in.read(buffer)) != -1)
            {
                out.write(buffer,0,length);
            }
            out.writeTo(outputStream);
            in.close();
            conn.close();

            /*Process p1 =Runtime.getRuntime().exec("mspaint blobImage.png");*/

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}