package br.edu.unoesc.backend_com_sb.api_rest_controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unoesc.backend_com_sb.model.Funcionario;

@RestController
@RequestMapping(value = "/api")
public class FuncionarioRestController {
	//SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY");
	Funcionario f1 = new Funcionario(1L, "Lucas Paz", 3, new BigDecimal("1550.99"));
	Funcionario f2 = new Funcionario(2L, "Lucas Guerra", 2, new BigDecimal("2333.60"));
	List<Funcionario> funcionarios; // aqui só declaramos que existe a lista.
	
	public FuncionarioRestController() {
		funcionarios = new ArrayList<Funcionario>(); // aqui inicializamos a lista
		
		funcionarios.add(f1);// aqui adicionamos elementos a lista
		funcionarios.add(f2);
	}
	
	// Métodos do CRUD de uma API REST:
	
	// Incluir funcionario
	@PostMapping("/funcionarios")
	public Funcionario salvarFuncionario(@RequestBody Funcionario funcionario) {
		System.out.println("Inserindo um novo funcionario...");
		System.out.println(funcionario);
		
		funcionarios.add(funcionario);
		
		System.out.println(this.listarFuncionarios());
		
		return funcionario;
	}
	
	
	// Alterar funcionario
	@PutMapping("/funcionarios")
	public Funcionario atualizarFuncionario(@RequestBody Funcionario funcionario) {
		Funcionario f = findById(funcionario.getId()); // aqui está buncando o funcionario pelo id
		System.out.println(f); // vai imprimir o funcionario encontrado na memória
		
		f.setNome(funcionario.getNome()); // setando os novos valores digitados pelo usuario
		f.setDependentes(funcionario.getDependentes());
		f.setSalario(funcionario.getSalario());
		
		System.out.println("Atualizando o funcionario...");
		System.out.println(f); // imprimir Funcionario atualizado
		
		System.out.println(this.listarFuncionarios());
		
		return f;
	}
	
	
	// Excluir funcionario                       // para excluir não precisa passar todo o funcionario, apenas o ID.
	@DeleteMapping(value = "/funcionarios/{id}") // PUBLIC VOID POR QUE NÃO VAI RETORNAR NADA. @pathVariable está ligando os IDs
	public void deletarFuncionario(@PathVariable Long id) { // @pathVariable está ligando o ID da URL, com essa variavel id do tipo Long
		Funcionario f = findById(id); //Buscar o produto pelo ID                         
		System.out.println(f); // Imprimir esse funcionario
		
		funcionarios.remove(f); // remover da lista
		
		System.out.println("Excluindo funcionario [" + id + "]...");
		
		System.out.println(this.listarFuncionarios()); //Imprimir a lista atualizada
	}
	
	
	
	//Mostrar funcionarios
	@GetMapping(value = "/funcionarios")
	public List<Funcionario> listarFuncionarios() {
		return funcionarios;
	}
	
	
	//Mostrar Funcionario pelo ID
	@GetMapping(value = "/funcionarios/{id}") // pesquisar/digitar o funcionario na URL pelo ID
	public Funcionario findById(@PathVariable Long id) {   //findById procurar pelo ID. @pathvariable está ligando os IDs.
		for (Funcionario funcionario : funcionarios) { // esse FOR vai percorrer toda lista.
			if (funcionario.getId().equals(id)) {
				return funcionario;
			}
		}
		
		return null; // Se não encontrar vai retornar NULO.
	}
}
