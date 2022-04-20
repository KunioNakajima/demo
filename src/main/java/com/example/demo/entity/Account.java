package com.example.demo.entity;

import lombok.Data;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.SequenceGenerator;
import org.seasar.doma.jdbc.entity.NamingType;

@Data
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "account_id_seq")
    private Long accountId;
    private String username;
    private String password;
}
