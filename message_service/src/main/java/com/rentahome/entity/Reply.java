package com.rentahome.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "replys")

public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int replyId;

    @ManyToOne
    @JoinColumn(name = "messageId", nullable = false)
    private Message message;

    private String senderId;
    private String receiverId;

    @Column(nullable = false, length = 300)
    private String content;

    @Temporal(TemporalType.DATE)
    private Date time;
}
