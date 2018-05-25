package railroad.model;

import railroad.model.enums.UserRoles;

import javax.persistence.*;

@Table(name = "roles")
@Entity
public class Role extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private UserRoles userRole;

    public Role() {
    }

    public Role( UserRoles userRole){
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRoles getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoles userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Role role = (Role) obj;

        return id != null ? id.equals(role.id) : role.id == null;
    }
}