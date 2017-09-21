package com.sunit.global.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

/**
 * 二维码生成器
 * 
 * @blog http://sjsky.iteye.com
 * @author Michael
 */
public class QRCodeEncoderHandler {

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @paramcontent
	 * @paramimgPath
	 */
	public void encoderQRCode(String content, String imgPath) {
		try {

			Qrcode qrcodeHandler = new Qrcode();
			qrcodeHandler.setQrcodeErrorCorrect('M');
			qrcodeHandler.setQrcodeEncodeMode('B');
			qrcodeHandler.setQrcodeVersion(7); 

			System.out.println(content);
			byte[] contentBytes = content.getBytes("utf-8"); 

			BufferedImage bufImg = new BufferedImage(140, 140, BufferedImage.TYPE_INT_RGB);

			Graphics2D gs = bufImg.createGraphics();

			gs.setBackground(Color.WHITE); 
			gs.clearRect(0, 0, 140, 140); 
 
			// 设定图像颜色> BLACK
			gs.setColor(Color.BLACK);

			// 设置偏移量 不设置可能导致解析出错
			int pixoff = 2;
			// 输出内容> 二维码
			if (1 == 1 || contentBytes.length > 0 && contentBytes.length < 120) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				System.err.println("QRCode content bytes length = " + contentBytes.length + " not in [ 0,120 ]. ");
			}

			gs.dispose();
			bufImg.flush();

			File imgFile = new File(imgPath);

			// 生成二维码QRCode图片
			ImageIO.write(bufImg, "png", imgFile);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String decoderQRCode(String imgPath) {

		// QRCode 二维码图片的文件
		File imageFile = new File(imgPath);

		BufferedImage bufImg = null;
		String decodedData = null;
		try {
			bufImg = ImageIO.read(imageFile);

			QRCodeDecoder decoder = new QRCodeDecoder();
			decodedData = new String(decoder.decode(new J2SEImageGucas(bufImg)));

			 try {
				 System.out.println(decodedData);
			// System.out.println(new String(decodedData.getBytes("gb2312"), "utf-8"));
			 } catch (Exception e) {
			 // TODO: handle exception
			 }
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
			dfe.printStackTrace();
		}
		return decodedData;
	}

	/**
	 * @param argsthe
	 *            command line arguments
	 */
	public static void main(String[] args) {
		String imgPath = "D:/Michael_QRCode.png";

		String content = "http://wxtest.longerinfo.com/SLHDemo/wx/viewRequest.action";

//		 content = "Hello 龙杰信息张qf,welcome to QRCode!";
		 
		QRCodeEncoderHandler handler = new QRCodeEncoderHandler();
		handler.encoderQRCode(content, imgPath); 

		System.out.println("encoder QRcode success");
 
		//decoderQRCode(imgPath); 
	}

	private static class J2SEImageGucas implements QRCodeImage {
		BufferedImage image;

		public J2SEImageGucas(BufferedImage image) {
			this.image = image;
		}

		public int getWidth() {
			return image.getWidth();
		}

		public int getHeight() {
			return image.getHeight();
		}

		public int getPixel(int x, int y) {
			return image.getRGB(x, y);
		}
	}

}
