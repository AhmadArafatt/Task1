package com.test.testpro.model;

import lombok.*;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor

public class Car {
    @Id
    @GeneratedValue
    private Long id;


    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false)
    private int year;


    @NonNull
    @Column(nullable = false)
    private String color;

    @NonNull
    @Column(nullable = false)
    private boolean rent;

    @NonNull
    @Column(nullable = false)
    private String rentBy;

    @NonNull
    @Column(nullable = false)
    private String rentalPeriod;
//    @Version
//    public int version;

    public Car(Long id, @NonNull String name, @NonNull int year, @NonNull String color, @NonNull boolean rent, @NonNull String rentBy, @NonNull String rentalPeriod) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.color = color;
        this.rent = rent;
        this.rentBy = rentBy;
        this.rentalPeriod = rentalPeriod;
    }


    //    @ManyToMany(fetch = FetchType.EAGER)//EAGER ===> inested of lazely loading the roles we just went to group them all
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
//    ) // join table ==> setting a new table for users and roles (third table gets created)
//    private Set<Role> roles = new HashSet<>();



//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for (Role role : roles) authorities.add(new SimpleGrantedAuthority(role.getName()));
//
//        return authorities;
//    }//returning a list of authorities and spring security needs to know about this



}
