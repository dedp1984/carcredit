package com.pujjr.business.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class KeyService 
{
	/**
	 *功能：产生用户加密密码信息
	 *参数：accountid:用户ID
	 *	  passwd   :用户密码
	 * **/
	public String generateEncryptPasswd(String accountid,String passwd) throws UnsupportedEncodingException, NoSuchAlgorithmException
	{
		MessageDigest md5=MessageDigest.getInstance("MD5");
		MessageDigest sha256=MessageDigest.getInstance("SHA-256");
		//生产64位用户随机盐
		Random ranGen = new SecureRandom();
        byte[] bSlot = new byte[32];
        String sSlot="";
        ranGen.nextBytes(bSlot);
        for(byte b:bSlot)
        {
        	sSlot+=String.format("%02X", b);
        }
        md5.update(bSlot);
		byte[] bMd5Slot=md5.digest();
		String sMd5Slot="";
		for(byte b:bMd5Slot)
		{
			sMd5Slot+=String.format("%02X", b);
		}
		sha256.update((passwd+sMd5Slot+accountid).getBytes("GBK"));
		byte[] bSha256=sha256.digest();
		String sSha256="";
		for(byte b:bSha256)
		{
			sSha256+=String.format("%02X", b);
		} 
		md5.update(bSha256);
		byte[] bMd5Sha256=md5.digest();
		String sMd5Sha256="";
		for(byte b:bMd5Sha256)
		{
			sMd5Sha256+=String.format("%02X", b);	
		}
		String encryptPasswd="";
		for(int i=0;i<sMd5Sha256.length();i++)
		{
			encryptPasswd+=sMd5Sha256.substring(i, i+1)+sMd5Slot.substring(i, i+1);
		}
		return encryptPasswd;
		
	}
	/**
	 * 功能：校验密码合法性
	 * 参数：accountid-用户ID
	 * 	   passwd-用户密码
	 *     encryptPasswd-用户加密密码
	 * **/
	public boolean verifyPasswd(String accountid,String passwd,String encryptPasswd) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		MessageDigest md5=MessageDigest.getInstance("MD5");
		MessageDigest sha256=MessageDigest.getInstance("SHA-256");
		String sMd5Slot="";
		String passwdHash="";
		for(int i=0;i<encryptPasswd.length();i=i+2)
		{
			passwdHash+=encryptPasswd.substring(i, i+1);
			sMd5Slot+=encryptPasswd.substring(i+1, i+2);
		}
		sha256.update((passwd+sMd5Slot+accountid).getBytes("GBK"));
		byte[] bSha256=sha256.digest();
		String sSha256="";
		for(byte b:bSha256)
		{
			sSha256+=String.format("%02X", b);
		} 
		System.out.println("sha256="+sSha256);
		md5.update(bSha256);
		byte[] bMd5Sha256=md5.digest();
		String sMd5Sha256="";
		for(byte b:bMd5Sha256)
		{
			sMd5Sha256+=String.format("%02X", b);	
		}
		if(sMd5Sha256.equals(passwdHash))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
}
