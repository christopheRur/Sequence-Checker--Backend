package com.codelab.sequencechecker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Sequence implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tag;

    private String sequence;
    private String inputSequence;

    public Long getId() {
        return id;
    }

    public Sequence setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getTag() {
        return tag;
    }

    public Sequence setTag(Long tag) {
        this.tag = tag;
        return this;
    }

    public String getSequence() {
        return sequence;
    }

    public Sequence setSequence(String sequence) {
        this.sequence = sequence;
        return this;
    }

    public String getInputSequence() {
        return inputSequence;
    }

    public Sequence setInputSequence(String inputSequence) {
        this.inputSequence = inputSequence;
        return this;
    }

    @Override
    public String toString() {
        return "Sequence{" +
                "id=" + id +
                ", tag=" + tag +
                ", sequence='" + sequence + '\'' +
                ", inputSequence='" + inputSequence + '\'' +
                '}';
    }
}
