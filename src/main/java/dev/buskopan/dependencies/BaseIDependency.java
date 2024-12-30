package dev.buskopan.dependencies;

import dev.buskopan.build_tools.BuildToolsType;
import dev.buskopan.exceptions.DependencyNotFoundException;

public abstract class BaseIDependency implements IDependency {

    public abstract String getMavenDependency();
    public abstract String getGradleDependency();

    @Override
    public String writeDependency(BuildToolsType buildTool) {
        if (buildTool == BuildToolsType.MAVEN) {
            return getMavenDependency();
        }
        if (buildTool == BuildToolsType.GRADLE) {
            return getGradleDependency();
        }
        throw new DependencyNotFoundException(buildTool.getDisplay() + " is not a valid build tool");
    }
}
