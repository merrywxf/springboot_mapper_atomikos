package com.example.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.City;
import com.example.service.CityService;
import com.example.util.UploadFile;

/**
 * Created by Administrator on 2017-03-21.
 */

public class CityController {
	@Resource
	private CityService cityService;

	public Object findById(Long id) {
		System.out.println(id + "==========");
		City city = this.cityService.findById(id);
		return city;
	}

	public Object insert() {
		City city = new City();
		city.setName("test");
		city.setState("test");
		Long x = this.cityService.insert(city);
		return x;
	}

	public Object updateFileUpload(@RequestParam("file") MultipartFile[] upfiles) {
		UploadFile.upload(upfiles, "D://", "upload//");
		return "OK";
	}
}
