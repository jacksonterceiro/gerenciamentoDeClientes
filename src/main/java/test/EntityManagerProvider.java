package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EntityManagerProvider {

    private final EntityManagerFactory factory;

    public EntityManagerProvider(String database) {
        this.factory = Persistence.createEntityManagerFactory(database);
    }

    public EntityManagerFactory getFactory() {
        return factory;
    }

    public EntityManager createManager() {
        return factory.createEntityManager();
    }
}
