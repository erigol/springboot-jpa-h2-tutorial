package com.ccsw.tutorial.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

// 1 user has many items. OnetoMany
//@Entity
public class User {
    @Id
    public int id;

    @NonNull
    @NotBlank
    @Column
    public String name;

    @JsonManagedReference
    // @JsonBackReference
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY) // property
    public List<Item> userItems;

    public void addItem(Item item) {
        if (userItems == null)
            userItems = new ArrayList<>();
        userItems.add(item);
    }

}
