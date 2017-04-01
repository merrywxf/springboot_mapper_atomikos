package com.example.service;

import com.example.model.City;

/**
 * Created by Administrator on 2017-03-21.
 */
public interface CityService {
    public City findById(Long id);

    public Long insert(City city);
}
