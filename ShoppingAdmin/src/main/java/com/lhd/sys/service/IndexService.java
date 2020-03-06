package com.lhd.sys.service;

import java.util.List;

import com.lhd.sys.entity.Classificationofgoods;


public interface IndexService {
	
	List<Classificationofgoods> findAll();
	
	List<Classificationofgoods> findType () ;
	
}
