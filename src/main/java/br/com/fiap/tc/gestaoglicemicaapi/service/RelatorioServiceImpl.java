package br.com.fiap.tc.gestaoglicemicaapi.service;

import br.com.fiap.tc.gestaoglicemicaapi.dto.ItemRegistroGlicemicoDTO;
import br.com.fiap.tc.gestaoglicemicaapi.dto.RegistroGlicemicoDTO;
import br.com.fiap.tc.gestaoglicemicaapi.dto.RelatorioDTO;
import br.com.fiap.tc.gestaoglicemicaapi.dto.UsuarioDTO;
import br.com.fiap.tc.gestaoglicemicaapi.model.RegistroGlicemico;
import br.com.fiap.tc.gestaoglicemicaapi.model.Relatorio;
import br.com.fiap.tc.gestaoglicemicaapi.model.Usuario;
import br.com.fiap.tc.gestaoglicemicaapi.utils.RegraStatusGlicemico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RelatorioServiceImpl implements RelatorioService{

  @Autowired
  private RegistroGlicemicoService rgService;


  public RelatorioDTO montaRelatorio(Long usuarioId, LocalDate dataIni, LocalDate dataFim) {
    List<RegistroGlicemico> registros = rgService.registrosDoUsuarioPorData(usuarioId, dataIni, dataFim);

    Relatorio relatorio = new Relatorio();
    relatorio.setDataIni(dataIni);
    relatorio.setDataFim(dataFim);
    relatorio.setListaDeRegistros(registros);

    if (registros.isEmpty()) {
      return toDTO(relatorio);
    }

    double valorMedio = mediaRegistrosGlicemicos(registros);

    //TODO ver como atrelar o StatusGlicemico ao valorMédio
    relatorio.setMediaValorGlicemia(valorMedio);
    relatorio.setStatusGlicemia(RegraStatusGlicemico.calculaResultadoGlicemia(valorMedio));
    relatorio.setMaiorValorGlicemia(maiorValorGlicemico(registros));
    relatorio.setMenorValorGlicemia(menorValorGlicemico(registros));

    return toDTO(relatorio);
  }

  private double maiorValorGlicemico(List<RegistroGlicemico> registroGlicemicos) {
    return registroGlicemicos.stream()
            .mapToDouble(RegistroGlicemico::getValorGlicemia)
            .max()
            .orElseThrow(NoSuchElementException::new);
  }

  private double menorValorGlicemico(List<RegistroGlicemico> registroGlicemicos) {
    return registroGlicemicos.stream()
            .mapToDouble(RegistroGlicemico::getValorGlicemia)
            .min()
            .orElseThrow(NoSuchElementException::new);
  }

  private double mediaRegistrosGlicemicos(List<RegistroGlicemico> registroGlicemicos){
    return registroGlicemicos.stream()
            .mapToDouble(RegistroGlicemico::getValorGlicemia)
            .average()
            .orElseThrow(NoSuchElementException::new);
  }

  private RelatorioDTO toDTO(Relatorio relatorio) {
    return new RelatorioDTO(
        relatorio.getStatusGlicemia(),
        relatorio.getDataIni(),
        relatorio.getDataFim(),
        relatorio.getMenorValorGlicemia(),
        relatorio.getMaiorValorGlicemia(),
        relatorio.getMediaValorGlicemia(),
        relatorio.getListaDeRegistros().stream().map(this::toRegistroGlicemicoDTO).toList()
    );
  }

  private ItemRegistroGlicemicoDTO toRegistroGlicemicoDTO(RegistroGlicemico registro) {
    return new ItemRegistroGlicemicoDTO(
        registro.getTitulo(),
        registro.getValorGlicemia(),
        registro.getData(),
        registro.getObservacao()
    );
  }
}
