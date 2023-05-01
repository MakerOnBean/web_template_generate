package cloud.makeronbean.generate.entity.dto;

import cloud.makeronbean.generate.resolver.PomResolver;

/**
 * @author makeronbean
 * @createDate 2023-05-01  20:16
 * @description
 */

public class SpringBootParentDto {
    public static String mainBootName = PomResolver.getInstance().getArtifactId().substring(0, 1).toUpperCase() + PomResolver.getInstance().getArtifactId().substring(1);
}
