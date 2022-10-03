package com.example.onlineExam4;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController  {
	
	@Autowired
	SessionFactory factory;
	
	
	@RequestMapping("showLogin")
	public String showLogin() {
		
		return "login";
		
	}
	
	@RequestMapping("showRegister")
	public String showRegister() {
		
		return "register";
		
	}
	@RequestMapping("validate")
	public ModelAndView validate(String username,String password,HttpServletRequest request) {
		
		Session session=factory.openSession();
		
		User user=session.load(User.class, username);
		
		ModelAndView mv= new ModelAndView();
		if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {
		
			HttpSession httpsession=request.getSession();
			httpsession.setAttribute("username", username);
		    mv.setViewName("welcome");
		    mv.addObject("message", "welcome  "+username);
		}
		else {
			mv.setViewName("login"); 
		    mv.addObject("message" ,"invalid credentials");
		}
		   return mv;
		
	}
	
	@RequestMapping("startExam")
	public ModelAndView startExam(String selectsubject,HttpServletRequest request) {
		
		Session session=factory.openSession();
		
		HttpSession httpsession=request.getSession();
		
		httpsession.setAttribute("qno", 0);
	
		Query <Questions>query=session.createQuery("from Questions where subject=:subject");
		
		query.setParameter("subject", selectsubject);
		
		List <Questions>listOfQuestions=query.list();
		
		ModelAndView modelAndView=new ModelAndView();
		
		modelAndView.setViewName("questions");
		
		modelAndView.addObject("listOfQuestions",listOfQuestions);
		
		modelAndView.addObject("question",listOfQuestions.get(0));
		
		httpsession.setAttribute("allquestions", listOfQuestions);

		HashMap<Integer, Answer> hashmap=new HashMap<Integer, Answer>();
		httpsession.setAttribute("submittedDetails", hashmap);
		
		httpsession.setAttribute("score", 0);

		return modelAndView;
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
