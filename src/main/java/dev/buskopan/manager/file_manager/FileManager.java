package dev.buskopan.manager.file_manager;

import dev.buskopan.build_tools.BuildToolsType;
import dev.buskopan.dependencies.IDependency;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class FileManager {

    private final String artifactId;
    private final String groupId;
    private final String projectName;
    private final String description;
    private final String packageName;
    private final String javaVersion;
    private final List<IDependency> dependencies;
    private final BuildToolsType buildTool;
    private String projectPath;

    private FileManager(FileManagerBuilder builder) {
        this.artifactId = builder.artifactId;
        this.groupId = builder.groupId;
        this.projectName = builder.projectName;
        this.dependencies = builder.dependencies;
        this.buildTool = builder.buildTool;
        this.description = builder.description;
        this.packageName = builder.packageName;
        this.javaVersion = builder.javaVersion;
    }

    public void createProjectStructure() {
        String currentPath = Paths.get("").toAbsolutePath().toString();
        String baseDir = currentPath + "/" + projectName.replace(" ", "-");
        setProjectPath(baseDir);
        String packName = packageName.replace(" ", ".");

        new File(baseDir + "/src/main/java/" + packName.replace(".", "/")).mkdirs();
        new File(baseDir + "/src/main/resources/").mkdirs();
        new File(baseDir + "/src/test/java/" + packName.replace(".", "/")).mkdirs();

        try {
            createApplicationClass(baseDir + "/src/main/java/" + packName.replace(".", "/"), packName);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar classe main " + e.getMessage());
        }

        try {
            createApplicationTestClass(baseDir + "/src/test/java/" + packName.replace(".", "/"), packName);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar classe de teste da main " + e.getMessage());
        }

        try {
            createAppProperties(baseDir + "/src/main/resources/", projectName.replace(" ", "/"));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao gerar application properties " + e.getMessage());
        }

        try {
            createGitIgnore(baseDir);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao gerar git ignore " + e.getMessage());
        }

        if (buildTool == BuildToolsType.MAVEN) {
            try {
                createPomXML(javaVersion, baseDir, artifactId, groupId, description, projectName, dependencies);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao gerar o POM.xml " + e.getMessage());
            }
        }

        //SUPPORT TO GRADLE SOON...

    }

    public String getProjectPath() {
        if (projectPath == null) {
            System.out.println("Project is not ready yet");
            return null;
        }
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    private void createApplicationClass(String baseDir, String packageName) throws IOException {
        FileWriter writer = new FileWriter(baseDir + "/Main.java");

        writer.write("package "+packageName.replace("/",".")+";\n" +
                "\n" +
                "import org.springframework.boot.SpringApplication;\n" +
                "import org.springframework.boot.autoconfigure.SpringBootApplication;\n" +
                "\n" +
                "@SpringBootApplication\n" +
                "public class Main {\n" +
                "\n" +
                "  public static void main(String[] args) {\n" +
                "    SpringApplication.run(Main.class, args);\n" +
                "  }\n" +
                "\n" +
                "}");

        writer.close();
    }

    private void createApplicationTestClass(String baseDir, String packageName) throws IOException {
        FileWriter writer = new FileWriter(baseDir + "/MainTests.java");

        writer.write("package "+packageName.replace("/",".")+";\n" +
                "\n" +
                "import org.junit.jupiter.api.Test;\n" +
                "import org.springframework.boot.test.context.SpringBootTest;\n" +
                "\n" +
                "import static org.junit.jupiter.api.Assertions.*;\n" +
                "\n" +
                "@SpringBootTest\n" +
                "class MainTests {\n" +
                "\n" +
                "  @Test\n" +
                "  void contextLoads() {\n" +
                "     //implement tests\n" +
                "     fail();\n" +
                "  }\n" +
                "\n" +
                "}");

        writer.close();
    }

    private void createAppProperties(String baseDir, String applicationName) throws IOException {
        FileWriter writer = new FileWriter(baseDir + "/application.properties");

        writer.write("#GENERATED BY SPRING-CLI\n");
        writer.write("spring.application.name="+applicationName);

        writer.close();
    }

    private void createGitIgnore(String baseDir) throws IOException {
        FileWriter writer = new FileWriter(baseDir + "/.gitignore");
        writer.write("""
                HELP.md
                target/
                !.mvn/wrapper/maven-wrapper.jar
                !**/src/main/**/target/
                !**/src/test/**/target/

                ### STS ###
                .apt_generated
                .classpath
                .factorypath
                .project
                .settings
                .springBeans
                .sts4-cache

                ### IntelliJ IDEA ###
                .idea
                *.iws
                *.iml
                *.ipr

                ### NetBeans ###
                /nbproject/private/
                /nbbuild/
                /dist/
                /nbdist/
                /.nb-gradle/
                build/
                !**/src/main/**/build/
                !**/src/test/**/build/

                ### VS Code ###
                .vscode/""");
        writer.close();
    }

    private void createPomXML(String javaVersion, String baseDir, String artifactId, String groupId, String description, String projectName, List<IDependency> dependencies) throws IOException {
        FileWriter writer = new FileWriter(baseDir + "/pom.xml");
        writer.write("<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0\n" +
                "         http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "<parent>\n" +
                "    <groupId>org.springframework.boot</groupId>\n" +
                "    <artifactId>spring-boot-starter-parent</artifactId>\n" +
                "    <version>3.4.1</version>\n" +
                "    <relativePath/> <!-- lookup parent from repository -->\n" +
                "</parent>\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "    <groupId>"+groupId+"</groupId>\n" +
                "    <artifactId>" + artifactId + "</artifactId>\n" +
                "    <version>0.0.1-SNAPSHOT</version>\n" +
                "    <name>"+projectName+"</name>\n" +
                "    <description>"+description+"</description>\n" +
                "    <properties>\n" +
                "       <java.version>" + javaVersion + "</java.version>\n" +
                "   </properties>\n" +
                "    <dependencies>\n" +
                "       <dependency>\n" +
                "           <groupId>org.springframework.boot</groupId>\n" +
                "           <artifactId>spring-boot-starter</artifactId>\n" +
                "       </dependency>\n" +
                "           ");

        if (!dependencies.isEmpty()) {
            for (IDependency dependency : dependencies) {
                String result = dependency.writeDependency(BuildToolsType.MAVEN);
                writer.write(result);
            }
        }

        writer.write("""
                <dependency>
                      <groupId>org.springframework.boot</groupId>
                      <artifactId>spring-boot-starter-test</artifactId>
                      <scope>test</scope>
                    </dependency>
                </dependencies>
                </project>""");
        writer.close();
    }

    public static class FileManagerBuilder {

        private String artifactId;
        private String groupId;
        private String projectName;
        private List<IDependency> dependencies;
        private String description;
        private String javaVersion;
        private String packageName;
        private BuildToolsType buildTool;

        public FileManagerBuilder artifactId(String artifactId) {
            this.artifactId = artifactId;
            this.projectName = artifactId;
            this.packageName = groupId + "." + artifactId;
            return this;
        }

        public FileManagerBuilder buildTool(String buildTool) {
            if (buildTool.equalsIgnoreCase("maven")) {
                this.buildTool = BuildToolsType.MAVEN;
                return this;
            }
            if (buildTool.equalsIgnoreCase("gradle")) {
                System.out.println("Gradle is not supported at moment");
                System.out.println("Maven selected by default");
                this.buildTool = BuildToolsType.MAVEN;
                return this;
            }
            System.out.println("Invalid build tool. Maven selected by default");
            this.buildTool = BuildToolsType.MAVEN;
            return this;
        }

        public FileManagerBuilder groupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public FileManagerBuilder packageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        public FileManagerBuilder description(String description) {
            this.description = description;
            return this;
        }

        public FileManagerBuilder javaVersion(String javaVersion) {
            this.javaVersion = javaVersion;
            return this;
        }

        public FileManagerBuilder projectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        public FileManagerBuilder dependencies(List<IDependency> dependencies) {
            this.dependencies = dependencies;
            return this;
        }

        public FileManager build() {
            return new FileManager(this);
        }
    }


}
