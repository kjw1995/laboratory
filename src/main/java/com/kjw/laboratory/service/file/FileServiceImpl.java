package com.kjw.laboratory.service.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

	@Override
	public ResponseEntity convertFileToHexString(MultipartFile multipartFile) {

		if (multipartFile == null || multipartFile.isEmpty()) {
			log.warn("Empty or null file submitted.");
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		String desktopPath = Paths.get(System.getProperty("user.home"), "Desktop").toString();
		File outputFile = new File(desktopPath, multipartFile.getOriginalFilename() + "_pdfHex.txt");

		try (InputStream is = multipartFile.getInputStream();
			 FileOutputStream fos = new FileOutputStream(outputFile)) {
			byte[] buffer = new byte[4096]; // 4KB 크기의 버퍼
			int bytesRead;

			log.info("파일 변환 시작");

			while ((bytesRead = is.read(buffer)) != -1) {
				StringBuilder hexString = new StringBuilder();
				for (int i = 0; i < bytesRead; i++) {
					hexString.append(String.format("%02X", buffer[i]));
				}
				fos.write(hexString.toString().getBytes());
			}

			log.info("파일 변환 완료");
			return new ResponseEntity(HttpStatus.OK);


		} catch (IOException e) {
			log.error("파일 변환 중 오류 발생", e);
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
