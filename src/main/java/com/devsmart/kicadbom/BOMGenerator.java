package com.devsmart.kicadbom;


import com.devsmart.kicadbom.generic.PanasonicSMDResistor;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.vseravno.solna.SolnaParser;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BOMGenerator {

    private static final ImmutableList<GenericPartFactory> GENERIC_PART_FACTORIES;

    static {
        GENERIC_PART_FACTORIES = new ImmutableList.Builder<GenericPartFactory>()
                .add(new PanasonicSMDResistor())
                .build();
    }

    private static String findMatchingStandardPart(Component c) {
        String retval = null;

        for(GenericPartFactory partFactory : GENERIC_PART_FACTORIES) {
            if((retval = partFactory.getMPN(c)) != null) {
                return retval;
            }
        }
        return null;
    }


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
            File outputCSVFile = new File(line.getOptionValue("o", "BOM.csv"));

            ComponentsHandler componentsHandler = new ComponentsHandler();
            SolnaParser xmlParser = new SolnaParser();
            xmlParser.addHandler("/export/components/comp", componentsHandler);

            FileInputStream fin = new FileInputStream(inputXMLFile);
            xmlParser.parse(fin);
            fin.close();

            ArrayListMultimap<String, Component> componentsByMPN = ArrayListMultimap.create();

            for(Component c : componentsHandler.components) {
                String mpn = c.getMPN();
                if(mpn == null) {
                    mpn = findMatchingStandardPart(c);
                }

                if(mpn == null) {
                    mpn = String.format("*UKN* %s %s", c.ref, c.value);
                }

                componentsByMPN.put(mpn, c);
            }

            BOMCSVWriter writer = new BOMCSVWriter(componentsByMPN, new FileOutputStream(outputCSVFile));
            writer.write();



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
