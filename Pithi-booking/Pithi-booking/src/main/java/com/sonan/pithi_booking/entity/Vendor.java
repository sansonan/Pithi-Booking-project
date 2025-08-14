package com.sonan.pithi_booking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "vendors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Linked to the user account

    @Column(nullable = false)
    private String businessName;

    private String description;

    private String location;

    private BigDecimal rating; // average rating from reviews

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    private Set<Product> products; // services provided by this vendor

    @Override
    public String toString() {
        return "Vendor{" +
                "id=" + id +
                ", user=" + user +
                ", businessName='" + businessName + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", rating=" + rating +
                ", products=" + products +
                '}';
    }
}
