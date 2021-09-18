package com.produtos.apivendas.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.produtos.apivendas.model.Vendas;
import com.produtos.apivendas.repository.VendasRepository;
import com.produtos.apivendas.service.ServiceVendas;

@Controller
public class ControllerVendas {

	@Autowired
	VendasRepository vendasRepository;
	@Autowired
	private ServiceVendas serviceVendas;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String formVendas() {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String salvarVendas(Vendas vendas) {

		vendasRepository.save(vendas);

		return "redirect:/register";
	}

	@RequestMapping("/product")
	public ModelAndView listarVEndas() {
		ModelAndView modelAndView = new ModelAndView("product");
		Iterable<Vendas> vendas = vendasRepository.findAll();

		modelAndView.addObject("vendas", vendas);

		return modelAndView;

	}

	@PostMapping("/buscarVendas") // culsatar por busca
	public ModelAndView pesquisar(@RequestParam("buscarVendas") String nomeProduto) {

		ModelAndView modelAndView = new ModelAndView("product");
		modelAndView.addObject("vendas", vendasRepository.findVendasByName(nomeProduto));
		modelAndView.addObject("vendasobj", new Vendas());

		return modelAndView;
	}


	@GetMapping("/barChartVendas")
	public String getAllVendas(Model model) {

		List<String> name = serviceVendas.getAllVendas().stream().map(n -> n.getNomeProduto())
				.collect(Collectors.toList());

		List<Integer> quantidade = serviceVendas.getAllVendas().stream().map(qtd -> qtd.getQuantidade())
				.collect(Collectors.toList());		
		
		model.addAttribute("name", name);
		model.addAttribute("values", quantidade);
		
		return "barChartVendas";

	}
	
	@GetMapping("/barChartModa")
	public String getAllVendasModal(Model model) {

		List<String> name = serviceVendas.getAllVendasModal().stream().map(n -> n.getNomeProduto())
				.collect(Collectors.toList());

		List<Integer> quantidade = serviceVendas.getAllVendasModal().stream().map(qtd -> qtd.getQuantidade())
				.collect(Collectors.toList());

		
		
		model.addAttribute("name", name);
		model.addAttribute("values", quantidade);
		
		return "barChartModa";

	}
	
}
