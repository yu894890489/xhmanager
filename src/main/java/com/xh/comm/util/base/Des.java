package com.xh.comm.util.base;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
/**
 * 生成密令的基础方法
 * @author yuq
 * @date 2017年8月9日
 * @todo TODO
 */
public class Des {
    static Des instance;
    static Key key;
    static Cipher encryptCipher;
    static Cipher decryptCipher;
 
    protected Des() {
    }
 
    protected Des(String strKey) {
        key = setKey(strKey);
        try {
            encryptCipher = Cipher.getInstance("DES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            decryptCipher = Cipher.getInstance("DES");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
 
    }
 
    public static Des getInstance() {
        if (instance == null) {
            instance = new Des("diaxxxxoft@201Y10");
        }
 
        return instance;
    }
 
    //  根据参数生成KEY
   
    private Key setKey(String strKey) {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES"); 
            DESKeySpec keySpec = new DESKeySpec(strKey.getBytes("utf-8")); 
            keyFactory.generateSecret(keySpec); 
            return keyFactory.generateSecret(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
     
        return null;
    }
 
    //  加密String明文输入,String密文输出
    public String setEncString(String strMing) {
        try {
            byte[] byteMing = strMing.getBytes("UTF-8");
            byte[] byteMi = this.getEncCode(byteMing);
            return BASE64Encoder.encode(byteMi);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 建议加密用这个
     * @param str
     * @return 
     */
    public String setEbcByByte2HexStr(String str){
    	 try {
             byte[] byteMing = str.getBytes("UTF-8");
             byte[] byteMi = this.getEncCode(byteMing);
             return parseByte2HexStr(byteMi);
             //return BASE64Encoder.encode(byteMi);
         } catch (Exception e) {
             e.printStackTrace();
         }
         return null;
    }
    //加密以byte[]明文输入,byte[]密文输出
    private byte[] getEncCode(byte[] byteS) {
        byte[] byteFina = null;
        try {
            byteFina = encryptCipher.doFinal(byteS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteFina;
    }
 
    //   解密:以String密文输入,String明文输出
    public String setDesString(String strMi) {
        try {
            byte[] byteMi = BASE64Encoder.decode(strMi);
            byte[] byteMing = this.getDesCode(byteMi);
            return new String(byteMing, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 建议解密此方法
     * @param str
     * @return
     */
    public  String getDesCodeHexStr2Byte(String str){
    	try {
            byte[] byteMing = this.getDesCode(parseHexStr2Byte(str));
            return new String(byteMing, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // 解密以byte[]密文输入,以byte[]明文输出
    private byte[] getDesCode(byte[] byteD) {
        byte[] byteFina = null;
        try {
            byteFina = decryptCipher.doFinal(byteD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteFina;
    }
	/**将二进制转换成16进制 
	 * @param buf 
	 * @return 
	 */  
	public static String parseByte2HexStr(byte buf[]) {  
	        StringBuffer sb = new StringBuffer();  
	        for (int i = 0; i < buf.length; i++) {  
	                String hex = Integer.toHexString(buf[i] & 0xFF);  
	                if (hex.length() == 1) {  
	                        hex = '0' + hex;  
	                }  
	                sb.append(hex.toUpperCase());  
	        }  
	        return sb.toString();  
	} 
	/**将16进制转换为二进制 
	 * @param hexStr 
	 * @return 
	 */  
	public static byte[] parseHexStr2Byte(String hexStr) {  
	        if (hexStr.length() < 1)  
	                return null;  
	        byte[] result = new byte[hexStr.length()/2];  
	        for (int i = 0;i< hexStr.length()/2; i++) {  
	                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
	                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
	                result[i] = (byte) (high * 16 + low);  
	        }  
	        return result;  
	}
 
    //多线程测试一下
    public static void main(String[] args) throws InterruptedException {
    	 //没有依赖注入的配置，所以在这里手动生成一次
        Des dtDes = Des.getInstance();
 
    	 String str = dtDes.setEbcByByte2HexStr("yu"+new Date().getTime());
         System.out.println(str);
    	System.out.println(dtDes.getDesCodeHexStr2Byte(str));
    	
       
 
//        final String[] mi = new String[10];
//        for (int i = 0; i < 10; i++) {
//            final Integer integer = i;
//            Thread thread = new Thread() {
//                public void run() {
//                    //明文加密：
//                    Des dtDes = Des.getInstance();
//                    mi[integer] = dtDes.setEncString("ShowHistory.jsp?MenuId=345&MenuBelong=1&tableLimits=where a1450=RecordId"); //调用get函数获取加密后密文。
//                }
//            };
//            thread.start();
//        }
// 
//        Thread.sleep(5000);
// 
//        for (int i = 0; i < 10; i++) {
//            final Integer integer = i;
// 
//            Thread thread2 = new Thread() {
//                public void run() {
//                    System.out.println(String.format("mi[%s] = %s", integer, mi[integer]));
//                    //这样来模拟另外一个页面的获取
//                    Des dtDes2 = Des.getInstance();
//                    String M = dtDes2.setDesString(mi[integer]);//调用get函数获取解密后明文。
//                    System.out.println(String.format("des[%s] = %s", integer, M));
//                }
//            };
//            thread2.start();
//        }
// 
//        //等待打印完毕
//        Thread.sleep(5000);
    }
}
