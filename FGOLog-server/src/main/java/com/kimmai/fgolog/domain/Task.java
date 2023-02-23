package com.kimmai.fgolog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kimmai.fgolog.domain.enumeration.TaskStatus;
import java.io.Serializable;
import javax.persistence.*;

/**
 * A Task.
 */
@Entity
@Table(name = "task")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "goal")
    private Integer goal;

    @Column(name = "progress")
    private Integer progress;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;

    @ManyToOne
    private Material material;

    @ManyToOne
    @JsonIgnoreProperties(value = { "tasks" }, allowSetters = true)
    private TaskGroup taskGroup;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Task id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGoal() {
        return this.goal;
    }

    public Task goal(Integer goal) {
        this.setGoal(goal);
        return this;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public Integer getProgress() {
        return this.progress;
    }

    public Task progress(Integer progress) {
        this.setProgress(progress);
        return this;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public TaskStatus getStatus() {
        return this.status;
    }

    public Task status(TaskStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Material getMaterial() {
        return this.material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Task material(Material material) {
        this.setMaterial(material);
        return this;
    }

    public TaskGroup getTaskGroup() {
        return this.taskGroup;
    }

    public void setTaskGroup(TaskGroup taskGroup) {
        this.taskGroup = taskGroup;
    }

    public Task taskGroup(TaskGroup taskGroup) {
        this.setTaskGroup(taskGroup);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        return id != null && id.equals(((Task) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Task{" +
            "id=" + getId() +
            ", goal=" + getGoal() +
            ", progress=" + getProgress() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
