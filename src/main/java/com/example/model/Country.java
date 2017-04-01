package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Country implements Serializable {
    /**
     * ����
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * ����
     */
    private String countryname;

    /**
     * ����
     */
    private String countrycode;

    /**
     * ��ȡ����
     *
     * @return Id - ����
     */
    public Integer getId() {
        return id;
    }

    /**
     * ��������
     *
     * @param id ����
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * ��ȡ����
     *
     * @return countryname - ����
     */
    public String getCountryname() {
        return countryname;
    }

    /**
     * ��������
     *
     * @param countryname ����
     */
    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    /**
     * ��ȡ����
     *
     * @return countrycode - ����
     */
    public String getCountrycode() {
        return countrycode;
    }

    /**
     * ���ô���
     *
     * @param countrycode ����
     */
    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }
}