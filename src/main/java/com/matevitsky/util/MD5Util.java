package com.matevitsky.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    /**
     * Hash incoming String by md5 algorithm
     *
     * @param st - string to be hashed
     * @return - hashed String
     */

    public static final String encryptPassword(String st) {

        return DigestUtils.md5Hex(st);
    }
}
