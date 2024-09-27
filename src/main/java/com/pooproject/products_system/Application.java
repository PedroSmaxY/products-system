package com.pooproject.products_system;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    public static void main(String[] args) {
        System.out.println("olá mundo!");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }
}
