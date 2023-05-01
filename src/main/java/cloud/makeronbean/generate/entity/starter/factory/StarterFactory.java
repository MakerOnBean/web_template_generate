package cloud.makeronbean.generate.entity.starter.factory;

/**
 * @author makeronbean
 * @createDate 2023-05-01  19:23
 * @description
 */

public interface StarterFactory {
    <T> T getInstance(Class<T> clazz);
}
