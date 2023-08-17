package de.cofinpro.cars.persistence;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "CAR")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "INT")
    private int id;

    @Column(name = "NAME", columnDefinition = "VARCHAR_IGNORECASE(255) UNIQUE NOT NULL")
    private String name;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name="COMPANY_ID", columnDefinition = "INT", nullable=false)
    private Company company;
}
