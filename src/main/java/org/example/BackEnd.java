package org.example;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class BackEnd {
    private File kassa;
    private File apell;
    private File template;
    private File konfig;
    private File output;
    private XWPFDocument kassaDoc;
    private XWPFDocument apellDoc;
    private XWPFDocument templateDoc;
    private String [] kassParagraphs;
    private String [] apellParagraphs;
    public BackEnd(File kassa, File apell, File template, File konfig, File output) throws IOException {
       this.kassa=kassa;
       this.apell=apell;
       this.template=template;
       this.konfig=konfig;
       this.output=output;
       kassaDoc=new XWPFDocument(new FileInputStream(kassa));
       apellDoc=new XWPFDocument(new FileInputStream(apell));
       templateDoc=new XWPFDocument(new FileInputStream(template));
       kassParagraphs=new String[kassaDoc.getParagraphs().size()];
       apellParagraphs=new String[apellDoc.getParagraphs().size()];
        kassParagraphs= kassaDoc.getParagraphs().stream().map(new Function<XWPFParagraph, String>() {
            @Override
            public String apply(XWPFParagraph xwpfParagraph) {
                return xwpfParagraph.getText();
            }
        }).toList().toArray(String[]::new);
        apellParagraphs= (String[]) apellDoc.getParagraphs().stream().map(new Function<XWPFParagraph, String>() {
            @Override
            public String apply(XWPFParagraph xwpfParagraph) {
                return xwpfParagraph.getText();
            }
        }).toList().toArray(new String[]{});
    }
    public void start() throws IOException {
        KonfigInitializer konfigInitializer=new KonfigInitializer(konfig);
       List<Command> listOfCommandsKass= konfigInitializer.evaluateKfgToRightFormKass();
        List<Command> listOfCommandApel=konfigInitializer.evaluateKfgToRightFormApell();
       for(int i=0;i<listOfCommandsKass.size();i++){
           listOfCommandsKass.get(i).evalCommand(kassParagraphs,templateDoc);
       }
       for(int i=0;i<listOfCommandApel.size();i++){
           listOfCommandApel.get(i).evalCommand(apellParagraphs,templateDoc);
       }
    }
}
