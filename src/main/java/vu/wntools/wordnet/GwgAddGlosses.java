package vu.wntools.wordnet;

import vu.wntools.lmf.Gloss;
import vu.wntools.lmf.Synset;
import vu.wntools.util.ReadGlosses;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by piek on 05/06/15.
 */
public class GwgAddGlosses {

    ///Users/piek/Desktop/GWG/nl/odwn1.0.lmf.test
    ///Users/piek/Desktop/GWG/nl/odwn-translated-glosses.final

    static public void main (String[] args) {
        try {
/*
            String glossLanguage = "en";
            String glossOwner = "google-translate";
            String pathToLmfFile = "/Users/piek/Desktop/GWG/nl/odwn1.0.lmf.test";
            String pathToGlossFile = "/Users/piek/Desktop/GWG/nl/odwn-translated-glosses.final";
*/
            String glossLanguage = "en";
            String glossOwner = "pwn";
            String pathToLmfFile = "/Users/piek/Desktop/GWG/nl/odwn1.0.lmf.test.test";
            String pathToGlossFile = "/Users/piek/Desktop/GWG/nl/wn-eng-glosses";
            ReadGlosses readGlosses = new ReadGlosses();
            readGlosses.readGlossFile(pathToGlossFile, glossLanguage, glossOwner);
            System.out.println("readGlosses.synsetToGlosses.size() = " + readGlosses.synsetToGlosses.size());
            WordnetLmfDataSaxParser wordnetLmfDataSaxParser = new WordnetLmfDataSaxParser();
            wordnetLmfDataSaxParser.parseFile(pathToLmfFile);
            for (int i = 0; i < wordnetLmfDataSaxParser.wordnetData.getSynsets().size(); i++) {
                Synset synset = wordnetLmfDataSaxParser.wordnetData.getSynsets().get(i);
                if (readGlosses.synsetToGlosses.containsKey(synset.getSynsetId())) {
                    ArrayList<Gloss> defs = readGlosses.synsetToGlosses.get(synset.getSynsetId());
                    for (int j = 0; j < defs.size(); j++) {
                        Gloss def = defs.get(j);
                        synset.addDefinition(def);
                    }
                }
            }

            OutputStream fos = new FileOutputStream(pathToLmfFile+".test");
            wordnetLmfDataSaxParser.wordnetData.serialize(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*  AD HOC TO FIX ODWN INPUT
    wordnetLmfDataSaxParser.wordnetData.setGlobalLabel("ODWN.1.0");
    wordnetLmfDataSaxParser.wordnetData.setLexiconLabel("ODWN.1.0");

    for (int j = 0; j < synset.getDefinitions().size(); j++) {
        Gloss gloss = synset.getDefinitions().get(j);
        gloss.setProvenance("odwn");
    }
*/

}