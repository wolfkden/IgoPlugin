/*
 * Licensed to Elastic Search and Shay Banon under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Elastic Search licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.index.analysis;

/**
 * Created by IntelliJ IDEA.
 * User: root
 * Date: 3/14/11
 * Time: 4:17 PM
 * To change this template use File | Settings | File Templates.
 */

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.inject.multibindings.MapBinder;
import org.elasticsearch.common.lucene.Lucene;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.settings.IndexSettings;
import org.elasticsearch.indices.analysis.IndicesAnalysisService;
import org.elasticsearch.index.analysis.AnalysisModule;

public class IgoAnalysisBinderProcessor extends AnalysisModule.AnalysisBinderProcessor {

    @Override public void processAnalyzers(AnalyzersBindings analyzersBindings)
    {
        analyzersBindings.processAnalyzer("igoAnalyzer", IgoAnalyzerProvider.class);
        analyzersBindings.processAnalyzer("igo_analyzer", IgoAnalyzerProvider.class);
        analyzersBindings.processAnalyzer("igo-analyzer", IgoAnalyzerProvider.class);
    }

    @Override public void processTokenFilters(TokenFiltersBindings tokenFiltersBindings) {
        tokenFiltersBindings.processTokenFilter("igoFilter", IgoTokenFilterFactory.class);
        tokenFiltersBindings.processTokenFilter("igo_filter", IgoTokenFilterFactory.class);
        tokenFiltersBindings.processTokenFilter("igo-filter", IgoTokenFilterFactory.class);
        tokenFiltersBindings.processTokenFilter("igoStopFilter", IgoTokenFilterStopFactory.class);
        tokenFiltersBindings.processTokenFilter("igo_stop_filter", IgoTokenFilterStopFactory.class);
        tokenFiltersBindings.processTokenFilter("igo-stop-filter", IgoTokenFilterStopFactory.class);
    }

    @Override public void processTokenizers(TokenizersBindings tokenizersBindings) {
        tokenizersBindings.processTokenizer("igoTokenizer", IgoTokenizerFactory.class);
        tokenizersBindings.processTokenizer("igo_tokenizer", IgoTokenizerFactory.class);
        tokenizersBindings.processTokenizer("igo-tokenizer", IgoTokenizerFactory.class);
    }
}
