package com.mateusz.library.library.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mateusz.library.library.commonTypes.UserRole;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users", schema = "library")
public class UserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @Basic
    private String name;

    @Column(name = "email")
    @Basic
    private String email;

    @Column(name = "fullName")
    @Basic
    private String fullName;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AuthEntity auth;

    @JsonBackReference
    @OneToMany(mappedBy = "user")
    private List<LoanEntity> loans;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AuthEntity getAuth() {
        return auth;
    }

    public void setAuth(AuthEntity auth) {
        this.auth = auth;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<LoanEntity> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanEntity> loans) {
        this.loans = loans;
    }
}
