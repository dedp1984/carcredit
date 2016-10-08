package com.pujjr.business.service;

import java.util.HashMap;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pujjr.business.dao.SqlServerMapper;

@Service
public class WSSqlserverService 
{
	@Autowired
	private SqlServerMapper sqlServerDao;
	/**
	 * 查询信贷系统在审案件进度表
	 * @throws Exception 
	 * **/
	public List<HashMap<String,Object>> getInCaseList(String sqfqrq) throws Exception
	{
		List<HashMap<String,Object>> list = sqlServerDao.selectInCaseList(sqfqrq);
		for(int i=0 ; i<list.size() ;i++)
		{
			HashMap<String,Object> item = list.get(i);
			String idno = item.get("custIdNo").toString();
			idno = decrypt(idno, "PsW_^##_");
			item.put("custIdNo", idno);
			list.set(i, item);
		}
		return  list;
	}
	
	/**
	 * 查询代扣明细
	 * **/
	public List<HashMap<String,Object>>  getChargeBackList()
	{
		return sqlServerDao.selectChargeBackList();
	}
	/**
	 * 查询征信数据
	 * @throws Exception 
	 * **/
	public List<HashMap<String,Object>> getCreditDataList(String sqfqrq) throws Exception
	{
		List<HashMap<String,Object>> list = sqlServerDao.selectCreditDataList(sqfqrq);
		for(int i=0 ; i<list.size() ;i++)
		{
			HashMap<String,Object> item = list.get(i);
			String idno = item.get("idno").toString();
			idno = decrypt(idno, "PsW_^##_");
			item.put("idno", idno);
			String mobile = item.get("mobile").toString();
			mobile = decrypt(mobile,"PsW_^##_");
			item.put("mobile", mobile);
			String dwdh = item.get("dwdh").toString();
			dwdh = decrypt(dwdh,"PsW_^##_");
			item.put("dwdh", dwdh);
			list.set(i, item);
		}
		return list;
	}
	/**
	 * 查询客户还租信息表
	 * @throws Exception 
	 * **/
	public List<HashMap<String,Object>> getCustRepayList(String qyrq) throws Exception
	{
		List<HashMap<String,Object>>  list = sqlServerDao.selectCustRepayList(qyrq);
		for(int i=0;i<list.size();i++)
		{
			HashMap<String,Object> item = list.get(i);
			String idno = item.get("CustomerIDNO").toString();
			idno = decrypt(idno,"PsW_^##_");
			item.put("CustomerIDNO", idno);
			list.set(i,item);
		}
		return list;
	}
	
	/**查询客户放款明细
	 * @throws Exception **/
	public List<HashMap<String,Object>> getFkmxList(String fkrq) throws Exception
	{
		List<HashMap<String,Object>>  list = sqlServerDao.selectFkmxList(fkrq);
		for(int i=0;i<list.size();i++)
		{
			HashMap<String,Object> item = list.get(i);
			String idno = item.get("idno").toString();
			idno = decrypt(idno,"PsW_^##_");
			item.put("idno", idno);
			String mobile =item.get("mobile").toString();
			mobile = decrypt(mobile,"PsW_^##_");
			item.put("mobile", mobile);
			list.set(i,item);
		}
		return list;
	}
	
	public List<HashMap<String,Object>> getCustomInfo() throws Exception
	{
		
		List<HashMap<String,Object>>  list = sqlServerDao.selectCustomerInfo();
		for(int i=0;i<list.size();i++)
		{
			try
			{
				HashMap<String,Object> item = list.get(i);
				System.out.println(item.toString());
				String idno = item.get("CustomerIDNO").toString();
				idno = decrypt(idno,"PsW_^##_");
				item.put("CustomerIDNO", idno);
			
				Object mobile = item.get("CustomerMobileNO");
				if(!mobile.toString().equals(""))
				{
					item.put("CustomerMobileNO", decrypt(mobile.toString(),"PsW_^##_"));
				}
				sqlServerDao.updateCustomerInfo(item);
				list.set(i,item);
			}
			catch(Exception e){
				continue;
			}
			
		}
		return list;
		/*
		List<HashMap<String,Object>>  list = sqlServerDao.selectContactInfo();
		for(int i=0;i<list.size();i++)
		{
			HashMap<String,Object> item = list.get(i);
			System.out.println(item.toString());
			String idno = item.get("Contact1MobileNO").toString();
			try
			{
				if(idno.startsWith("1")|| idno.startsWith("0") || idno.length()==0){
					continue;
				}
				idno = decrypt(idno,"PsW_^##_");
				item.put("Contact1MobileNO", idno);
			
				sqlServerDao.updateContactTelInfo(item.get("ApplicationNO").toString(), idno);
				list.set(i,item);
			}
			catch(Exception e){
				continue;
			}
			
		}
		return list;*/
	}
	
	public static String decrypt(String message, String key) throws Exception {
		byte[] bytesrc = Base64.decode(message.getBytes("UTF-8"));
		// byte[] bytesrc =convertHexString(message);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
		byte[] retByte = cipher.doFinal(bytesrc);
		return new String(retByte);
	}

	public static byte[] encrypt(String message, String key) throws Exception {
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
		return cipher.doFinal(message.getBytes("UTF-8"));
	}

	public static byte[] convertHexString(String ss) {
		byte digest[] = new byte[ss.length() / 2];
		for (int i = 0; i < digest.length; i++) {
			String byteString = ss.substring(2 * i, 2 * i + 2);
			int byteValue = Integer.parseInt(byteString, 16);
			digest[i] = (byte) byteValue;
		}
		return digest;
	}

	public static String toHexString(byte b[]) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String plainText = Integer.toHexString(0xff & b[i]);
			if (plainText.length() < 2)
				plainText = "0" + plainText;
			hexString.append(plainText);
		}
		return hexString.toString();
	}
}
