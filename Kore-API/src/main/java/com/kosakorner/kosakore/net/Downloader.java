package com.kosakorner.kosakore.net;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class Downloader extends Thread {

    private static SSLSocketFactory socketFactory;

    private String            sourceURL;
    private File              destination;
    private String            md5Hash;
    private IDownloadListener listener;

    private int retries;

    static {
        try {
            TrustSSL[] trustAllCerts = new TrustSSL[]{new TrustSSL()};

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
            socketFactory = sslSocketFactory;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Downloader(String sourceURL, File destination, String md5Hash, IDownloadListener listener) {
        setDaemon(true);
        this.sourceURL = sourceURL;
        this.destination = destination;
        destination.getParentFile().mkdir();
        this.md5Hash = md5Hash;
        this.retries = 0;
        this.listener = listener;
    }

    public void run() {
        try {
            if (destination.exists()) {
                if (md5Hash != null) {
                    String fileHash = getMD5Hash(destination);
                    if (md5Hash.equals(fileHash)) {
                        listener.onSuccess(destination);
                    }
                }
                else {
                    listener.onSuccess(destination);
                }
            }
            else {
                URL url = new URL(sourceURL);
                URLConnection connection = url.openConnection();
                connection.setReadTimeout(15000);
                connection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");

                if (sourceURL.contains("https")) {
                    ((HttpsURLConnection) connection).setSSLSocketFactory(socketFactory);
                }

                ReadableByteChannel rbc = Channels.newChannel(connection.getInputStream());
                FileOutputStream fos = new FileOutputStream(destination);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                fos.close();
                rbc.close();

                if (md5Hash != null) {
                    String fileHash = getMD5Hash(destination);
                    if (!md5Hash.equals(fileHash)) {
                        if (retries < 3) {
                            if (destination.delete()) {
                                retries++;
                                run();
                            }
                        }
                        else {
                            listener.onFailure(destination);
                        }
                    }
                    else {
                        listener.onSuccess(destination);
                    }
                }
                else {
                    listener.onSuccess(destination);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getMD5Hash(File file) {
        DigestInputStream is = null;
        try {
            is = new DigestInputStream(new FileInputStream(file), MessageDigest.getInstance("MD5"));
            byte[] ignored = new byte[65536];
            for (int readBytes = is.read(ignored); readBytes >= 1; readBytes = is.read(ignored)) {
                ;
            }
            return String.format("%1$032x", new Object[]{new BigInteger(1, is.getMessageDigest().digest())});
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if (is != null) {
                    is.close();
                }
            }
            catch (IOException localIOException2) {
            }
        }
        return "";
    }

}
