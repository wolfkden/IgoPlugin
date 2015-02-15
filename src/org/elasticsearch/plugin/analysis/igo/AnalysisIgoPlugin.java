package org.elasticsearch.plugin.analysis.igo;

import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.index.analysis.IgoAnalysisBinderProcessor;
import org.elasticsearch.plugins.AbstractPlugin;
import org.elasticsearch.common.inject.Module;


public class AnalysisIgoPlugin extends AbstractPlugin {

    public String name() {
        return "igo-analysis";
    }

    public String description() {
        return "Japanese language related Igo analysis support";
    }

    @Override public void processModule(Module module) {
        if (module instanceof AnalysisModule) {
            AnalysisModule analysisModule = (AnalysisModule) module;
            analysisModule.addProcessor(new IgoAnalysisBinderProcessor());
        }
    }
}
