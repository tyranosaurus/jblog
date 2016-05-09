package com.estsoft.jblog.service;

import java.io.FileOutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.estsoft.jblog.dao.BlogDao;
import com.estsoft.jblog.vo.BlogVo;
import com.estsoft.jblog.vo.UserVo;

@Service
public class BlogService 
{
	
	@Autowired
	private BlogDao blogDao;
	
	private static final String SAVE_PATH = "/temp";
	
	public void insert(UserVo vo)
	{
		blogDao.insert(vo);
	}
	
	public BlogVo getBlog(String userId)
	{

		BlogVo blogVo = blogDao.getBlog(userId);
		
		return blogVo;
	}
	
	public void basicModify(String userId, String title, String imageUrl)
	{
		blogDao.basicModify(userId, title, imageUrl);
	}
	
	public String upload(MultipartFile logoFile)
	{

		if( logoFile.isEmpty() == false ) {
			
	        String fileOriginalName = logoFile.getOriginalFilename();
	        String extName = fileOriginalName.substring( fileOriginalName.lastIndexOf(".") + 1, fileOriginalName.length() );  // .확장자
	        String saveFileName = genSaveFileName( extName );   // 파일 이름 새로 만들어줌 
	        
	        writeFile( logoFile, SAVE_PATH, saveFileName );
	        
	        String imageUrl = "/jblog/product-images/" + saveFileName;
	        return imageUrl;
		}
		
        return "";
	}
	
	
	public void writeFile( MultipartFile file, String path, String fileName ) {
		FileOutputStream fos = null;
		try {
			byte fileData[] = file.getBytes();
			fos = new FileOutputStream( path + "/" + fileName );
			fos.write(fileData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
		}
	}
	
	public String genSaveFileName( String extName ) {
		
        Calendar calendar = Calendar.getInstance();
		String fileName = "";
        
        fileName += calendar.get( Calendar.YEAR );
        fileName += calendar.get( Calendar.MONTH );
        fileName += calendar.get( Calendar.DATE );
        fileName += calendar.get( Calendar.HOUR );
        fileName += calendar.get( Calendar.MINUTE );
        fileName += calendar.get( Calendar.SECOND );
        fileName += calendar.get( Calendar.MILLISECOND );
        fileName += ( "." + extName );
        
        return fileName;
	}
}
