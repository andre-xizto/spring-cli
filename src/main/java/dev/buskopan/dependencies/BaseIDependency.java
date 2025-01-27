package dev.buskopan.dependencies;

import dev.buskopan.build_tools.BuildToolsType;
import dev.buskopan.exceptions.InvalidBuildToolException;

public abstract class BaseIDependency implements IDependency {

    public abstract String getMavenDependency();
    public abstract String getGradleDependency();
    public abstract String getDisplayName();

    @Override
    public String getDisplay() {
        return getDisplayName();
    }

    @Override
    public String writeDependency(BuildToolsType buildTool) {
        if (buildTool == BuildToolsType.MAVEN) {
            return getMavenDependency();
        }
        if (buildTool == BuildToolsType.GRADLE) {
            return getGradleDependency();
        }
        throw new InvalidBuildToolException(buildTool.getDisplay() + " is not a valid build tool");
    }
}
