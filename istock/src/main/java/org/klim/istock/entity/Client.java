package org.klim.istock.entity;

import lombok.Data;
import org.klim.istock.model.ClientStatus;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_seq")
    @SequenceGenerator(name = "client_id_seq", sequenceName = "client_id_seq", allocationSize = 1)
    private Integer clientId;
    @Enumerated(EnumType.STRING)
    private ClientStatus status;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

//    @OneToMany(mappedBy = "client")
//    private Set<Order> orders;
}

