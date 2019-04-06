package br.com.pizzaria.controladores;

import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.pizzaria.modelo.repositorios.PizzaRepositorio;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

	// @RequestMapping("/ola/{nome}")
	// @ResponseBody
	// public String ola(@PathVariable String nome) {
	// return "Olá :" + nome;
	// }
	@Autowired private PizzaRepositorio pizzaRepositorio;
	
	@RequestMapping("/quantas")
	@ResponseBody
	public String quantasPizzas() {
		return "Atualimente  há " + pizzaRepositorio.count() + " cadastradas";
	}
}
