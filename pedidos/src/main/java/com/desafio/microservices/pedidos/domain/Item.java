package com.desafio.microservices.pedidos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Min(value = 1, message = "Only positive or > 1 ")
    public Integer quantity;
    public String desc;

//    @ManyToMany(cascade = javax.persistence.CascadeType.PERSIST,
//            mappedBy = "items",
//            fetch = FetchType.EAGER
//    )


    private double preco;

}
