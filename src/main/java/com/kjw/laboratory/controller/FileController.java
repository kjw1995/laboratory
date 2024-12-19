package com.kjw.laboratory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kjw.laboratory.service.file.FileService;
import com.kjw.laboratory.url.LabURL;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FileController {

	private final FileService fileService;

	@GetMapping(LabURL.PAGE_FILE)
	public ModelAndView getFilePage() {

		return new ModelAndView("/file");

	}

	@PostMapping(LabURL.FILE_HEX)
	public ResponseEntity convertFileToHexString(MultipartFile file) {

	}

}
