package com.example.onlineExam4;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionsController {
	
	@RequestMapping("endexam")
	public ModelAndView endexam(HttpServletRequest request) {
		
		HttpSession httpsession=request.getSession();
		
		HashMap<Integer, Answer> hashmap=(HashMap) httpsession.getAttribute("submittedDetails");
		Collection<Answer>allanswer=hashmap.values();
		
		System.out.println(allanswer);
		
		for (Answer answer : allanswer) {
			if(answer.originalanswer.equals(answer.submittedanswer)) {
				httpsession.setAttribute("score",(Integer)httpsession.getAttribute("score")+1);
			}
		}
		
		ModelAndView mv= new ModelAndView();
		mv.setViewName("score");
		mv.addObject("finalscore",httpsession.getAttribute("score"));
		mv.addObject("allanswer",allanswer);
		

		
		//String name= (String) httpsession.getAttribute("username");
		
		httpsession.invalidate();
		
		return mv;
		
	}

	@RequestMapping("next")
	public ModelAndView next(HttpServletRequest request) {
		
		HttpSession httpsession=request.getSession();
		
		Integer i=(Integer) httpsession.getAttribute("qno");
		
		int nextQno=i+1;
		
		List<Questions> list=(List<Questions>) httpsession.getAttribute("allquestions");
		
		ModelAndView mv= new ModelAndView();
		
		mv.setViewName("questions");
		
		if(nextQno<list.size()) {
		
		Questions question=list.get(nextQno);
		
		mv.addObject("question", question);
		
       httpsession.setAttribute("qno",nextQno );		
		}
		
		else
		{
		     mv.addObject("question", list.get(list.size()-1));
			mv.addObject("message", "click on previous button ");
		}
		return mv;
		
	}
	@RequestMapping("previous")
	public ModelAndView previous(HttpServletRequest request) {
		
		HttpSession httpsession=request.getSession();
		
		Integer i=(Integer) httpsession.getAttribute("qno");
		
		int previousQno=i-1;
		
		List<Questions> list=(List<Questions>) httpsession.getAttribute("allquestions");
		
		ModelAndView mv= new ModelAndView();
		
		mv.setViewName("questions");
		
		if(previousQno>=0) {
		
		Questions question=list.get(previousQno);
		
		mv.addObject("question", question);
		
       httpsession.setAttribute("qno",previousQno );		
		}
		
		else
		{
			mv.addObject("question", list.get(0));

			mv.addObject("message", "click on next button");
		}
		return mv;
		
	}
	@RequestMapping("saveResponse")
	public void saveResponse(Answer answer,HttpServletRequest request) {
		System.out.println(answer);
		
		HttpSession httpsession=request.getSession();
		List<Questions> list=(List<Questions>) httpsession.getAttribute("allquestions");

		for (Questions questions : list) {
			
			if(questions.qno==answer.qno) {
				String ans=questions.answer;
				answer.setOriginalanswer(ans);
				break;
			}
		}
		System.out.println(answer);
		
		HashMap<Integer, Answer> map=(HashMap) httpsession.getAttribute("submittedDetails");
		map.put(answer.qno, answer);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
