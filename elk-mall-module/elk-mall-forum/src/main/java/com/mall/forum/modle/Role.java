package com.mall.forum.modle;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@ToString
@Table(name = "jforum_roles")
public class Role {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "jforum_roles_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
    @Column(name = "role_id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @JoinTable(name = "jforum_role_values", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "role_value")

    private List<Integer> roleValues = new ArrayList<Integer>();

    public Role() { }

    public Role(Role role) {
        this.id = role.id;
        this.name = role.name;
        this.group = role.group;
        this.roleValues = new ArrayList<Integer>(role.roleValues);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * @return the values
     */
    public List<Integer> getRoleValues() {
        return roleValues;
    }

    /**
     * Add a new role value to this role
     * @param value
     */
    public void addRoleValue(int value) {
        this.roleValues.add(value);
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Role)) {
            return false;
        }

        return ((Role)o).getId() == this.getId();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.getId();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("%d => %s (%s)", this.getId(), this.getName(), this.roleValues);
    }

}
