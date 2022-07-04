package com.example.mapstest.DTO;

import lombok.*;

@Setter@Getter@NoArgsConstructor@AllArgsConstructor@ToString
public class Message {
    private String senderName;
    private String receiverName;
    private String message;

}
