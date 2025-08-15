package br.com.fiap.tc.gestaoglicemicaapi.model;

import br.com.fiap.tc.gestaoglicemicaapi.enums.StatusGlicemicoEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class Relatorio {

    private StatusGlicemicoEnum statusGlicemia;

    private LocalDate dataIni;

    private LocalDate dataFim;

    private double menorValorGlicemia;

    private double maiorValorGlicemia;

    private double mediaValorGlicemia;

    private List<RegistroGlicemico> listaDeRegistros;
}
