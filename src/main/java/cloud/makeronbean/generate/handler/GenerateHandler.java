package cloud.makeronbean.generate.handler;

import cloud.makeronbean.generate.starter.base.starter.AbstractStarter;

/**
 * @author makeronbean
 * @createDate 2023-05-03  13:55
 * @description 生成处理器接口
 */
public interface GenerateHandler {
    
    /**
     * 生成完成之前执行
     */
    void beforeGenerate() throws Exception;
    
    /**
     * 代码生成处理方法
     *
     * @param starter starter项
     */
    void generateHandler(AbstractStarter starter) throws Exception;
    
    
    /**
     * 生成完成之后执行
     */
    void afterGenerate() throws Exception;
}
