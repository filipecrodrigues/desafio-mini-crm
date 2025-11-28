package io.github.filipecrodrigues.repository;

import io.github.filipecrodrigues.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
