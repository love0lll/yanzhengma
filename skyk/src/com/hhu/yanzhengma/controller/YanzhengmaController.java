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
		
		String code = createCode();//生成验证码
		
		HttpSession session = request.getSession();//获取session
		
		session.setAttribute("createdCode", code);
		session.setAttribute("msg", "");//清除验证显示信息

		return "yanzhengma";
	}

	@RequestMapping("/checkCode")
	public String checkCode(HttpServletRequest request,String code){
		HttpSession session = request.getSession();
		String createdCode = (String) session.getAttribute("createdCode");
		if(code == null || "".equals(code.trim())){
			session.setAttribute("msg", "请输入验证码");
		}else if(code.toLowerCase().equals(createdCode.toLowerCase())){
			session.setAttribute("msg", "验证码正确");
		}else{
			session.setAttribute("msg", "验证码不正确");
		}
		
		//重新生成验证码
		String newCode = createCode();
		//保存新的验证码到session中
		session.setAttribute("createdCode",newCode);
		
		return "yanzhengma";
	}
	
	//生成验证码
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
