package com.hta.studyroom.controller;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hta.studyroom.repository.StudyroomDto;
import com.hta.studyroom.service.StudyroomService;
import com.hta.member.service.MemberService;


@Controller
public class ReservationController {
	private StudyroomService studyroomService;


	public void setStudyroomService(StudyroomService studyroomService) {
		this.studyroomService = studyroomService;
	}

	@RequestMapping(value = "/reservation.study", method=RequestMethod.GET)
	public ModelAndView form(HttpSession session){
		ModelAndView mav = new ModelAndView("/study_room/reservation/step1");
		String member_email = (String)session.getAttribute("email");
		if(member_email==null){
			ModelAndView mav2 = new ModelAndView("/member/login/loginForm");
			mav2.addObject("email", member_email);
			return mav2;
		}
		mav.addObject("email", member_email);
		System.out.println("step1 �씠�룞 ^^"+member_email );
		return mav;
		
	}
	
	
	@RequestMapping(value="/reservation2.study", method=RequestMethod.POST)
	public ModelAndView submit(@ModelAttribute StudyroomDto studyroomdto, HttpSession session, HttpServletRequest req , HttpServletResponse resp){
		Calendar cal = Calendar.getInstance();//�쁽�옱�떆媛�
		String nowyear = String.valueOf(cal.get(Calendar.YEAR));
		int nowmonth = cal.get(Calendar.MONTH)+1;
		int nowday = cal.get(Calendar.DAY_OF_MONTH);	
		System.out.println("�쁽�옱�떆媛꾩�" + nowyear +" , "+ nowmonth +" , "+ nowday);
		
		System.out.println("dto�뿉 ���옣�맂 �궇吏쒕뒗"+studyroomdto.getReservation_date());
		if(studyroomdto.getReservation_date() == ""){
			System.out.println("step1�씠�룞~~~!!");
			ModelAndView mav4 = new ModelAndView("/study_room/reservation/step1");
			String NullValue = "而⑦듃濡ㅻ줈 �씠�룞�븳 ";
			mav4.addObject("NullValueError", NullValue);
			return mav4;
		}
		
		
		
		//�꽑�깮�븳 (臾몄옄�삎)�궇吏� �젙�솗�븳 (�닽�옄)�궇吏쒕줈 蹂��솚�떆�궎湲�(charAt()-->0遺��꽣 �떆�옉)
		int month = studyroomdto.getReservation_date().charAt(6)-48;//�썡 �닽�옄
		int firstday = (studyroomdto.getReservation_date().charAt(8)-48)*10;//�궇吏쒖쓽 �떗�옄由� �닽�옄瑜� 留뚮뱶�뒗 �닔�떇
		int secondday = studyroomdto.getReservation_date().charAt(9)-48;//�궇吏� �씪�옄由� �닽�옄 
		int sumday = (firstday + secondday);//�꽑�깮�븳 �젙�솗�븳 �궇吏�
		String year = studyroomdto.getReservation_date().substring(0, 4);//泥섏쓬 �씤�옄 �닚�꽌 媛믪� 媛��졇媛�怨� 留덉�留� �씤�옄 �닚�꽌�뒗 �쟾�뿉 �씤�옄�닚�꽌瑜� 媛�吏�怨� �삩�떎.
		
		
		/*//Map �삎�떇�쑝濡� ���옣�븯�뿬 addAllObject()濡� page�뿉 蹂쇰궪 �닔�엳�뒗�뜲 �씠�븣 �럹�씠吏��뿉�꽌 EL�깭洹몃줈 異쒕젰�븯�뒗 諛⑸쾿�뒗 洹몃깷 key�씠由� 媛믪쑝濡� 遺덈윭�꽌 �벝�닔 �엳�떎�뒗�뜲 �굹以묒뿉 �빐遊먯빞寃좊떎....
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("day", sumday);
		map.put("month", month);
		map.put("year", new Integer(year));
		System.out.println("Map�뱾�뼱媛��굹�슕??"+ map.get("year")+" , "+map.get("month")+ " , " +map.get("day"));
		*/
		
		List list = new ArrayList();
		list.add(year);//0
		list.add(month);//1
		list.add(sumday);//2
		System.out.println("list 異쒕젰 �뀉�뀆"+ list.get(0));
		System.out.println("�썡!!!!!!!!"+month);
		System.out.println("�씪!!!!!!!!"+firstday + " and " +secondday + " = " + sumday);
		System.out.println("�뀈!!!!!!!!"+year);
		System.out.println("1�삁�빟 ���옣....");
		System.out.println("1�솗�씤"+studyroomdto.getMember_email());
		System.out.println("2�솗�씤"+studyroomdto.getReservation_date());
		System.out.println("3�솗�씤"+studyroomdto.getTable_num());
		System.out.println("4�솗�씤"+studyroomdto.getReservation_time());
		System.out.println("5�솗�씤"+studyroomdto.getClass());
		System.out.println("6�솗�씤"+studyroomdto.getMember_name());
		try {
			
				
					//怨꾩젙�쓣 �꽭�뀡�뿉 ���옣.
				String memberEmail = (String)session.getAttribute("email");
					session.setAttribute("member_email", memberEmail); //session�뿉 �씠硫붿씪媛� ���옣.
					studyroomdto.setMember_email(memberEmail);
						
				String memberName = (String) session.getAttribute("name");
					session.setAttribute("member_name", memberName);
					studyroomdto.setMember_name(memberName);
						
				StudyroomDto dto = studyroomService.findBySameResurvation(studyroomdto);//媛숈� �궇吏쒖뿉 �삁�빟�엳�뒗吏� 李얜뒗�떎.
				
				
				if(nowyear.equals(year) && nowmonth<month){//媛숈� �뀈�룄�뿉 �쁽�옱 �썡蹂대떎 �겢�븣 臾댁“嫄� �엯�젰�쓣 �븳�떎.
							//if(nowday<=sumday){
								//System.out.println("1. write �떆~~~~~~~~�옉!!!");
								//step1.jsp�뿉�꽌 �쟾�떖諛쏆� �뜲�씠�꽣瑜� �뒪�꽣�뵒猷� 醫뚯꽍 �삁�빟 DB�뿉 �엯�젰
								
							//}
							if(dto == null){//dto媛� �꼸�씠 �븘�땲�씪硫� 媛숈� �궇吏쒖뿉 �삁�빟�릺�뼱�엳�뒗寃� �엳�떎�뒗 �쑜
								System.out.println("1. write �떆~~~~~~~~�옉!!!");
								studyroomService.resWrite(studyroomdto);
							}
							else{
								System.out.println("1-1. mav2 �떆~~~~~~~~�옉!!!");
								ModelAndView mav2 = new ModelAndView("/study_room/reservation/step1Error");
								mav2.addObject("list", list);//List �삎�떇�쑝濡� 蹂대궪�븣
								//mav2.addAllObjects(map); //Map �삎�떇�쑝濡� 蹂대궪�븣
								//mav2.addObject("month", month);//�꽑�깮�븳 �썡
								//mav2.addObject("year", year);//�꽑�깮�븳 �뀈�룄
								//mav2.addObject("day", sumday);//�꽑�깮�븳 �젙�솗�궇吏�
								return mav2;
								
							}
				}
				else if(nowyear.equals(year) && nowmonth==month && nowday<=sumday){//媛숈� �뀈�룄�뿉 �쁽�옱�썡媛� 媛숈쑝硫� �쁽�옱 �궇吏쒕낫�떎 �겕嫄곕굹 媛숈쓣�븣 �엯�젰�븳�떎.
							
							if(dto == null){//dto媛� �꼸�씠 �븘�땲�씪硫� 媛숈� �궇吏쒖뿉 �삁�빟�릺�뼱�엳�뒗寃� �엳�떎�뒗 �쑜
							System.out.println("2. write �떆~~~~~~~~�옉!!!");
							//step1.jsp�뿉�꽌 �쟾�떖諛쏆� �뜲�씠�꽣瑜� �뒪�꽣�뵒猷� 醫뚯꽍 �삁�빟 DB�뿉 �엯�젰
							studyroomService.resWrite(studyroomdto);
							}
							else{
								System.out.println("2-1. mav2 �떆~~~~~~~~�옉!!!");
								ModelAndView mav2 = new ModelAndView("/study_room/reservation/step1Error");
								mav2.addObject("list", list);//List �삎�떇�쑝濡� 蹂대궪�븣
								//mav2.addAllObjects(map); //Map �삎�떇�쑝濡� 蹂대궪�븣
								//mav2.addObject("month", month);//�꽑�깮�븳 �썡
								//mav2.addObject("year", year);//�꽑�깮�븳 �뀈�룄
								//mav2.addObject("day", sumday);//�꽑�깮�븳 �젙�솗�궇吏�
								return mav2;
								
							}
				}
				else{
					System.out.println("2. mav2 �떆~~~~~~~~�옉!!!");
					ModelAndView mav2 = new ModelAndView("/study_room/reservation/step1Error");
					mav2.addObject("list", list);//List �삎�떇�쑝濡� 蹂대궪�븣
					//mav2.addAllObjects(map); //Map �삎�떇�쑝濡� 蹂대궪�븣
					//mav2.addObject("month", month);//�꽑�깮�븳 �썡
					//mav2.addObject("year", year);//�꽑�깮�븳 �뀈�룄
					//mav2.addObject("day", sumday);//�꽑�깮�븳 �젙�솗�궇吏�
					return mav2;
					
				}
			
			
		}catch (Exception err) {
			
//			System.out.println("�뒪�꽣�뵒猷� 醫뚯꽍 �삁�빟 遺�遺�:"+ err);
//			System.out.println("3. mav2 �떆~~~~~~~~�옉!!!");
			ModelAndView mav2 = new ModelAndView("/study_room/reservation/step1Error");
			mav2.addObject("list", list);//List �삎�떇�쑝濡� 蹂대궪�븣
			//mav2.addAllObjects(map); //Map �삎�떇�쑝濡� 蹂대궪�븣
			//mav2.addObject("month", month);//�꽑�깮�븳 �썡
			//mav2.addObject("year", year);//�꽑�깮�븳 �뀈�룄
			//mav2.addObject("day", sumday);//�꽑�깮�븳 �젙�솗�궇吏�
			return mav2;
		}
		
		ModelAndView mav = new ModelAndView("/study_room/reservation/step2");
		mav.addObject("studyroomdto", studyroomdto);
		
		
		
		
		
//		System.out.println("�삁�빟 ���옣....");
//		System.out.println("1-2�솗�씤"+studyroomdto.getMember_email());
//		System.out.println("2�솗�씤"+studyroomdto.getReservation_date());
//		System.out.println("3�솗�씤"+studyroomdto.getTable_num());
//		System.out.println("4�솗�씤"+studyroomdto.getReservation_time());
//		System.out.println("5�솗�씤"+studyroomdto.getClass());
//		System.out.println("6�솗�씤"+studyroomdto.getMember_name());
//		System.out.println("6�솗�씤"+studyroomdto.getMember_name());
		return mav;
	}
	
	

}