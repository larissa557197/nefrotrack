package br.com.fiap.nefrotrack.exame;


import br.com.fiap.nefrotrack.paciente.Paciente;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExameRenal {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Paciente paciente;

    @NotNull
    private LocalDate data;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoExame tipo;

    @NotNull
    @DecimalMin("0.0")
    private Double resultado; // ex: creatinina (mg/dL)

    @Size(max = 500)
    private String observacao;



}
