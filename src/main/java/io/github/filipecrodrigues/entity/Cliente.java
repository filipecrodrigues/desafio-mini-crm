package io.github.filipecrodrigues.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity //indica uma tabela no banco de dados
@AllArgsConstructor // criar contrutor com arqumentos
@NoArgsConstructor //criar contrutor sem arquimentos
@Getter //criar os getters
@Setter // criar os settters
@Table(name ="clientes") //trocar o nome da tabela para clientes
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//par auto incrementar o id no db
    private Long id;

    private String nome;

    private String email;

    /*one to many = um cliente possue varios contatos
    *mappedBy - Os contatos guardam quem é o cliente dono deles
    *cascade - Tudo que acontece com o cliente acontece com o contato
    *orphanRemoval -  se o cleinte é removido os contatos orfãos seram removidos
    *fetch EAGER -  a busca pelo cliente já tras todos os seus contatos
    * */

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)

    //atributo da classe to tipo Lista que representa a lista de contatos de um cliente
    //Ela gurada varios objetos do tipo contato dentro de um cliente

    @JsonManagedReference /*Marca o lado “pai” da relação.
    Diz ao Jackson: “Quando serializar, inclua este campo normalmente, mas cuidado com loops.”*/
    private List<Contato> contatos = new ArrayList<>();
}
