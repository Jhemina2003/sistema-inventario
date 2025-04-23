package com.fhce.inv.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.inv.obj.pcDtoRequest;
import com.fhce.inv.obj.pcDtoResponce;
import com.fhce.inv.service.pcService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf-inv/cpu")
@RequiredArgsConstructor
public class pcController {
	
	private final pcService pcService;
	
	@PostMapping("/addCpu")
	public ResponseEntity<pcDtoResponce> addCpu(@RequestBody pcDtoRequest pcDtoRequest) {
		try {	
			return new ResponseEntity<>(this.pcService.addPc(pcDtoRequest), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
