package dev.buskopan.dependencies.impl;

import dev.buskopan.dependencies.BaseIDependency;

public class SpringWeb extends BaseIDependency {

    private static final String VERSION = "6.2.1";

    @Override
    public String getMavenDependency() {
        return "<dependency>\n" +
                "    <groupId>org.springframework</groupId>\n" +
                "    <artifactId>spring-web</artifactId>\n" +
                "    <version>"+VERSION+"</version>\n" +
                "</dependency>\n";
    }

    @Override
    public String getGradleDependency() {
        return "implementation group: 'org.springframework', name: 'spring-web', version: '"+VERSION+"'\n";
    }
}
