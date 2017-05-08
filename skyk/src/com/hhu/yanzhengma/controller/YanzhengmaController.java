package com.hhu.yanzhengma.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class YanzhengmaController {

	@RequestMapping("/yanzhengma")
	public String createCode(HttpServletRequest request)
	{
		
		String code = createCode();//������֤��
		
		HttpSession session = request.getSession();//��ȡsession
		
		session.setAttribute("createdCode", code);
		session.setAttribute("msg", "");//�����֤��ʾ��Ϣ

		return "yanzhengma";
	}

	@RequestMapping("/checkCode")
	public String checkCode(HttpServletRequest request,String code){
		HttpSession session = request.getSession();
		String createdCode = (String) session.getAttribute("createdCode");
		if(code == null || "".equals(code.trim())){
			session.setAttribute("msg", "��������֤��");
		}else if(code.toLowerCase().equals(createdCode.toLowerCase())){
			session.setAttribute("msg", "��֤����ȷ");
		}else{
			session.setAttribute("msg", "��֤�벻��ȷ");
		}
		
		//����������֤��
		String newCode = createCode();
		//�����µ���֤�뵽session��
		session.setAttribute("createdCode",newCode);
		
		return "yanzhengma";
	}
	
	//������֤��
	public  String createCode(){
		String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String code="";
		for(int i=0;i<4;i++)
		{
			int idx=(int)Math.floor(Math.random()*62);
			code += String.valueOf(str.charAt(idx));
		}
		
		return code;
	}
}
