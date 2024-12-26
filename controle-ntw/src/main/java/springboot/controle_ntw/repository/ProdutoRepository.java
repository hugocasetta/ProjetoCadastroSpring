package springboot.controle_ntw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springboot.controle_ntw.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
