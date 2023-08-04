package tech.beanmak1r.generate.starter.base.factory;

import tech.beanmak1r.generate.starter.base.starter.AbstractStarter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author makeronbean
 * @createDate 2023-05-01  19:24
 * @description 单例简单工厂实现
 */

public class StarterFactorySingleImpl implements StarterFactory {
    
    Set<AbstractStarter> starterSet = new HashSet<>();

    private static StarterFactory starterFactory;

    public static StarterFactory getStarterFactory() {
        if (starterFactory == null) {
            starterFactory = new StarterFactorySingleImpl();
        }
        return starterFactory;
    }

    private StarterFactorySingleImpl() {}
    
    public <T extends AbstractStarter> T getInstance(Class<T> clazz) {
        try {
            T result;
            Optional<AbstractStarter> starterOptional = starterSet.stream().filter(item -> item.getClass().equals(clazz)).findFirst();
            if (!starterOptional.isPresent()) {
                Constructor<T> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                result = constructor.newInstance();
                starterSet.add(result);
            } else {
                result = (T) starterOptional.get();
            }
            return result;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
