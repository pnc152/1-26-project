package com.hta.book.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.hta.book.bookimgdao.BookImgDao;
import com.hta.book.repository.BookDto;
import com.hta.book.service.BookService;




@Controller
public class BookInputController {
	private BookService bookService;

	
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	//book 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙
	@RequestMapping(value="/bookinput", method=RequestMethod.GET)
	public String bookinhandle(){
		
		return "bookinput";
		
	}
	
	//book 異붽� 硫붿꽌�뱶
	@RequestMapping(value="/bookinput", method=RequestMethod.POST)
	public String booksubmit(@ModelAttribute BookDto dto, HttpServletRequest req) throws IOException {
		try{
			MultipartFile file = dto.getUpFile();//uploadDto.getUpFile()�쓽 type�씠 MultipartFile�씠誘�濡� MultipartFile濡� 諛쏆븘以��떎.
			/*
			String path = 
					WebUtils.getRealPath(req.getSession().getServletContext(), "/upload");//WebUtils.getRealPath�뒗 �떎�젣 寃쎈줈瑜� �쟾�떖�빐以��떎. 利�, upload�뤃�뜑�쓽 �쐞移섎�� �븣�븘 �궪�닔 �엳�떎.
					*/
			
			String path = req.getSession().getServletContext().getRealPath("/") + "/upload";
			BookImgDao uploadDao = new BookImgDao();
			uploadDao.writeFile(file, path, file.getOriginalFilename());
			dto.setBook_img(file.getOriginalFilename());
		
			
            try {
				bookService.bookinput(dto);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } 
		catch (IOException e) {
                e.printStackTrace();
		}
		
		return "redirect:bookinput";//책 占쌩곤옙 占쌀곤옙 占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙漫占� 占쌕쏙옙 占쏙옙占쏙옙 占싫댐옙.
		
	}
	
	//占쏙옙체 占쏙옙占� 占쏙옙占쏙옙占쌍깍옙 
	@RequestMapping("/success.book")
	public ModelAndView pageHandler(){
		ModelAndView mav = new ModelAndView("successbook");
		try {
			
			List list = bookService.getList();
			System.out.println("input size :"+list.size());
			mav.addObject("list", list);
		} catch (Exception err) {
			System.out.println("ListController:pageHandler():" + err);
		}
	
		return mav;
		
	}
	
	//占쏙옙占쏙옙 占쏙옙占쏙옙占� 책 占쏙옙占� 占쏙옙占쏙옙占쌍깍옙
	@RequestMapping("/today.book")
	public ModelAndView pageHandler1(){
		ModelAndView mav = new ModelAndView("todaybook");
		try {
				
			List list = bookService.todayList();
				
			mav.addObject("list", list);
		} catch (Exception err) {
			System.out.println("ListController:pageHandler():" + err);
		}
	
		return mav;
			
	}
	
	
}
