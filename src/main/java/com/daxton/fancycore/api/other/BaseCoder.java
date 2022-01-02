package com.daxton.fancycore.api.other;

import java.util.Base64;

public class BaseCoder {

	//byte轉Base64字串
	public static String byteToBase64(byte[] message){
		byte[] encodedBytes = Base64.getEncoder().encode(message);
		return new String(encodedBytes);
	}

	//byte轉Base64字串
	public static byte[] byteToBase64Byte(byte[] message){
		return Base64.getEncoder().encode(message);
	}

	//Base64字串轉byte
	public static byte[] base64ToByte(String base64){
		return Base64.getDecoder().decode(base64);
	}

	//字串轉Base64編碼
	public static String stringToBase64(String message){
		byte[] encodedBytes = Base64.getEncoder().encode(message.getBytes());
		return new String(encodedBytes);
	}
	//Base64編碼轉字串
	public static String base64toString(String base64){
		byte[] decodedBytes = Base64.getDecoder().decode(base64.getBytes());
		return new String(decodedBytes);
	}

}
