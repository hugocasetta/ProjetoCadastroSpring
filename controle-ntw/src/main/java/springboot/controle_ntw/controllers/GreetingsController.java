package springboot.controle_ntw.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.controle_ntw.model.Funcionario;
import springboot.controle_ntw.model.FuncionarioComProdutosDTO;
import springboot.controle_ntw.model.Produto;
import springboot.controle_ntw.repository.FuncionarioRespository;
import springboot.controle_ntw.repository.ProdutoRepository;

@RestController
@RequestMapping(value = "/controle-ntw")
public class GreetingsController {

	@Autowired
	ProdutoRepository produtorepository;

	@Autowired
	FuncionarioRespository funcionariorepository;

	@PostMapping(value = "associarProduto")
	@ResponseBody
	public ResponseEntity<String> associarProdutos(@RequestBody Map<String, Long> payload) {
	    Long produtoId = payload.get("produtoId");
	    Long funcionarioId = payload.get("funcionarioId");

	    Funcionario funcionario = funcionariorepository.findById(funcionarioId)
	            .orElseThrow(() -> new RuntimeException("Funcionário não encontrado!"));

	    Produto produto = produtorepository.findById(produtoId)
	            .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

	    produto.setFuncionario(funcionario);
	    produtorepository.save(produto);

	    return new ResponseEntity<>("Produto associado com sucesso", HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "listarProdutosDisponiveis")
	@ResponseBody
	public ResponseEntity<List<Produto>> listarProdutosNaoAssociados() {
	    List<Produto> produtosDisponiveis = produtorepository.findAll()
	            .stream()
	            .filter(produto -> produto.getFuncionario() == null) // Apenas produtos sem funcionário
	            .collect(Collectors.toList());
	    return new ResponseEntity<>(produtosDisponiveis, HttpStatus.OK);
	}

	@DeleteMapping(value = "deletarProduto")
	@ResponseBody
	public ResponseEntity<String> deletarProduto (@RequestBody Long id) {
		
		produtorepository.deleteById(id);
		
		return new ResponseEntity<>("Produto deletado com sucesso", HttpStatus.OK);
	}
	
	@GetMapping(value = "funcionariosComProdutos")
	@ResponseBody
	public ResponseEntity<List<Funcionario>> listarFuncionariosComProdutos() {
	    List<Funcionario> funcionarios = funcionariorepository.findAll();
	    return new ResponseEntity<>(funcionarios, HttpStatus.OK);
	}

	@GetMapping(value = "listarFuncionarios")
	@ResponseBody
	public ResponseEntity<List<Funcionario>> listaDeFuncionario() {

		List<Funcionario> f = funcionariorepository.findAll();

		return new ResponseEntity<>(f, HttpStatus.OK);
	}

	@PostMapping(value = "salvarFuncionario")
	@ResponseBody
	public ResponseEntity<Funcionario> salvarFuncionario(@RequestBody Funcionario funcionario) {

		Funcionario f = funcionariorepository.save(funcionario);
		return new ResponseEntity<Funcionario>(f, HttpStatus.CREATED);
	}

	@PostMapping(value = "salvarProduto")
	@ResponseBody
	public ResponseEntity<Produto> salva(@RequestBody Produto produto) {

		Produto p = produtorepository.save(produto);

		return new ResponseEntity<Produto>(p, HttpStatus.CREATED);
	}

	@GetMapping(value = "listarProdutos")
	@ResponseBody
	public ResponseEntity<List<Produto>> listarprodutos() {

		List<Produto> p = produtorepository.findAll();

		return new ResponseEntity<>(p, HttpStatus.OK);
	}

}
