package com.test.lang3.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * JAR包工具
 * <p>
 *
 * @author <a href="mailto:yangsheng1993812@gmail.com">Oatelauser</a>
 * @date 2022-05-12
 * @since 1.0
 */
public class JarPaths {

    /**
     * 获取JAR包运行的路径
     *
     * @return 路径
     */
    public static File getPath() {
        return JarFile.JAR_FILE;
    }

    private static File findPath() {
        // 1. com/test/config/AgentPackagePath.class
        String classPath = JarPaths.class.getName().replaceAll("\\.", "/") + ".class";

        // 2. jar:file:to/path/XXX.jar!com/test/config/AgentPackagePath.class
        URL resource = ClassLoader.getSystemResource(classPath);
        if (resource != null) {
            String urlString = resource.toString();
            int insidePathIndex = urlString.indexOf("!");
            if (insidePathIndex > -1) {
                // 3. file:to/path/XXX.jar
                urlString = urlString.substring(urlString.indexOf("file:"), insidePathIndex);
                File jarFile;
                try {
                    jarFile = new File(new URL(urlString).toURI());
                    if (jarFile.exists()) {
                        // 4. file:to/path
                        return jarFile.getParentFile();
                    }
                } catch (URISyntaxException | MalformedURLException e) {
                    throw new IllegalStateException(e);
                }
            } else {
                // file:to/path/com/test/config/AgentPackagePath.class
                // file:to/path
                String classLocation = urlString.substring("file:".length(), urlString.length() - classPath.length());
                return new File(classLocation);
            }
        }

        throw new InternalError("Can not find jar file.");
    }

    private static class JarFile {
        private static final File JAR_FILE = findPath();
    }

}
