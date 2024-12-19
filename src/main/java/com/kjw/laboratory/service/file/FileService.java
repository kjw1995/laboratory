package com.kjw.laboratory.service.file;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	ResponseEntity convertFileToHexString(MultipartFile file);

}
