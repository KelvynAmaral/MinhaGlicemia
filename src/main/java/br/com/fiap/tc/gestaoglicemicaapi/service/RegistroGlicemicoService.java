package br.com.fiap.tc.gestaoglicemicaapi.service;

import br.com.fiap.tc.gestaoglicemicaapi.dto.RegistroGlicemicoDTO;
import br.com.fiap.tc.gestaoglicemicaapi.dto.RegistroGlicemicoMinDTO;
import br.com.fiap.tc.gestaoglicemicaapi.model.RegistroGlicemico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface RegistroGlicemicoService {
  Page<RegistroGlicemicoDTO> listarRegistros(Pageable pageable);

  RegistroGlicemicoDTO buscarPeloId(Long idRegistroGlicemico);

  RegistroGlicemicoDTO criar(RegistroGlicemicoMinDTO registroGlicemico);

  void deletar(Long idRegistroGlicemico);

  RegistroGlicemicoDTO atualizar(Long idRegistroGlicemico, RegistroGlicemicoMinDTO rg);

  List<RegistroGlicemico> registrosDoUsuarioPorData(Long usuarioId, LocalDate dataIni, LocalDate dataFim);

  Page<RegistroGlicemico> registrosDoUsuario(Long usuarioId, Pageable pageable);
}
