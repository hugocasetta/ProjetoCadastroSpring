package springboot.controle_ntw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springboot.controle_ntw.model.Funcionario;

@Repository
public interface FuncionarioRespository extends JpaRepository<Funcionario, Long> {

}
