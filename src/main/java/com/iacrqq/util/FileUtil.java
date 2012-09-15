package com.iacrqq.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.docx4j.convert.out.pdf.PdfConversion;
import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFont;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

public class FileUtil
{
	/**
	 * 
	 * @param path
	 * @return
	 */
	public static final String getFileName(String path)
	{
		if(StringUtil.isBlank(path))
		{
			return "";
		}
		
		int index = path.lastIndexOf(File.separator);
		
		if(index == -1)
		{
			return "";
		}
		
		return path.substring(index + 1);
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	public static final String getSuffix(String path)
	{
		if(StringUtil.isBlank(path))
		{
			return "";
		}
		
		int index = path.lastIndexOf(".");
		
		if(index == -1)
		{
			return "";
		}
		
		return path.substring(index + 1);
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	public static final String removeSuffix(String path)
	{
		String suffix = getSuffix(path);
		if(StringUtil.isBlank(suffix))
		{
			return path;
		}
		else
		{
			return path.substring(0, path.indexOf(suffix) - 1);
		}
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static final String read4String(String fileName) throws FileNotFoundException, IOException
	{
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		String line = null;
		try
		{
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			while((line = reader.readLine()) != null)
			{
				sb.append(line);
			}
			
			return sb.toString();
		}
		finally
		{
			if(reader != null)
			{
				reader.close();
			}
		}
	}
	
	public static boolean word2pdf(String source, String dest)
	{
		try 
		{
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new File(source));
			// Set up font mapper
			PhysicalFonts.discoverPhysicalFonts();
			Mapper fontMapper = new IdentityPlusMapper();
			wordMLPackage.setFontMapper(fontMapper);
			
			// Example of mapping missing font Algerian to installed font Comic Sans MS
			/*PhysicalFont font = PhysicalFonts.getPhysicalFonts().get("Comic Sans MS");
			fontMapper.getFontMappings().put("Algerian", font);*/
						
			// As of docx4j 2.5.0, only viaXSLFO is supported.
			// The viaIText and viaHTML source code can be found in src/docx4j-extras directory
						
			PdfConversion pdfConversion = 
//				new org.docx4j.convert.out.pdf.viaHTML.Conversion(wordMLPackage);
				new org.docx4j.convert.out.pdf.viaXSLFO.Conversion(wordMLPackage);
//				new org.docx4j.convert.out.pdf.viaIText.Conversion(wordMLPackage);
			//((Conversion)pdfConversion).setSaveFO(new File("/tmp/" + ".fo"));
			OutputStream os = new FileOutputStream(dest);			
			pdfConversion.output(os, new PdfSettings());
			return true;
		}
		catch(Docx4JException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static void main(String[] args)
	{
		System.out.println(word2pdf("/media/develop/doc/SEDA-Stage Event Driven Application Framework.docx", "/tmp/SEDA-Stage Event Driven Application Framework.docx.pdf"));
	}
}
