package io.github.filipecrodrigues.repository;


import io.github.filipecrodrigues.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
//cliente repositoy vai extender da interface JPA repository, facilitando a manipulação de dados mp banco relacional, sem a necessidade de escrever connsultas sql manualmente
public interface ClienteRepository extends JpaRepository <Cliente, Long>{

}
