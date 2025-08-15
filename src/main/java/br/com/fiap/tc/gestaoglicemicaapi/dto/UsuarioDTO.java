package br.com.fiap.tc.gestaoglicemicaapi.dto;

import br.com.fiap.tc.gestaoglicemicaapi.enums.CategoriaDiabeteEnum;
import br.com.fiap.tc.gestaoglicemicaapi.enums.SexoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UsuarioDTO(
        @NotBlank(message = "Campo obrigatório")
        String nome,
        SexoEnum sexo,
        @NotNull(message = "Campo obrigatório")
        @Positive(message = "Campo não pode ter valor negativo")
        int idade,
        @NotNull(message = "Campo obrigatório")
        CategoriaDiabeteEnum categoriaDiabete
) {
}
