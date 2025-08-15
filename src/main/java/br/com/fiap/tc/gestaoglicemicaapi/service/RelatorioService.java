package br.com.fiap.tc.gestaoglicemicaapi.service;

import br.com.fiap.tc.gestaoglicemicaapi.dto.RelatorioDTO;
import br.com.fiap.tc.gestaoglicemicaapi.model.Relatorio;

import java.time.LocalDate;

public interface RelatorioService {
  RelatorioDTO montaRelatorio(Long usuarioId, LocalDate dataIni, LocalDate dataFim);
}
