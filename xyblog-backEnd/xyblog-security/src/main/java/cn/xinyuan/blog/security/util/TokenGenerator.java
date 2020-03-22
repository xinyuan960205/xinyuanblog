package cn.xinyuan.blog.security.util;

import cn.xinyuan.blog.common.exception.ErrorEnum;
import cn.xinyuan.blog.common.exception.MyException;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * @ClassName: TokenGenerator
 * @Description: 生成token
 * @Author: xinyuan
 * @CreateDate: 2020/1/22 22:39
 */
public class TokenGenerator {

    public static String generateValue() {
        return generateValue(UUID.randomUUID().toString());
    }

    private static final char[] hexCode = "0123456789abcdef".toCharArray();

    // 字符数组转换成字符串返回
    public static String toHexString(byte[] data) {
        if(data == null) {
            return null;
        }
        // new一个字符串，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        StringBuilder r = new StringBuilder(data.length*2);
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        for (byte b : data) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
    }

    public static String generateValue(String param) {
        try {
            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            // param.getBytes() 输入的字符串转换成字节数组
            algorithm.update(param.getBytes());
            // 转换并返回结果，也是字节数组，包含16个元素
            byte[] messageDigest = algorithm.digest();
            // 字符数组转换成字符串返回
            return toHexString(messageDigest);
        } catch (Exception e) {
            throw new MyException(ErrorEnum.TOKEN_GENERATOR_ERROR, e);
        }
    }
}
