package com.shfb.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;

public class ImgUtil {
	public static String rootpath=ConfigManager.getKeyValue("img.rootpath");
	
	public static String imageResize(String srcFilePath, int destW, int destH){
		if("".equals(srcFilePath)){
			return "";
		}
		String destFilePath=getDestFilePath(srcFilePath);
		String srcPath=rootpath+"/"+srcFilePath;
		String destPath=rootpath+"/"+destFilePath;
		BufferedImage bufImageOriginal = new BufferedImage(destW, destH, BufferedImage.TYPE_INT_RGB);
		try {
			bufImageOriginal.getGraphics().drawImage(ImageIO.read(new File(srcPath)), 0, 0, destW, destH, null);
			FileOutputStream fosDestImage = new FileOutputStream(destPath);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fosDestImage);
			JPEGEncodeParam jep = encoder.getDefaultJPEGEncodeParam(bufImageOriginal);
			jep.setQuality(1f, false);
			encoder.setJPEGEncodeParam(jep);
			encoder.encode(bufImageOriginal);
			fosDestImage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFilePath;
	}
	
	public static String getDestFilePath(String srcFilePath){
		int index=srcFilePath.lastIndexOf(".");
		return srcFilePath.substring(0, index)+"s.jpg";
	}
	
	public static void main(String[] args){
		String s="2015/04/20150402133217.png";
		System.out.println(imageResize(s,80,80));
	}
}
