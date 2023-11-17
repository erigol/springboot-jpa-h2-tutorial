package com.ccsw.tutorial.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Entity
public class Item {
    @Id
    @GeneratedValue
    @NotNull
    public int id;

    @NotNull
    @NotBlank
    public String itemName;

    @JsonBackReference // not deserialised, com @JsonIgnore
    // @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    public User owner;

}
