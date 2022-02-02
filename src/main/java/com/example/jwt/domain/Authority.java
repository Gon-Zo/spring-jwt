package com.example.jwt.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority {

  @Id private String name;

  @Builder
  public Authority(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Authority) || Hibernate.getClass(o) != Hibernate.getClass(this))
      return false;

    Authority authority = (Authority) o;

    return Objects.equals(authority.getName(), this.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}
