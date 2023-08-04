package tech.beanmak1r.generate.handler;


/**
 * 条件执行 handler
 *
 * @author beanMak1r
 * @since 2023-08-04 09:36
 */
public interface SupportHandler extends GenerateHandler {

    /**
     * 返回值为 true 时执行生成
     *
     * @return true ｜ false
     */
    boolean isSupport();
}