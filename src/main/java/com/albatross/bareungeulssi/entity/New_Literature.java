package com.albatross.bareungeulssi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="new_literature")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class New_Literature {
    @Id
    @Column(name = "nid", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long nid;

    @Column(name="lid", nullable = false)
    private Long lid;

}
