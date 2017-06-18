package com.sunit.global.util;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
  

public class Base64UUIDGenerate implements IdentifierGenerator {
 public static String uuid() {
  UUID uuid = UUID.randomUUID();
  return toBase64UUID(uuid);   
 }
 
 public static String UUID2Base64UUID(String uuidString) {
  UUID uuid = UUID.fromString(uuidString);
  return toBase64UUID(uuid);
 }
  
 public static String base64UUID2UUID(String base64uuid) {
  if (base64uuid.length() != 22) {
   throw new IllegalArgumentException("Invalid base64uuid!");
  }
  byte[] byUuid = Base64.decodeBase64(base64uuid + "==");
  long most = bytes2long(byUuid, 0);
  long least = bytes2long(byUuid, 8);
  UUID uuid = new UUID(most, least);
  return uuid.toString().toUpperCase();
 }
 private static String toBase64UUID(UUID uuid) {
  byte[] byUuid = new byte[16];
  long least = uuid.getLeastSignificantBits();
  long most = uuid.getMostSignificantBits();
  long2bytes(most, byUuid, 0);
  long2bytes(least, byUuid, 8);
  String compressUUID = Base64.encodeBase64URLSafeString(byUuid);
  return compressUUID;
 }
 private static void long2bytes(long value, byte[] bytes, int offset) {
  for (int i = 7; i > -1; i--) {
   bytes[offset++] = (byte) ((value >> 8 * i) & 0xFF);
  }
 }
 private static long bytes2long(byte[] bytes, int offset) {
  long value = 0;
  for (int i = 7; i > -1; i--) {
   value |= (((long) bytes[offset++]) & 0xFF) << 8 * i;
  }
  return value; 
 }
 public Serializable generate(SessionImplementor arg0, Object arg1) throws HibernateException {
  return uuid();
 }
	public static void main(String[] args) throws IOException {
		
		
		String uuid = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
//		System.out.println(UUID.fromString(s));;   
//		
//		   
//		s = "402880e8-52ef-06b0-0152-ef11de920008"; 
//		System.out.println(UUID.fromString(s));; 
		 
 
//java.util.zip.ZipOutputStream 
//		  s = "402880e852ef06b00152ef11de920008";  

		int i=1000000;
		
		for(int k=0;k<i;k++){
			 
			String uid = UUID.randomUUID().toString();
			String en = Base64UUIDGenerate.UUID2Base64UUID(uid);
			String de = Base64UUIDGenerate.base64UUID2UUID(en);
			System.out.print(uid+"     "+en+"     "+de);
			System.out.println();
			if(en.equals(de)){
				System.out.println("break");
				break;
			} 
		} 
//		String en = Base64UUIDGenerate.UUID2Base64UUID(s);
//		System.out.println(en);
//		String de = Base64UUIDGenerate.base64UUID2UUID(en);
//		
//		System.out.println(de);
		
		System.out.println("iJZXazggRsKJzJxuLqHREw".length());
	}
}
 