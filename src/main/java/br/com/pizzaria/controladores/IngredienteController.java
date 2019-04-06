package br.com.pizzaria.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pizzaria.modelo.entidades.Ingrediente;
import br.com.pizzaria.modelo.enumeracoes.CategoriaDeIngrediente;
import br.com.pizzaria.modelo.repositorios.IngredienteRepositorio;

@Controller
@RequestMapping("/ingredientes")
public class IngredienteController {

	@Autowired
	private IngredienteRepositorio ingredienteRepositorio;

	@RequestMapping(method = RequestMethod.GET)
	public String listarIngredientes(Model model) {
		//Listagem
		Iterable<Ingrediente> ingredientes = ingredienteRepositorio.findAll();
		model.addAttribute("titulo", "Listagem de ingredientes");
		model.addAttribute("ingredientes", ingredientes);
		//Retorna array/combobox
		model.addAttribute("categoria", CategoriaDeIngrediente.values());
		return "ingrediente/listagem";

	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvarIngrediente(@Valid @ModelAttribute Ingrediente ingrediente, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			//Retorna o error
			FieldError error = bindingResult.getFieldErrors().get(0);
			redirectAttributes.addFlashAttribute("mensagemErro",
					"Não foi possivel salvar o Ingrediente" + error.getField() + " " + error.getDefaultMessage());
		} else {
			//Salva ingredientes
			ingredienteRepositorio.save(ingrediente);
			//Mensagem informativo
			redirectAttributes.addFlashAttribute("mensagemInfo", "O ingrediente salvo corretamente");
		}
		return "redirect:/app/ingredientes";
	}
}
