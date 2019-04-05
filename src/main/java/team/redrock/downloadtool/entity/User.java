package team.redrock.downloadtool.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    @Column(name = "identity")
    private int isPrime;

    private String nickname;

    private String phone;

    private String email;

    private String sex;

    private String birthday;

    private String address;

    private String remark;
}
