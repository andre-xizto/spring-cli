package dev.buskopan.dependencies;

import dev.buskopan.build_tools.BuildToolsType;

public interface IDependency {
    String writeDependency(String dependency, BuildToolsType buildTool);
}
