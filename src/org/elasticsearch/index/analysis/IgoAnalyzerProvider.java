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

import net.reduls.igo.Tagger;
import net.reduls.igo.analysis.ipadic.IpadicAnalyzer;

import net.reduls.igo.analysis.ipadic.IpadicCompositeAnalyzer;
import org.elasticsearch.ElasticSearchIllegalArgumentException;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.lucene.Lucene;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.settings.IndexSettings;

import java.util.Set;
import java.io.IOException;
import java.util.jar.JarFile;

public class IgoAnalyzerProvider extends AbstractIndexAnalyzerProvider<IpadicCompositeAnalyzer> {
    private final IpadicCompositeAnalyzer analyzer;

    @Inject public IgoAnalyzerProvider(Index index, @IndexSettings Settings indexSettings, @Assisted String name, @Assisted Settings settings)
    {
        super(index, indexSettings, name);
//        Set<?> stopWords = Analysis.parseStopWords(settings, IpadicAnalyzer.getDefaultStopSet());

        try {
        analyzer = new IpadicCompositeAnalyzer(new Tagger("./config/ipadic"));
        } catch (IOException e) {
            throw new ElasticSearchIllegalArgumentException("Failed to load Ipadic dictionary", e);
        }
    }

    public IpadicCompositeAnalyzer get() {
        return this.analyzer;
    }
}
