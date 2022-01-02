package com.example.jwt.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
@NoArgsConstructor
public class Authority {

    @Id
    private String name;

    public String getName() {
        return name;
    }

    @Builder
    public Authority(String name) {
        this.name = name;
    }

}
