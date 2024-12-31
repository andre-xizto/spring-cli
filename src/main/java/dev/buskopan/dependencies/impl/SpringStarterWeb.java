package dev.buskopan.dependencies.impl;

import dev.buskopan.dependencies.BaseIDependency;

public class SpringStarterWeb extends BaseIDependency {

    private static final String VERSION = "3.4.1";

    @Override
    public String getMavenDependency() {
        return "<dependency>\n" +
                "    <groupId>org.springframework</groupId>\n" +
                "    <artifactId>spring-boot-starter-web</artifactId>\n" +
                "    <version>"+VERSION+"</version>\n" +
                "</dependency>\n";
    }

    @Override
    public String getGradleDependency() {
        return "implementation group: 'org.springframework', name: 'spring-web', version: '"+VERSION+"'\n";
    }
}
