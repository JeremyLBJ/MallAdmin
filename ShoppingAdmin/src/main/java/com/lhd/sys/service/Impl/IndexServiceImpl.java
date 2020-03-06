package com.lhd.sys.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhd.sys.dao.ClassificationofgoodsMapper;
import com.lhd.sys.entity.Classificationofgoods;
import com.lhd.sys.service.IndexService;


@Service("IndexService")
public class IndexServiceImpl implements IndexService{
	
	private Logger logger = LoggerFactory.getLogger(getClass());	
	
	@Autowired
	private ClassificationofgoodsMapper classificationofgoodsMapper;
	
	public List<Classificationofgoods> findAll () {
		
		logger.info("synchroEnterprises.start =====>> ");
		
		List<Classificationofgoods> list = classificationofgoodsMapper.selectByExample(null);
		return list;
	}
	
	
	
	public List<Classificationofgoods> findType () {
		List<Classificationofgoods> lists = classificationofgoodsMapper.selectByExample(null);
		List<Classificationofgoods> list = new ArrayList<Classificationofgoods>() ;
		List<Integer> mun = new ArrayList<>() ; 
		Random ram = new Random() ;
		int i = 0 ;
		for ( int j = 0 ; j < lists.size() ; j++ ) {
			i = ram.nextInt(10) ;
			if( ! mun.contains(i) ) {
				mun.add(i) ;
				list.add(lists.get(i)) ; 
				if( mun.size() == 3 ) {
					return list ; 
				}
			}
		}
		return list ; 
	}


}
