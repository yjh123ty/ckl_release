package tech.youmu.ckl.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 
 * <p>Title:QRCodeUtil</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年10月12日上午9:19:17</p>
 * <p>Description:二维码</p>
 */
public class QRCodeUtil {
	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	/*
	 * 解码： 1 将图片反解码为二维矩阵 2 将该二维矩阵解码为内容
	 */
	public static void decode(String imgPath) {
		try {
			File file = new File(imgPath);// 获取该图片文件
			BufferedImage image;
			image = ImageIO.read(file);
			if (image == null) {
				System.out.println("Could not decode image");
			}
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			Result result;
			Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();// 将图片反解码为二维矩阵
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			result = new MultiFormatReader().decode(bitmap, hints);// 将该二维矩阵解码成内容
			String resultStr = result.getText();
			System.out.println("解码结果：" + resultStr);

		} catch (IOException ioe) {
			System.out.println(ioe.toString());
		} catch (ReaderException re) {
			System.out.println(re.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 *  <p>Author:xiongchuan;</p>
	 *  <p>Date:2016年10月12日上午9:21:01;</p>
	 *	<p>Description: TODO;</p>
	 *  @param content
	 *  @param w
	 *  @param h
	 *  @return
	 */
	public static BufferedImage encode(String content, int w, int h) {

		// 生成二维码
		MultiFormatWriter mfw = new MultiFormatWriter();
		BitMatrix bitMatrix = null;

		Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		// 指定编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		// 设置二维码内容到边框的距离
		hints.put(EncodeHintType.MARGIN, 0);
		try {
			bitMatrix = mfw.encode(content, BarcodeFormat.QR_CODE, w, h, hints);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
			}
		}
		return image;
	}

	/**
	 * 编码
	 * 
	 * @author ChenGuiYong
	 * @data 2015年7月13日 上午11:06:20
	 * @param contents
	 * @param width
	 * @param height
	 * @param imgPath
	 * @param logoPath
	 * @return
	 */
	public static boolean encode(String contents, int width, int height, String imgPath, String logoPath) {
		Map<EncodeHintType, Object> hints = new Hashtable<>();
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		// 指定编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		// 设置二维码内容到边框的距离
		hints.put(EncodeHintType.MARGIN, 1);
		String format = "png";
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);

			MatrixToImageWriter.writeToPath(bitMatrix, format, new File(imgPath).toPath());// 输出图像
			// MatrixToImageWriter.writeToStream(bitMatrix, format ,new
			// FileOutputStream(imgPath));

			// File qrcodeFile = new File(imgPath);
			// 增加logo
			// writeToLogo(bitMatrix,format , qrcodeFile, logoPath);
		} catch (Exception e) {
			return false;
		}
		System.out.println("编码成功！");
		return true;
	}

	/**
	 * 增加Logo
	 * 
	 * @author ChenGuiYong
	 * @data 2015年7月13日 上午11:07:00
	 * @param matrix
	 * @param format
	 * @param file
	 * @param logoPath
	 * @throws IOException
	 */
	public static void writeToLogo(BitMatrix matrix, String format, File file, String logoPath) throws IOException {
		Graphics2D graphics2 = null;
		BufferedImage image = null;
		BufferedImage logo = null;
		try {
			/**
			 * 读取二维码图片，并构建绘图对象
			 */
			image = toBufferedImage(matrix);
			graphics2 = image.createGraphics();

			/**
			 * 读取Logo图片
			 */
			logo = ImageIO.read(new File(logoPath));
			int codeWidth = image.getWidth();
			int codeHeight = image.getHeight();
			/**
			 * 设置logo的大小,设置为二维码图片的25%,因为过大会盖掉二维码
			 */
			int widthLogo = logo.getWidth(null) > codeWidth * 2 / 13 ? (codeWidth * 2 / 13) : logo.getWidth(null),
					heightLogo = logo.getHeight(null) > codeHeight * 2 / 13 ? (codeHeight * 2 / 13)
							: logo.getWidth(null);

			/**
			 * 计算图片放置位置 logo放在中心
			 */
			int x = (codeWidth - widthLogo) / 2;
			int y = (codeHeight - heightLogo) / 2;
			int radius = 14;// 圆角范围

			// 填充与logo大小类似的扁平化圆角矩形背景
			graphics2.setComposite(AlphaComposite.Src);
			graphics2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			graphics2.setColor(Color.WHITE);
			graphics2.fill(new RoundRectangle2D.Float(x - 2, y - 2, widthLogo + 4, heightLogo + 4, radius, radius));
			graphics2.setComposite(AlphaComposite.SrcAtop);

			// 开始绘制logo图片
			graphics2.drawImage(logo, x, y, widthLogo, heightLogo, null);

			if (!ImageIO.write(image, format, file)) {
				throw new IOException("Could not write an image of format " + format + " to " + file);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (graphics2 != null) {
				graphics2.dispose();
			}
			if (logo != null) {
				logo.flush();
			}
			if (image != null) {
				image.flush();
			}
		}
	}

	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}
	
	public static String getQRCodeUrl(String code){
	    StringBuffer buffer = new StringBuffer();
	    buffer.append(ConfigUtil.getLocalDomain()).append("/user/remote/getQRCode").append("?code=").append(code);
        return buffer.toString();
	}

}