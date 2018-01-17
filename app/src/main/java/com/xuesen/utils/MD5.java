package com.xuesen.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Static functions to simplifiy common {@link MessageDigest}
 * tasks. This class is thread safe.
 */
public final class MD5 {

    private MD5() {
    }

    /**
     * Returns a MessageDigest for the given <code>algorithm</code>.
     *
     * @return An MD5 digest instance.
     * @throws RuntimeException when a {@link NoSuchAlgorithmException} is
     *                          caught
     */

    static MessageDigest getDigest() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Calculates the MD5 digest and returns the value as a 16 element
     * <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return MD5 digest
     */
    public static byte[] md5(byte[] data) {
        return getDigest().digest(data);
    }

    /**
     * Calculates the MD5 digest and returns the value as a 16 element
     * <code>byte[]</code>.
     *
     * @param buffer Data to digest
     * @return MD5 digest
     */
    public static byte[] md5(ByteBuffer buffer) {
        MessageDigest digest = getDigest();
        digest.update(buffer);
        return digest.digest();
    }

    /**
     * Calculates the MD5 digest and returns the value as a 16 element
     * <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return MD5 digest
     */
    public static byte[] md5(String data) {
        return md5(data.getBytes());
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex
     * string.
     *
     * @param data Data to digest
     * @return MD5 digest as a hex string
     */
    public static String md5Hex(byte[] data) {
        return StringUtils.bytesToHexString(md5(data));
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex
     * string.
     *
     * @param buffer Data to digest
     * @return MD5 digest as a hex string
     */
    public static String md5Hex(ByteBuffer buffer) {
        return StringUtils.bytesToHexString(md5(buffer));
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex
     * string.
     * 计算MD5 返回字符串
     *
     * @param data Data to digest
     * @return MD5 digest as a hex string 字符串
     */
    public static String md5Hex(String data) {
        return StringUtils.bytesToHexString(md5(data));
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex
     * string.
     *
     * @return MD5 digest as a hex string
     */
    public static String md5Hex(File f) throws IOException {
        if (!f.exists()) throw new FileNotFoundException(f.toString());

        InputStream in = null;
        try {
            in = new FileInputStream(f);
            return md5Hex(in);
        } catch (IOException e) {
            throw e;
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (Exception e2) {
                }
        }
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex
     * string.
     *
     * @param in InputStream data to digest
     * @return MD5 digest as a hex string
     * @throws IOException
     */
    public static String md5Hex(InputStream in) throws IOException {
        long buf_size = in.available();
        if (buf_size < 512) buf_size = 512;
        if (buf_size > 65536) buf_size = 65536;

        byte[] buf = new byte[(int) buf_size];

        MessageDigest digest = getDigest();

        int r;
        while ((r = in.read(buf)) != -1) {
            digest.update(buf, 0, r);
        }

        return StringUtils.bytesToHexString(digest.digest());
    }
}
