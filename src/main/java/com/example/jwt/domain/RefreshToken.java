package com.example.jwt.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Table
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

  @Id
  @Column(unique = true, nullable = false)
  private Long userId;

  @Column(columnDefinition = "TEXT" , nullable = false)
  private String refreshToken;

  @Builder
  public RefreshToken(Long userId, String refreshToken) {
    this.userId = userId;
    this.refreshToken = refreshToken;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) return true;

    if (!(o instanceof RefreshToken) || Hibernate.getClass(o) != Hibernate.getClass(this))
      return false;

    RefreshToken that = (RefreshToken) o;

    return Objects.equals(that.userId, this.userId);
  }

  @Override
  public int hashCode() {
    return userId.intValue();
  }

  @Transient
  public void issueToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
