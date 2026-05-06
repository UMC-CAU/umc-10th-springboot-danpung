package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.FoodCategory;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FoodCategory name;

}
