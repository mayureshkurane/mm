package com.example.onlineExam4;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {
	
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("saveuserdata")
	public ModelAndView saveuserdata(User user) {
	
		Session session=factory.openSession();
		
	   Transaction tx=session.beginTransaction();
		
	   session.save(user);
	  
	   tx.commit();
	   ModelAndView mv= new ModelAndView();
	  
	   mv.setViewName("login");
	  
	   mv.addObject("message", "registration successfully");
	 
	   return mv;
		
	}

}
