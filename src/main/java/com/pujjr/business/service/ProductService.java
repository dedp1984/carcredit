package com.pujjr.business.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pujjr.business.dao.ProductMapper;
import com.pujjr.business.domain.Product;

@Service
@Transactional(rollbackFor=Exception.class) 
public class ProductService {
	
	@Autowired
	private ProductMapper productDao;
	
	public List<HashMap> getProductNames()
	{
		return productDao.getProductNames();
	}
    
    public List<HashMap> getProductSeqs(String productName)
    {
    	return productDao.getProductSeqs(productName);
    }
    
    public List<HashMap> getProductPeriods(String productName,String productSeq)
    {
    	return productDao.getProductPeriods(productName, productSeq);
    }
    
    public Product getProduct(String productName,String productSeq,int periods)
    {
    	return productDao.getProduct(productName, productSeq, periods);
    }
}
