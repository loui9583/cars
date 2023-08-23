package dat3.car.cars.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Member {


    @Id
    private String username;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_first_name")
    private String firstName;

    @Column(name = "user_last_name")
    private String lastName;

    @Column(name = "user_street")
    private String street;

    @Column(name = "user_city")
    private String city;

    @Column(name = "user_zip")
    private String zip;


    @CreationTimestamp
    private LocalDate createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public Member() {
    }

    public Member(String user, String password, String email, String firstName,
                  String lastName, String street, String city, String zip) {
        this.username = user;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }
}
