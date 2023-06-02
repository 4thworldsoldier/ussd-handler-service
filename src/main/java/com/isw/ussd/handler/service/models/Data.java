package com.isw.ussd.handler.service.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
//import jakarta.persistence;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Data_user")
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String bvn;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;
    private String gender;
    private String dob;
    private String nationality;
    private boolean verified;
    private String statusCode;
    private String statusMessage;
    private String requestReference;

    @Override
    public String toString() {
        return " bvn=" + bvn + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName
                + ", phoneNumber=" + phoneNumber + ", gender=" + gender + ", dob=" + dob + ", nationality=" + nationality
                + ", verified=" + verified + ", statusCode=" + statusCode + ", statusMessage=" + statusMessage
                + ", requestReference=" + requestReference + "]";
    }
}
