package com.food.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.domain.model.Cozinha;
import com.food.domain.model.Restaurante;
import com.food.domain.repository.CozinhaRepository;
import com.food.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class testeController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	@Autowired
	private RestauranteRepository restauranteRepository;

	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhaPorNome(String nome) {
		return cozinhaRepository.findTodasBynomeContaining(nome);
	}

	@GetMapping("/cozinhas/unico-por-nome")
	public Optional<Cozinha> cozinhasPorNome(String nome) {
		return cozinhaRepository.findBynome(nome);
	}

	@GetMapping("/cozinhas/exists")
	public Boolean cozinhasExists(String nome) {
		return cozinhaRepository.existsByNome(nome);
	}

	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.queryByTaxaFreteBetween(taxaInicial, taxaFinal);
	}

	@GetMapping("/restaurantes/por-nome")
	public List<Restaurante> restaurantesPorTaxaFrete(String nome, Long cozinhaCodigo) {
		return restauranteRepository.consultarPorNome(nome, cozinhaCodigo);
	}

	@GetMapping("/restaurantes/primeiro-por-nome")
	public Optional<Restaurante> restaurantePrimeironome(String nome) {
		return restauranteRepository.findFirstByNomeContaining(nome);
	}

	@GetMapping("/restaurantes/top2-por-nome")
	public List<Restaurante> restaurantesTop2PorNome(String nome) {
		return restauranteRepository.findTop2ByNomeContaining(nome);
	}
	
	@GetMapping("/restaurantes/por-nome-e-frete")
	public List<Restaurante> restaurantesPorNomeFrete(String nome, 
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}

	@GetMapping("/restaurantes/count-por-cozinha")
	public int restaurantesCounporCozinha(Long cozinhaCodigo) {
		return restauranteRepository.countByCozinhaCodigo(cozinhaCodigo);
	}
}
