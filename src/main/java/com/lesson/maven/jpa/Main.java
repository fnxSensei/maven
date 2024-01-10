package com.lesson.maven.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lesson36");
        EntityManager manager=factory.createEntityManager();

        //
        Product product = new Product("title", "description", Product.Category.SPORT);

        manager.getTransaction().begin();//открываем транзакцию
        //managed (persist, update, find)
        manager.persist(product); //entity менеджер взял объект под контроль
        manager.merge(product);

        manager.getTransaction().commit(); // подтверждаем транзакцию

        manager.find(Provider.class, 1); // find работает без транзакции

        manager.remove(product); // удаление из базы и из под управления менеджера
        manager.detach(product); //entity менеджер отпустил объект (удаление из под управления)
        //manager.close();
    }
}
