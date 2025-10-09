//package com.ankitregmi.ecommerce.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Table(name = "address")
//@Getter
//@Setter
//
//public class Address {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private long id;
//
//    @Column(name = "street")
//    private String street;
//
//    @Column(name = "city")
//    private String city;
//
//    @Column(name = "state")
//    private String state;
//
//    @Column(name = "country")
//    private String country;
//
//    @Column(name = "zip_code")
//    private String zipCode;
//
//    @OneToOne
//    @PrimaryKeyJoinColumn
//    private Order order;
//}

package com.ankitregmi.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "country")
    private String country;

    // Optional: If you want to keep compatibility with existing DB records
    // you can safely comment out or delete the unused fields below:
    //
    // @Column(name = "street")
    // private String street;
    //
    // @Column(name = "city")
    // private String city;
    //
    // @Column(name = "state")
    // private String state;
    //
    // @Column(name = "zip_code")
    // private String zipCode;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Order order;
}
