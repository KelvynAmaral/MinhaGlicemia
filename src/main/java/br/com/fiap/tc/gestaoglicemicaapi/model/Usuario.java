package br.com.fiap.tc.gestaoglicemicaapi.model;

import br.com.fiap.tc.gestaoglicemicaapi.enums.CategoriaDiabeteEnum;
import br.com.fiap.tc.gestaoglicemicaapi.enums.SexoEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nome;

    @NonNull
    private SexoEnum sexo;

    @NonNull
    private int idade;

    @NonNull
    private CategoriaDiabeteEnum categoriaDiabete;

//    @OneToMany(mappedBy = "usuario")
//    private List<RegistroGlicemico> registrosGlicemicos;
}
