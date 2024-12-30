package dev.buskopan.dependencies;

import dev.buskopan.build_tools.BuildToolsType;

public interface IDependency {
    String writeDependency(BuildToolsType buildTool);
}
