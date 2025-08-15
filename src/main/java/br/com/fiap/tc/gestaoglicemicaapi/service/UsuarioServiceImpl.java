package br.com.fiap.tc.gestaoglicemicaapi.service;

import br.com.fiap.tc.gestaoglicemicaapi.dto.UsuarioDTO;
import br.com.fiap.tc.gestaoglicemicaapi.exceptionhandler.exception.RecursoNotFoundException;
import br.com.fiap.tc.gestaoglicemicaapi.model.RegistroGlicemico;
import br.com.fiap.tc.gestaoglicemicaapi.model.Usuario;
import br.com.fiap.tc.gestaoglicemicaapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RegistroGlicemicoService rgService;

    @Override
    public void validaUsuario(Usuario usuario) {
      Usuario usuarioId = usuarioRepository.findById(usuario.getId()).orElseThrow(() -> new RecursoNotFoundException("Usuário não encontrado com ID: " + usuario.getId()));
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> listaUsuario() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(this::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioDTO buscarPorId(Long usuarioId) {
      Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RecursoNotFoundException("Usuário não encontrado com ID: " + usuarioId));
      return toDTO(usuario);
    }

    @Transactional
    @Override
    public UsuarioDTO criar(UsuarioDTO usuarioBody) {
      return toDTO(usuarioRepository.save(toEntity(usuarioBody)));
    }

    @Transactional
    @Override
    public UsuarioDTO atualizar(Long usuarioId, UsuarioDTO usuario) {
      Usuario usuarioBuscado = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RecursoNotFoundException("Usuário não encontrado com ID: " +usuarioId));
      usuarioBuscado.setNome(usuario.nome());
      usuarioBuscado.setSexo(usuario.sexo());
      usuarioBuscado.setIdade(usuario.idade());
      usuarioBuscado.setCategoriaDiabete(usuario.categoriaDiabete());
      return toDTO(usuarioRepository.save(usuarioBuscado));
    }

    @Transactional
    @Override
    public void deletar(Long usuarioId) {
        Page<RegistroGlicemico> rgPage = rgService.registrosDoUsuario(usuarioId, Pageable.unpaged());
        if (rgPage.hasContent()) {
            rgPage.get().forEach(rg -> {
                rgService.deletar(rg.getId());
            });
        }
        usuarioRepository.deleteById(usuarioId);
    }

    private UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
            usuario.getNome(),
            usuario.getSexo(),
            usuario.getIdade(),
            usuario.getCategoriaDiabete()
        );
    }

  private Usuario toEntity(UsuarioDTO dto) {
    return new Usuario(
        dto.nome(),
        dto.sexo(),
        dto.idade(),
        dto.categoriaDiabete()
    );
  }
}
