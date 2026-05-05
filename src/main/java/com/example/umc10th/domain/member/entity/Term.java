package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.TermType;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "term")
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long termId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TermType name;
}
