package com.gear.testSpringMVC.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gear.testSpringMVC.Servces.RentThemselvesService;

@Controller
public class HomeController {
	
	@Autowired
	private RentThemselvesService rentThemselvesService;	

	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException {
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/flats")
	public ModelAndView getFlatResources(HttpServletResponse response) {
		String data = null;
		try {
			data = rentThemselvesService.getResourceData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("flats_resources", "data", data);
	}
}
