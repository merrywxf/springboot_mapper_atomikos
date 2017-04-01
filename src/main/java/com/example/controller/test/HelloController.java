package com.example.controller.test;

import java.util.List;

import javax.annotation.Resource;

import com.example.model.Country;
import com.example.service.CountryService;


public class HelloController {
	@Resource
	private CountryService countryService;

	public Object getAll() {
		System.out.println(countryService + "===============================");
		List<Country> list = this.countryService.selectAll(1, 12);
		list.stream().forEach(m -> {
			System.out.println(m.getCountryname() + "================");
		});
		return list;
	}
}
