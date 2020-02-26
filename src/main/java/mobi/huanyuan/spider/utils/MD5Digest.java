package mobi.huanyuan.spider.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Digest {

    public static final String ALGORITHM_MD2 = "MD2";
    public static final String ALGORITHM_MD5 = "MD5";
    public static final String ALGORITHM_SHA_1 = "SHA-1";
    public static final String ALGORITHM_SHA_256 = "SHA-256";
    public static final String ALGORITHM_SHA_384 = "SHA-384";
    public static final String ALGORITHM_SHA_512 = "SHA-512";

    public static String getDigest(String msg) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] byteArray = null;
        byteArray = msg.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);

        for (int i = 0; i < bArray.length; i++) {
            String sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * source.getBytes("UTF-8")
     *
     * @param source
     * @return
     */
    public static String encrypt(String source) {
        return encrypt(source, ALGORITHM_MD5);
    }

    public static String encrypt(String source, String algorithm) {
        if (source == null) return null;
        MessageDigest md;
        try {
            byte[] bt = source.getBytes("UTF-8");
            md = MessageDigest.getInstance(algorithm);
            md.update(bt);
            return bytesToHexString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
