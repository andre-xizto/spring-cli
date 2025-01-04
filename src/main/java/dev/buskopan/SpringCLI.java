package dev.buskopan;

import dev.buskopan.checker.JavaVersionChecker;
import dev.buskopan.dependencies.IDependency;
import dev.buskopan.manager.dependency_manager.DependencyManager;
import dev.buskopan.manager.file_manager.FileManager;
import picocli.CommandLine;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "springcli",
        description = "Generate Spring Boot application in Java"
)
public class SpringCLI implements Callable<Integer> {

    Scanner sc = new Scanner(System.in);

    @Override
    public Integer call() throws Exception {

        FileManager.FileManagerBuilder builder = new FileManager.FileManagerBuilder();

        print("SPRING-CLI");
        skipLine();

        question("What's your group id? ");
        String groupId = sc.next();
        builder.groupId(groupId.replace(" ","."));

        question("What's your artifact id? ");
        String artifact = sc.next();
        builder.artifactId(artifact);
        sc.nextLine();

        question("What's your project's name? (default: "+artifact.replace(" ", "-")+")");
        String name = sc.nextLine();
        if (!name.isEmpty()) {
            builder.projectName(name);
        }

        question("What's your project's description? ");
        String description = sc.nextLine();
        builder.description(description);

        question("What's your package name? (default: "+groupId.replace("/",".")+"."+artifact.replace(" ",".")+")");
        String packageName = sc.nextLine();
        if (!packageName.isEmpty()) {
            builder.packageName(packageName);
        }

        question("What's your build tool? [MAVEN|GRADLE] ");
        builder.buildTool(sc.next());

        List<String> versions = JavaVersionChecker.getJavaVersions();
        question("What java version you prefer?\n");
        versions.forEach(v -> {
            print(" - " + v);
        });
        skipLine();
        String versionSelected = sc.next();
        if (!versions.contains(versionSelected)) {
            print("You don't have this java version");
            versionSelected = versions.getFirst();
            print("Last available version selected: " + versionSelected);
        }
        builder.javaVersion(versionSelected);

        List<IDependency> dependencies = new DependencyManager().selectDependencies();
        builder.dependencies(dependencies);
        print("Generating project...");

        FileManager fm = builder.build();

        fm.createProjectStructure();

        print("Project ready at " + fm.getProjectPath());

        return 0;
    }

    private void print(String s) {
        System.out.println("> " + s);
    }

    private void question(String question) {
        System.out.print(question);
    }

    private void skipLine() {
        System.out.println();
    }

    public static void main(String[] args) {
        CommandLine cmd = new CommandLine(new SpringCLI());
        int code = cmd.execute(args);
        System.exit(code);
    }

}
