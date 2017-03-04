package com.devsmart.kicadbom;


import org.apache.commons.cli.*;

import java.io.File;

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



        } catch (ParseException e) {
            System.err.println(e.getMessage());

            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "BOMGenerator", options );

            System.exit(-1);
        }


    }
}
