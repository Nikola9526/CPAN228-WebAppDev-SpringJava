package com.humber.Week11_SpringProfiles.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
public record Animal(@Id int id, String name) {}
// difference of record and class is there 'immutable' can not change it after it is initialized can not USE set,
// does get by itself

// not creating DB no need for repo
// next create config package