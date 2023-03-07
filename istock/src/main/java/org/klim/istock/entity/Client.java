package org.klim.istock.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
//import org.klim.istock.model.ClientStatus;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_seq")
    @SequenceGenerator(name = "client_id_seq", sequenceName = "client_id_seq", allocationSize = 1)
    private Integer clientId;
//  private ClientStatus status;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}

