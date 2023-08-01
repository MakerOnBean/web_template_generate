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
        return str == null || str.isEmpty();
    }

    public static String bootNameFormat(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] charArray = name.toCharArray();
        boolean flag = true;
        for (char c : charArray) {
            if (flag) {
                if (c >= 97 && c <= 122) {
                    stringBuilder.append((char) (c - 32));
                } else {
                    stringBuilder.append(c);
                }
                flag = false;
            } else if (c == '_' || c == '-') {
                flag = true;
            } else {
                stringBuilder.append(c);
            }
        }
        stringBuilder.append("Application");
        return stringBuilder.toString();
    }

}
