package com.iacrqq.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class PDFUtil
{
	/**
	 * 
	 * @param pdfFileName
	 * @param pageNumber
	 * @param imageFileName
	 * @return
	 * @throws Exception
	 */
	public static boolean page2Image(String pdfFileName, int pageNumber, String imageFileName) throws IOException
	{
		
		PDDocument doc = null;
		try
		{
			doc = PDDocument.load(pdfFileName);
			PDPage page = (PDPage)doc.getDocumentCatalog().getAllPages().get(pageNumber);
			BufferedImage bImage = page.convertToImage();
			File imageFile = new File(imageFileName);
			ImageIO.write(bImage, FileUtil.getSuffix(imageFileName), imageFile);
			return true;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(doc != null)
			{
				doc.close();
			}
		}
		
		return false;
	}
	
	public static int getPageNumber(String pdfFileName) throws IOException
	{
		PDDocument doc = null;
		try
		{
			doc = PDDocument.load(pdfFileName);
			return doc.getNumberOfPages();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(doc != null)
			{
				doc.close();
			}
		}
		
		return 1000;
	}
	
	public static void main(String[] args)
	{
		try 
		{
			page2Image("/tmp/test.pdf", 0, "/tmp/test.jpg");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
