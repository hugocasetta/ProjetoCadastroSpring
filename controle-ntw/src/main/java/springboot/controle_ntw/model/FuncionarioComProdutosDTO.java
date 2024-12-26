package springboot.controle_ntw.model;

import java.util.List;

public class FuncionarioComProdutosDTO {

	private String funcionarioNome;
	private List<String> produtos;

	public String getFuncionarioNome() {
		return funcionarioNome;
	}

	public void setFuncionarioNome(String funcionarioNome) {
		this.funcionarioNome = funcionarioNome;
	}

	public List<String> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<String> produtos) {
		this.produtos = produtos;
	}

}
