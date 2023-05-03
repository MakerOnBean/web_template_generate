package cloud.makeronbean.generate.utils;

/**
 * @author makeronbean
 * @createDate 2023-05-03  10:41
 * @description
 */
public class StringUtils {
    
    private static final String packageStr = "package %s%s;\n\n";
    
    public static String addPackage(String codePath, String codeTemplate) {
        String resultStr = null;
        if (isEmpty(codePath)) {
            resultStr = String.format(packageStr, ProjectInfoUtils.basePackage, "");
        } else {
            resultStr = String.format(packageStr, ProjectInfoUtils.basePackage, "."+codePath);
        }
        return resultStr + codeTemplate;
    }
    
    
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }
}
