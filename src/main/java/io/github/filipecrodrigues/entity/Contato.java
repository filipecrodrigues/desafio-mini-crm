package io.github.filipecrodrigues.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="contatos")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String tipo;
    private String valor;

    //N:1  muitos contatos pertencem a 1 cliente
    //LAZY: Pega o arquivo do cliente, contatos ficam guardados. Só abre quando precisar.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id") //Cria a coluna cliente_id na tabela de contatos para guardar a FK
    @JsonBackReference //Serve para evitar loops infinitos quando você tem relações bidirecionais entre entidades.
    //Ou seja, no JSON, você verá os contatos do cliente, mas cada contato não vai tentar serializar o cliente de volta.
    private Cliente cliente; //Variável que referencia o cliente do contato


    /*✅ Serializar
    Serializar significa transformar um objeto em um formato que pode ser armazenado ou enviado.
    Em Java e APIs, isso geralmente significa converter objetos em JSON, XML ou bytes.

    ✅ Desserializar
    É o processo inverso:
    Pega o JSON e transforma de volta em objeto Java.
    * */

}
