package cloud.makeronbean.generate.starter.parent;

import cloud.makeronbean.generate.starter.base.code.BaseCode;
import cloud.makeronbean.generate.starter.base.code.CodeItem;

/**
 * @author makeronbean
 * @createDate 2023-05-03  12:19
 * @description
 */
public class SpringBootParentCode extends BaseCode {
    
    @Override
    protected void getDefault() {
        addMainBoot();
    }
    
    
    /**
     * 主启动类
     */
    private void addMainBoot() {
        String bootName = SpringBootParentStarter.config.getMainBootName();
        CodeItem boot = new CodeItem();
        boot.setPath("");
        boot.setFileName(bootName + ".java");
        boot.setCodeTemplate(String.format(
                "import org.springframework.boot.SpringApplication;\n" +
                        "import org.springframework.boot.autoconfigure.SpringBootApplication;\n" +
                        "\n" +
                        "@SpringBootApplication\n" +
                        "public class %s {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        SpringApplication.run( %s.class, args);\n" +
                        "    }\n" +
                        "}", bootName, bootName
        ));
        this.addCodeItem(boot);
    }
}
