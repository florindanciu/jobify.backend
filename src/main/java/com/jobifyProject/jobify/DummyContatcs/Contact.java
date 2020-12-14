package com.jobifyProject.jobify.DummyContatcs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Contact {
    private String id;
    private String name;

    public Contact(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
