package br.com.fiap.tc.gestaoglicemicaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class RegistroGlicemico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @NonNull
    private double valorGlicemia;

    private LocalDate data;

    @NonNull
    private String observacao;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("hibernateLazyInitializer")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; // Chave estrangeira

    @PrePersist
    private void gerarTituloAutomaticoDefinirDataAtual() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM - HH:mm");
        String formattedDate = LocalDateTime.now().format(formatter);

        String tituloAutomatico = "Índice Glicêmico - " + LocalDateTime.now().format(formatter);
        this.titulo = tituloAutomatico;
        this.data = LocalDate.now();
    }
}
