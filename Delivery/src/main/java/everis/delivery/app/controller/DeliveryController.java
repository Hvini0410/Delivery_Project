package everis.delivery.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DeliveryController {

	@GetMapping
	public String principal() {
		return String.format("Bem vindo ao programa de delivery \n"
				+ "Digite '/hello+world' na url para ver o primeiro teste"); 
	}

	@GetMapping("/hello+world")
	public String hello() {
		return "Hello World!";
	}

}

//package projeto.desafio.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//public class HelloController {
//
//	@RequestMapping("/hello")
//	@ResponseBody
//	public String hello() {
//		return "Hello World!";
//	}
//
//	@RequestMapping("/")
//	@ResponseBody
//	public String principal() {
//		return "pagina principal";
//	}
//
//}