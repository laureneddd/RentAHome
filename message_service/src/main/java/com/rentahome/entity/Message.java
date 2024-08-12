package com.rentahome.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "messages")

public class Message {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int messageId;

    private String senderId;
    private String receiverId;

    @Column(nullable = false, length = 300)
    private String Content;

    @Temporal(TemporalType.DATE)
    private Date time;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
    private List<Reply> replies;

}