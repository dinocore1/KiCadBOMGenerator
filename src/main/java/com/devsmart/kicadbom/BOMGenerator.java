package com.devsmart.kicadbom;


import com.vseravno.solna.SolnaParser;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BOMGenerator {


    public static void main(String[] args) {

        Options options = new Options();

        options.addOption(Option.builder("i")
                .hasArg()
                .argName("inputfile")
                .required()
                .build());

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(options, args);

            File inputXMLFile = new File(line.getOptionValue("i"));

            ComponentsHandler componentsHandler = new ComponentsHandler();
            SolnaParser xmlParser = new SolnaParser();
            xmlParser.addHandler("/export/components/comp", componentsHandler);

            FileInputStream fin = new FileInputStream(inputXMLFile);
            xmlParser.parse(fin);
            fin.close();




        } catch (ParseException e) {
            System.err.println(e.getMessage());

            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "BOMGenerator", options );

            System.exit(-1);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }


    }
}
