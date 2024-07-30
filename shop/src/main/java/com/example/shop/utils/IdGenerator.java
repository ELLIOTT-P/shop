package com.example.shop.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ID生成器
 * @author wubei
 *
 */
public class IdGenerator {
	
	private static SecureRandom random = new SecureRandom();
	private static final long ONE_STEP = 999;
    private static final Lock LOCK = new ReentrantLock();
    private static long lastTime = System.currentTimeMillis();
    private static short lastCount = 0;
    private static int count = 0;
    private static char[] charsItem = new char[] { 'a', 'b', 'c', 'd', 'e', 'f',  
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',  
            't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',  
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',  
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',  
            'W', 'X', 'Y', 'Z' };
    
	/**
	 * 生成36位唯一ID<br>
	 * 调用java原生{@link UUID}
	 * @return
	 * 	返回ID格式 21b599cf76aa4bf2a38a262bb3067e94
	 */
	public static String uuid36() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 生成32位唯一ID<br>
	 * 调用java原生{@link UUID}
	 * @return
	 * 	返回ID格式  1b810d19-db3c-4122-a23d-f657fa9e09c9
	 */
	public static String uuid32() {
		return uuid36().replace("-", "");
	}


    /**
     * 生成24位唯一ID<br>
     * 调用java原生{@link UUID}生成36位唯一ID后<br>
     * 对生成的36位ID做MD5,Base64加密
     * @return
     * 	返回ID格式 4usX1L8MvsswZcTQxyLJrA==
     */
    public static String uuidBase64(){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte md5[] =  md.digest(uuid36().getBytes());
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成自定义长度的ID<br>

     * @param
     * 	length 生成ID的长度
     * @return
     * 	返回ID格式 length = 10,ID = PhHjJn867L
     */
    public static String randomBase62(int length){
//    	byte[] randomBytes = new byte[length];
//		random.nextBytes(randomBytes);
//		return encodeBase62(randomBytes);
        return generateRandomPassword(length);
    }

    /**
     * 生成随机密码,去除容易混淆的数字和字母 i I l 1 o O 0 z Z k K
     * @param length 密码长度
     * @return 随机密码
     * @param length
     * @return
     */
    public static String generateRandomPassword(int length) {
        // 定义小写字母
        String lowerCaseLetters = "abcdefghjmnpqrstuvwxy";
        // 定义大写字母
        String upperCaseLetters = "ABCDEFGHJMNPQRSTUVWXYL";
        // 定义数字
        String numbers = "23456789";

        // 将所有可选字符拼接起来
        String allCharacters = lowerCaseLetters + upperCaseLetters + numbers;
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        // 循环生成密码的每个字符
        for (int i = 0; i < length; i++) {
            // 生成一个随机索引值
            int index = random.nextInt(allCharacters.length());
            // 将对应索引值上的字符添加到密码中
            password.append(allCharacters.charAt(index));
        }

        // 将密码转换为字符串并返回
        return password.toString();
    }


    /**
     * 生成16位唯一ID<br>
     * 按当前时间戳拼接4位数<br>
     * 4位数从0开始,为999的时归0重新计数
     * @return
     *  返回ID格式 1450840529064000
     */
    @SuppressWarnings("finally")
	public static String generalSrid() {
		LOCK.lock();
        try {
            if (lastCount == ONE_STEP) {
                boolean done = false;
                while (!done) {
                    long now = System.currentTimeMillis();
                    if (now == lastTime) {
                        try {
                            Thread.currentThread();
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                        }
                        continue;
                    } else {
                        lastTime = now;
                        lastCount = 0;
                        done = true;
                    }
                }
            }
            count = lastCount++;
        }
        finally 
        {
            LOCK.unlock();
            return lastTime+""+String.format("%03d",count); 
        }
	}
    
    /**
     * 自定义Base62编码
     */
    public static String encodeBase62(byte[] input){
    	char[] chars = new char[input.length];
		for (int i = 0; i < input.length; i++) {
			chars[i] = charsItem[((input[i] & 0xFF) % charsItem.length)];
		}
		return new String(chars);
    }


    
    public static void main(String[] args) {
    	System.out.println(Double.compare(10, 5));
    	System.out.println(String.format("%01d"+"2",0));
//    	for (int i = 0; i < 1000; i++) {
//    		System.out.println(generalSrid());
//		}
        System.out.println(getItemID("CN").length());
	}
    public static String getItemID(String prefix){
        String str = prefix + String.valueOf(System.currentTimeMillis());
        str +=  (int)((Math.random() * 9 + 1) * 10000);
        return str;
    }

    public static String getMD5(String input) {
        try {
            // 创建一个MD5 Hash
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(input.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16进制数
            byte[] messageDigest = md.digest();

            // 创建Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            // 返回32位hex格式hash码
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
