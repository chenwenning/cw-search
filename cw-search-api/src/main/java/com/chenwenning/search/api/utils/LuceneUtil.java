package com.chenwenning.search.api.utils;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by chenwenning on 2016/7/19.
 */
public class LuceneUtil {

    public static void initOpenIndex() throws IOException {
        Directory dir = FSDirectory.open(Paths.get("D://lucene/index"));
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        //config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        IndexWriter writer = new IndexWriter(dir, config);
       // String docsPath = "D://lucene/index";

        //final Path docDir = Paths.get(docsPath);
       // indexDocs(writer, docDir);

        Document doc = new Document();
        doc.add(new StringField("path", "dsdsadasdadasd", Field.Store.YES));
        doc.add(new StringField("name", "sdsaddsad", Field.Store.YES));
        doc.add(new StringField("nikeName", "wumosadsasdsadsa534534adsaduli", Field.Store.YES));
        writer.addDocument(doc);
        writer.commit();
        writer.close();


    }



    public static void main(String[] args) {
        try {
            initOpenIndex();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
