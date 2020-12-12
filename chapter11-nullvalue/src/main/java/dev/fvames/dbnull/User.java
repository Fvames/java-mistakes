package dev.fvames.dbnull;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Long score;
}
