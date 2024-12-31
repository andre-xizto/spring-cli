package dev.buskopan.dependencies.impl;

import dev.buskopan.dependencies.BaseIDependency;

public class SpringDataJPA extends BaseIDependency {

    private static final String VERSION = "3.4.1";

    @Override
    public String getMavenDependency() {
        return "<dependency>\n" +
                "    <groupId>org.springframework.boot</groupId>\n" +
                "    <artifactId>spring-boot-starter-data-jpa</artifactId>\n" +
                "    <version>"+VERSION+"</version>\n" +
                "</dependency>\n";
    }

    @Override
    public String getGradleDependency() {
        return "implementation group: 'org.springframework.boot', name: 'spring-boot-starter-data-jpa', version: '"+VERSION+"'\n";
    }

    @Override
    public String getDisplayName() {
        return "Spring Data JPA";
    }
}
