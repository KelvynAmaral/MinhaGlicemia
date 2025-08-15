package br.com.fiap.tc.gestaoglicemicaapi.repository;

import br.com.fiap.tc.gestaoglicemicaapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
