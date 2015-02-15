package org.elasticsearch.index.analysis;

import net.reduls.igo.Tagger;
import net.reduls.igo.analysis.ipadic.IpadicAnalyzer;
import net.reduls.igo.analysis.ipadic.IpadicTokenizer;
import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.ElasticSearchIllegalArgumentException;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.settings.IndexSettings;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by IntelliJ IDEA.
 * User: root
 * Date: 4/13/11
 * Time: 10:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class IgoTokenizerFactory extends AbstractTokenizerFactory {

    Tokenizer tokenizer;
    Tagger tagger;
    Reader reader;

    @Inject
    public IgoTokenizerFactory(Index index, @IndexSettings Settings indexSettings, @Assisted String name, @Assisted Settings settings)
    throws IOException
    {
        super(index, indexSettings, name);
//        Set<?> stopWords = Analysis.parseStopWords(settings, IpadicAnalyzer.getDefaultStopSet());

        try{
        tagger = new Tagger("./config/ipadic");
        } catch (IOException e) {
            throw new ElasticSearchIllegalArgumentException("Failed to load Ipadic dictionary", e);
        }
    }

    public Tokenizer create(Reader reader) {

        return new IpadicTokenizer(tagger, reader);

    }
}
