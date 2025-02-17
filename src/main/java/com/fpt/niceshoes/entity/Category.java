package com.fpt.niceshoes.entity;

import com.fpt.niceshoes.entity.base.PrimaryEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Nationalized;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "category")
public class Category extends PrimaryEntity {
    @Nationalized
    @Column(name = "name")
    @NotNull(message = "Tên không được để trống!")
    private String name;
}
