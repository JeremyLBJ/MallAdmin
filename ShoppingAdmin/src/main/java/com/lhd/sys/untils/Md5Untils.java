package com.lhd.sys.untils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//使用MD5对用户登录进行加密
public class Md5Untils {
	
	
	/**
	 * 使用md5的算法进行加密
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// // 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}
	
	
	public static String stringMD5(String input) {

		  try {

		     // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）

		     MessageDigest messageDigest =MessageDigest.getInstance("MD5");
		     

		     // 输入的字符串转换成字节数组

		     byte[] inputByteArray = input.getBytes();


		     // inputByteArray是输入字符串转换得到的字节数组

		     messageDigest.update(inputByteArray);



		     // 转换并返回结果，也是字节数组，包含16个元素

		     byte[] resultByteArray = messageDigest.digest();



		     // 字符数组转换成字符串返回

		     return byteArrayToHex(resultByteArray);



		  } catch (NoSuchAlgorithmException e) {

		     return null;

		  }
		}
	
	//下面这个函数用于将字节数组换成成16进制的字符串
		public static String byteArrayToHex(byte[] byteArray) {

		  // 首先初始化一个字符数组，用来存放每个16进制字符

		  char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };



		  // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））

		  char[] resultCharArray =new char[byteArray.length * 2];



		  // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去

		  int index = 0;

		  for (byte b : byteArray) {

		     resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];

		     resultCharArray[index++] = hexDigits[b& 0xf];

		  }


		  // 字符数组组合成字符串返回

		  return new String(resultCharArray);
		}


}
