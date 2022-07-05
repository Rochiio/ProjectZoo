package com.example.zoojava.repositories.animals;

import com.example.zoojava.models.Animal;
import com.example.zoojava.repositories.ICRUD;

public interface AnimalsRepository extends ICRUD<Animal> {
    Animal findById(int id);
    Animal modifyById(int id, Animal newAnimal);
}
