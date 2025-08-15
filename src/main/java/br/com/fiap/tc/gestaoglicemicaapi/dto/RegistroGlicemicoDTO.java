package br.com.fiap.tc.gestaoglicemicaapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record RegistroGlicemicoDTO(
        Long id,
        String titulo,
        @NotNull(message = "Campo obrigatório")
        @Positive(message = "O campo precisa ser positivo")
        double valorGlicemia,
        LocalDate data,
        String observacao,
        @NotNull(message = "Campo obrigatório")
        UsuarioDTO usuario
) {
}
