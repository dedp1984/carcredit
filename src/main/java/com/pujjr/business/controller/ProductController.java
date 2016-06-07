package com.pujjr.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

import com.pujjr.business.domain.Product;
import com.pujjr.business.service.ProductService;

@Controller
@RequestMapping("/api/products")
public class ProductController extends BaseController  
{

	@Autowired
	private ProductService productService;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<HashMap> getProductNames()
	{
		return productService.getProductNames();
	}
	
	@RequestMapping(value="/{productname}",method=RequestMethod.GET)
	@ResponseBody
	public List<HashMap> getProductSeqs(@PathVariable String productname)
	{
		return productService.getProductSeqs(productname);
	}
	
	@RequestMapping(value="/{productname}/{productseq}",method=RequestMethod.GET)
	@ResponseBody
	public List<HashMap> getProductPeriods(@PathVariable String productname,@PathVariable String productseq)
	{
		return productService.getProductPeriods(productname, productseq);
	}
	
	@RequestMapping(value="/{productname}/{productseq}/{period}",method=RequestMethod.GET)
	@ResponseBody
	public Product getProduct(@PathVariable String productname,@PathVariable String productseq,@PathVariable String period)
	{
		return productService.getProduct(productname, productseq, Integer.valueOf(period));
	}
	
}
