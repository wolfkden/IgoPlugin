package org.elasticsearch.index.analysis;

import net.reduls.igo.Tagger;
import net.reduls.igo.analysis.ipadic.IpadicFilter;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.ElasticSearchIllegalArgumentException;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.settings.IndexSettings;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: root
 * Date: 4/13/11
 * Time: 10:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class IgoTokenFilterFactory extends AbstractTokenFilterFactory {

    private TokenFilter tokenFilter;
    private Tagger tagger;

    public IgoTokenFilterFactory(Index index, @IndexSettings Settings indexSettings, Environment environment, @Assisted String name, @Assisted Settings settings) {
         super(index, indexSettings, name);

        try{
        tagger = new Tagger("./config/ipadic");
        } catch (IOException e) {
            throw new ElasticSearchIllegalArgumentException("Failed to load Ipadic dictionary", e);
        }
    }


    public TokenStream create(TokenStream tokenStream) {

        return new IpadicFilter(tokenStream, tagger);

    }
}
