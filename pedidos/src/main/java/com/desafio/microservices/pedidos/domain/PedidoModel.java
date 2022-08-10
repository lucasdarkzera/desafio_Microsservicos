package com.desafio.microservices.pedidos.domain;

import com.desafio.microservices.pedidos.domain.enumerations.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "pedido_model")
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public LocalDateTime dateTime;
    @Enumerated(EnumType.STRING)
    public StatusPedido orderStatus;
    @ManyToMany(cascade = javax.persistence.CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    @JoinTable(name = "pedido_model_items",
            joinColumns = { @JoinColumn(name = "pedido_model_id") },
            inverseJoinColumns = { @JoinColumn(name = "item_id") }
    )
    public Set<Item> items;

    public Double valor;


    @Override
    public String toString() {
        return "PedidoModel{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", orderStatus=" + orderStatus +
                ", items=" + items +
                '}';
    }
}
