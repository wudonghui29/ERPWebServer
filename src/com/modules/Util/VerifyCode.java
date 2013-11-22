package com.modules.Util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

public class VerifyCode {

	public static String generateCode(int length) {
		// 生成随机类
		Random random = new Random();
		String verifyCode = "";

	    String alphabet = "1234567890abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < length; i++) {
//			String rand = String.valueOf(random.nextInt(10));
			String rand = String.valueOf(alphabet.charAt(random.nextInt(alphabet.length())));
			verifyCode += rand;
		}
		return verifyCode;
	}

	public static byte[] generateImageBytes(String verifyCode) throws Exception {
		// 在内存中创建图象
		int width = 200, height = 50, fontSize = 50;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		Graphics2D g2d = (Graphics2D)g;
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g2d.setColor(getRandColor(200, 250));
		g2d.fillRect(0, 0, width, height);
		// 设定字体
		g2d.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
		// 随机产生100条干扰线，使图象中的认证码不易被其它程序探测到
		g2d.setColor(getRandColor(160, 200));
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(25);
			int yl = random.nextInt(50);
			g2d.drawLine(x, y, x + xl, y + yl);
		}
		// 画随机数
		char[] chars = verifyCode.toCharArray();
		int charsCount = chars.length;
		int standard = (6 - charsCount) * 25/2 + 25;
		AffineTransform transform = new AffineTransform();
		for (int i = 0; i < charsCount; i++) {
			String randChar = String.valueOf(chars[i]);
			// 将认证码显示到图象中
			g2d.setColor(new Color(20 + random.nextInt(110), 20 + random .nextInt(110), 20 + random.nextInt(110)));
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			double theta = Math.PI / (3 + Math.PI);
			double anchorx = standard * i + (i + 1)  * 5 ;
			double anchory = 35 ;
			
			transform.setToIdentity();
			transform.rotate(random.nextBoolean() ? theta : theta * -1, anchorx, anchory);
			g2d.setTransform(transform);
			g2d.drawString(randChar, (int)anchorx , (int)anchory);
			
//			System.out.println(i + "   " + randChar + " -> theta: " + theta + "     (" + anchorx + " , " + anchory + ")" + "   " + anchorx + "," + anchory);
		}
		// 图象生效
		g2d.dispose();
		// 转换成Bytes
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
		ImageIO.write(image, "JPEG", imageOut);
		imageOut.close();
		byte[] imageBytes = output.toByteArray();
		output.close();
		return imageBytes;
	}

	/*
	 * 给定范围获得随机颜色
	 */
	private static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}
