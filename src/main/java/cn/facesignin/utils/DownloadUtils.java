package cn.facesignin.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class DownloadUtils {
	public ResponseEntity<byte[]> getResponseEntity(String sourcePath, String fileName) throws Exception {

		HttpHeaders headers = new HttpHeaders();//http头信息
		
		String downloadFileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");//设置编码
		
		headers.setContentDispositionFormData("attachment", downloadFileName);
		
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
	    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(sourcePath)), headers, HttpStatus.CREATED);
		
	}
}
