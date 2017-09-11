package com.example.temre.appx.event;

import com.example.temre.appx.models.Person;

/**
 * Created by temre on 9.08.2017.
 */

public class PersonEvent {

    Person person;

    public PersonEvent(Person person) {

        this.person = person;

    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
