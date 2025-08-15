package br.com.fiap.tc.gestaoglicemicaapi.dto;

import java.time.LocalDate;

public record ItemRegistroGlicemicoDTO(
    String titulo,
    double valorGlicemia,
    LocalDate data,
    String observacao
) {
}
