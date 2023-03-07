package org.klim.istock.DTO;

import lombok.Data;
//import org.klim.ishop.model.ClientStatus;

@Data
public class ClientDTO {
    private int clientId;
//    private ClientStatus status;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
