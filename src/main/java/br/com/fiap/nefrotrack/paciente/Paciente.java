package br.com.fiap.nefrotrack.paciente;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Past(message = "A data de nascimento deve ser no passado")
    private LocalDate dataNascimento;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "O E-mail é obrigatório")
    @Column(unique = true)
    private String email;

    private String telefone;



}
