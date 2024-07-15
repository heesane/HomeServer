package hhs.server.domain.domain;

import hhs.server.domain.type.UserRole;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="users")
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_email_verification")
    private boolean emailVerification;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

}
