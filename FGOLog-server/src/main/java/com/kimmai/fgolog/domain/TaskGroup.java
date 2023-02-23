package com.kimmai.fgolog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A TaskGroup.
 */
@Entity
@Table(name = "task_group")
public class TaskGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "taskGroup")
    @JsonIgnoreProperties(value = { "material", "taskGroup" }, allowSetters = true)
    private Set<Task> tasks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TaskGroup id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public TaskGroup name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(Set<Task> tasks) {
        if (this.tasks != null) {
            this.tasks.forEach(i -> i.setTaskGroup(null));
        }
        if (tasks != null) {
            tasks.forEach(i -> i.setTaskGroup(this));
        }
        this.tasks = tasks;
    }

    public TaskGroup tasks(Set<Task> tasks) {
        this.setTasks(tasks);
        return this;
    }

    public TaskGroup addTask(Task task) {
        this.tasks.add(task);
        task.setTaskGroup(this);
        return this;
    }

    public TaskGroup removeTask(Task task) {
        this.tasks.remove(task);
        task.setTaskGroup(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskGroup)) {
            return false;
        }
        return id != null && id.equals(((TaskGroup) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaskGroup{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
