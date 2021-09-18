package com.produtos.apivendas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.produtos.apivendas.model.Vendas;
import com.produtos.apivendas.repository.VendasRepository;

@Service
public class ServiceVendas {

	@Autowired
	private VendasRepository vendasRepository;

	int qtdVendas = 0;
	String nome = "";

	List<Vendas> vndGroup = new ArrayList<>();

	public boolean temList(String nome) {
		for (Vendas vendas : vndGroup) {
			if (vendas.getNomeProduto().equalsIgnoreCase(nome)) {
				return true;
			}
		}
		return false;
	}

	public String salvarVendas(Vendas vendas) {

		vendasRepository.save(vendas);
		return "saved Vendas Reource";
	}

	// listar todas as vendas
	public List<Vendas> getAllVendas() {

		return vendasRepository.findAll();
	}

	// retornar vendas modo
	public List<Vendas> getAllVendasModal() {

		ModelAndView modelAndView = new ModelAndView("index");
		Iterable<Vendas> vendas = vendasRepository.findAll();

		modelAndView.addObject("vendas", vendas);

		List<Vendas> vnd = new ArrayList<>();
		for (Vendas vendas2 : vendas) {

			vnd.add(vendas2);
		}
		for (Vendas vendas2 : vnd) {

			if (vendas2.getNomeProduto().equalsIgnoreCase("")) {
				nome = vendas2.getNomeProduto();
				qtdVendas += vendas2.getQuantidade();
			}
		}
		// Variaveis auxiliares
		String nomeProduto = "";
		int quantidade = 0;
		for (Vendas vendasG : vnd) {

			for (Vendas vn : vnd) {
				
				if (vendasG.getNomeProduto().equalsIgnoreCase(vn.getNomeProduto())) {

					nomeProduto = vn.getNomeProduto();
					quantidade += vn.getQuantidade();
				}
			}
			
			if (!temList(nomeProduto) && nomeProduto != "") {
				Vendas v = new Vendas();
				v.setNomeProduto(nomeProduto);
				v.setQuantidade(quantidade);
				vndGroup.add(v);
			}

			nomeProduto = "";
			quantidade = 0;

		}

		return vndGroup;
	}

}