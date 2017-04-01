package com.example.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.db01.CountryMapper;
import com.example.model.Country;
import com.github.pagehelper.PageHelper;

public class CountryService {

	@Resource
	private CountryMapper countryMapper;

	// @Cacheable(value = "country", key = "#c.id")
	public Country findById(Country c) {
		c = this.countryMapper.selectByPrimaryKey(c.getId());
		return c;
	}

	public List<Country> selectAll(int startPage, int pageSize) {
		PageHelper.startPage(startPage, pageSize);
		return this.countryMapper.selectAll();
	}

	// @CachePut(value = "country", key = "#c.id")
	@Transactional
	public int insert(Country c) {
		System.out.println("为 id,key 为:" + c.getId() + "数据做了缓存");
		this.countryMapper.insert(c);
		// int x = 1 / 0;
		System.out.println("为 id,key 为:" + c.getId() + "数据做了缓存");
		return c.getId();
	}

	// @CacheEvict(value = "country")
	public void remove(int id) {
		System.out.println("删除了id" + id + "d数据缓存");
		this.countryMapper.deleteByPrimaryKey(id);
	}
}
