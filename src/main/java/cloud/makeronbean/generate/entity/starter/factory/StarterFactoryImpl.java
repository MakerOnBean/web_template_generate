package cloud.makeronbean.generate.entity.starter.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author makeronbean
 * @createDate 2023-05-01  19:24
 * @description
 */

public class StarterFactoryImpl implements StarterFactory {
    
    
    @Override
    public <T> T getInstance(Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
