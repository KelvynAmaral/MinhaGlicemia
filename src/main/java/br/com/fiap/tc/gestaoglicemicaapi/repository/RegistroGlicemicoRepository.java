package br.com.fiap.tc.gestaoglicemicaapi.repository;

import br.com.fiap.tc.gestaoglicemicaapi.model.RegistroGlicemico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RegistroGlicemicoRepository extends JpaRepository<RegistroGlicemico, Long> {

        @Query(value = "SELECT rg FROM RegistroGlicemico rg JOIN FETCH rg.usuario WHERE rg.usuario.id = :usuarioId",
        countQuery = "SELECT COUNT(rg) FROM RegistroGlicemico rg JOIN rg.usuario")
        Page<RegistroGlicemico> findByUsuarioId(Long usuarioId, Pageable pageable);

        /**
         *  Com essa query, tocamos o banco de dados apenas uma vez, assim como a query de cima faz. Recomendo deixar para melhorar a performance.
         *  Por mais que ela faça só uma query a mais pq em ambas só trazemos 1 usuário, ainda continuo achando válido. Mas vamos discutir para comentar
         *  sobre :D
         *
         * @Query("SELECT rg FROM RegistroGlicemico rg JOIN FETCH rg.usuario " +
         *     "WHERE rg.usuario.id = :usuarioId " +
         *    "AND rg.data BETWEEN :dataIni AND :dataFim")
         **/
        List<RegistroGlicemico> findByUsuarioIdAndDataBetween(Long usuarioId, LocalDate dataIni, LocalDate dataFim);
}
