package com.test.Mcq.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@EqualsAndHashCode
@Entity
@Table(name ="question")
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(length = 5000,unique = true)
    private String question;
    @Column(length = 5000)
    private String answer;
    private int value;
    private Instant airdate;
    private Instant created_at;
    private Instant updated_at;
    private long game_id;
    private String invalid_count;
    @ManyToOne(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    private Category category;

}
