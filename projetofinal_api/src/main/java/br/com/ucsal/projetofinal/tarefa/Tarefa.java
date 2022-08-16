package br.com.ucsal.projetofinal.tarefa;

import br.com.ucsal.projetofinal.casoteste.CasoTeste;
import br.com.ucsal.projetofinal.prova.Prova;
import br.com.ucsal.projetofinal.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    @Column(length = 5000)
    private String descricao;

    private Integer status;

    @Valid
    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<CasoTeste> testes = new ArrayList<>();

    @ManyToMany(mappedBy = "tarefas", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Prova> prova;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    @Valid
    @NotNull
    private Usuario usuario;

    public Tarefa(String titulo, String descricao, Integer status, List<CasoTeste> testes, Usuario usuario) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.testes = testes;
        this.usuario = usuario;
    }
}
