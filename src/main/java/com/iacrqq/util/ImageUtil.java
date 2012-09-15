package com.iacrqq.util;

import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 
 * @author sihai
 *
 */
public class ImageUtil
{
	/**
	 * 
	 * @param sourceImage
	 * @param targetImage
	 * @param width
	 * @param height
	 * @throws IOException
	 */
	public static void compress(String sourceImage, String targetImage, int width, int height) throws IOException
	{
		Thumbnails.of(sourceImage).width(width).height(height).toFile(targetImage);
	}
	
	public static void main(String[] args)
	{
		try
		{
			compress("/home/sihai/test.jpg", "/home/sihai/target.jpg", 320, 320);
			compress("/home/sihai/test.png", "/home/sihai/target.png", 320, 320);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
