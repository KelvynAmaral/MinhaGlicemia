package br.com.fiap.tc.gestaoglicemicaapi.dto;

import br.com.fiap.tc.gestaoglicemicaapi.enums.StatusGlicemicoEnum;

import java.time.LocalDate;
import java.util.List;

public record RelatorioDTO(
        StatusGlicemicoEnum statusGlicemia,
        LocalDate dataIni,
        LocalDate dataFim,
        double menorValorGlicemia,
        double maiorValorGlicemia,
        double mediaValorGlicemia,
        List<ItemRegistroGlicemicoDTO> listaDeRegistro
) {
}
