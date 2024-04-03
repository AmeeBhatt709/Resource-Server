package com.example.resourceServer.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="privillege")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ColumnDefault("false")
    @Column(name = "read_access", nullable = false)
    private Boolean readAccess;

    @ColumnDefault("false")
    @Column(name = "write_access", nullable = false)
    private Boolean writeAccess;

    @ColumnDefault("false")
    @Column(name = "remove_access", nullable = false)
    private Boolean removeAccess;

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_role_id", referencedColumnName = "id")
    private RoleUSerMapping roleUSerMapping;
}
