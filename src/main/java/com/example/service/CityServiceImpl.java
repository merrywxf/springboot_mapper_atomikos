package com.example.service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.db01.CityMapper;
import com.example.model.City;

/**
 * Created by Administrator on 2017-03-21.
 */
public class CityServiceImpl implements CityService {
	@Resource
	private CityMapper cityMapper;

	@Override
	public City findById(Long id) {
		return this.cityMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public Long insert(City city) {
		this.cityMapper.insert(city);
		// int x = 1 / 0;
		return city.getId();
	}
}
