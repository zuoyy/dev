

package com.zuoyy.component.pay.common.util.sign.encrypt;
/**
 *  Base64
 */
public class Base64 {

    private Base64() {}

    public static byte[] decode(String str) {
        return org.apache.commons.codec.binary.Base64.decodeBase64(str);
    }

    public static String encode(byte[] bytes) {
        return org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
    }

}
