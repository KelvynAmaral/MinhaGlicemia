package br.com.fiap.tc.gestaoglicemicaapi.service;

import br.com.fiap.tc.gestaoglicemicaapi.dto.RegistroGlicemicoDTO;
import br.com.fiap.tc.gestaoglicemicaapi.dto.RegistroGlicemicoMinDTO;
import br.com.fiap.tc.gestaoglicemicaapi.dto.UsuarioDTO;
import br.com.fiap.tc.gestaoglicemicaapi.exceptionhandler.exception.RecursoNotFoundException;
import br.com.fiap.tc.gestaoglicemicaapi.model.RegistroGlicemico;
import br.com.fiap.tc.gestaoglicemicaapi.model.Usuario;
import br.com.fiap.tc.gestaoglicemicaapi.repository.RegistroGlicemicoRepository;
import br.com.fiap.tc.gestaoglicemicaapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegistroGlicemicoServiceImpl implements RegistroGlicemicoService {

    @Autowired
    private RegistroGlicemicoRepository rgRepository;

    @Autowired
    private UsuarioRepository uRepository;


    @Transactional(readOnly = true)
    @Override
    public Page<RegistroGlicemicoDTO> listarRegistros(Pageable pageable) {
        return rgRepository.findAll(pageable).map(this::toDTO);
    }


    @Transactional(readOnly = true)
    @Override
    public RegistroGlicemicoDTO buscarPeloId(Long idRegistroGlicemico) {
        RegistroGlicemico rg = rgRepository.findById(idRegistroGlicemico).orElseThrow(() -> new RecursoNotFoundException("Registro Glicemico de ID: " + idRegistroGlicemico + " não encontrado"));
        return toDTO(rg);
    }

    @Transactional
    @Override
    public RegistroGlicemicoDTO criar(RegistroGlicemicoMinDTO rgDTO) {
        Usuario usuario  = uRepository.findById(rgDTO.usuarioId()).orElseThrow(() -> new RecursoNotFoundException("Usuário não encontrado com ID: " + rgDTO.usuarioId()));;

        RegistroGlicemico rg = new RegistroGlicemico(
            rgDTO.valorGlicemia(),
            rgDTO.observacao(),
            usuario
            );
        return toDTO(rgRepository.save(rg));
    }

    @Transactional
    @Override
    public void deletar(Long idRegistroGlicemico) {
        rgRepository.deleteById(idRegistroGlicemico);
    }

    @Transactional
    @Override
    public RegistroGlicemicoDTO atualizar(Long idRegistroGlicemico, RegistroGlicemicoMinDTO rg) {
        RegistroGlicemico rgSalvo = rgRepository.findById(idRegistroGlicemico).orElseThrow(() -> new RecursoNotFoundException("Registro Glicemico de ID: " + idRegistroGlicemico + " não encontrado"));

        rgSalvo.setData(rg.data());
        rgSalvo.setTitulo(rg.titulo());
        rgSalvo.setObservacao(rg.observacao());
        rgSalvo.setValorGlicemia(rg.valorGlicemia());

        return toDTO(rgRepository.save(rgSalvo));
    }

    @Transactional(readOnly = true)
    public List<RegistroGlicemico> registrosDoUsuarioPorData(Long usuarioId, LocalDate dataIni, LocalDate dataFim) {
        List<RegistroGlicemico> registrosGlicemicos = rgRepository.findByUsuarioIdAndDataBetween(usuarioId, dataIni, dataFim);
        return registrosGlicemicos;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<RegistroGlicemico> registrosDoUsuario(Long usuarioId, Pageable pageable) {
      return rgRepository.findByUsuarioId(usuarioId, pageable);
    }

    private RegistroGlicemicoDTO toDTO(RegistroGlicemico entity) {
        return new RegistroGlicemicoDTO(
            entity.getId(),
            entity.getTitulo(),
            entity.getValorGlicemia(),
            entity.getData(),
            entity.getObservacao(),
            new UsuarioDTO(
                entity.getUsuario().getNome(),
                entity.getUsuario().getSexo(),
                entity.getUsuario().getIdade(),
                entity.getUsuario().getCategoriaDiabete()
            )
        );
    }
}
