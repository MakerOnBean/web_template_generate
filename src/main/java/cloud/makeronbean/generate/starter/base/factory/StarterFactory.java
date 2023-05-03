package cloud.makeronbean.generate.starter.base.factory;

/**
 * @author makeronbean
 * @createDate 2023-05-01  19:23
 * @description
 */

public interface StarterFactory {
    <T> T getInstance(Class<T> clazz);
}
