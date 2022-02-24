package com.albatross.bareungeulssi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="best_literature")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Best_Literature {

    @Id
    @Column(name = "bid", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bid;

    @Column(name = "lid", nullable = false)
    private Long lid;
}
