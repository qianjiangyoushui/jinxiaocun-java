package com.ecfund.base.util.common;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import com.sun.image.codec.jpeg.*;
/**
 * 图片压缩处理
 * @author 崔素强
 */
public class ImgCompress {
	private Image img;
	private int width;
	private int height;
	private static String infilePath="D:\\tudouji\\images\\15947217447\\77-16061Q44U6444.jpg";
	private static String outfilePath="D:\\tudouji\\images\\15947217447\\77-16061Q44U6444-suo.jpg";
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		ImgCompress imgCom = new ImgCompress(infilePath,outfilePath);
		imgCom.resizeFix(79, 79);
	}
	/**
	 * 默认构造函数
	 */
	public ImgCompress() throws IOException {
	}
	/**
	 * 构造函数
	 */
	public ImgCompress(String inFileName,String outFileName) throws IOException {
		infilePath=inFileName;
		outfilePath=outFileName;
		File file = new File(infilePath);// 读入文件
		img = ImageIO.read(file);      // 构造Image对象
		width = img.getWidth(null);    // 得到源图宽
		height = img.getHeight(null);  // 得到源图长
	}
	/**
	 * 按照宽度还是高度进行压缩
	 * @param w int 最大宽度
	 * @param h int 最大高度
	 */
	public void resizeFix(int w, int h) throws IOException {
		if (width / height > w / h) {
			resizeByWidth(w);
		} else {
			resizeByHeight(h);
		}
	}
	/**
	 * 以宽度为基准，等比例放缩图片
	 * @param w int 新宽度
	 */
	public void resizeByWidth(int w) throws IOException {
		int h = (int) (height * w / width);
		resize(w, h);
	}
	/**
	 * 以高度为基准，等比例缩放图片
	 * @param h int 新高度
	 */
	public void resizeByHeight(int h) throws IOException {
		int w = (int) (width * h / height);
		resize(w, h);
	}
	/**
	 * 强制压缩/放大图片到固定的大小
	 * @param w int 新宽度
	 * @param h int 新高度
	 */
	public void resize(int w, int h) throws IOException {
		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB ); 
		image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		File destFile = new File(outfilePath);
		FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
		// 可以正常实现bmp、png、gif转jpg
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image); // JPEG编码
		out.close();
	}
	
	/**
	 * 强制压缩/放大图片到固定的大小
	 * @param w int 新宽度
	 * @param h int 新高度
	 */
	public void resize(int w, int h,String outfilePath,byte[] imageByte) throws IOException {
		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		InputStream in=new ByteArrayInputStream(imageByte);
		Image img = ImageIO.read(in);  
		BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB ); 
		image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		File destFile = new File(outfilePath+".png");
		FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
		// 可以正常实现bmp、png、gif转jpg
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image); // JPEG编码
		out.close();
//		原图
		File file = new File(outfilePath+"-base.png");// 读入文件
		FileOutputStream t = new FileOutputStream(file); // 输出到文件流
		t.write(imageByte);
		t.close();
	}
}