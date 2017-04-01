package com.example.controller;

import java.util.List;

import javax.annotation.Resource;

import com.example.model.Country;
import com.example.service.CountryService;
/**
 * 操作country模块
 * */
public class CountryController {

	@Resource
	private CountryService countryService;

	public Object getAll() {
		List<Country> list = this.countryService.selectAll(1, 12);
		return list;
	}

	public Object insert() {
		Country c = new Country();
		c.setCountrycode("test");
		c.setCountryname("test");
		int x = this.countryService.insert(c);
		System.out.println(x + "==============================");
		return c;
	}

	public Object getOne(int id) {
		System.out.println(id + "================================");
		Country c = new Country();
		c.setId(id);
		c = this.countryService.findById(c);
		return c;
	}
}
