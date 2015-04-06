import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;

import java.io.*;
import java.net.URL;

public class Main {

    public static String extractHtmlContent(String htmlstr) throws BoilerpipeProcessingException {
        return ArticleExtractor.INSTANCE.getText(htmlstr).replace('\n',' ');
    }

    public static void main(String[] args) throws Exception {
        String inputFileName = args[1];
        String outputFileName = args[2];

        BufferedReader br  = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName)));
        BufferedWriter bw  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName)));

        String htmlstr = new String();
        String line = new String();
        int count =1;
        boolean inpool = true;

        while((line = br.readLine())!= null){
            if(line.equals("<doc>")){
                inpool = true;
                htmlstr = "";
                continue;
            }
            if(line.equals("</doc>")){
                inpool = false;
                try{
                    bw.write(extractHtmlContent(htmlstr));
                    bw.flush();
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }
            if (inpool == true){
                htmlstr += line;
            }
        }



    }
}
