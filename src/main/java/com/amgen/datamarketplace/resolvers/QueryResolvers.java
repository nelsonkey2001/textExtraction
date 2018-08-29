package com.amgen.datamarketplace.resolvers;

import com.amgen.datamarketplace.TextExtraction;
import com.amgen.datamarketplace.types.DocumentAttributes;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class QueryResolvers implements GraphQLQueryResolver {



    public DocumentAttributes textExtraction(String input, String output){

        DocumentAttributes DA = new DocumentAttributes();
        List<String> someText = null;
        TextExtraction tx = new TextExtraction();
        try {
            someText = tx.parseUsingAutoDetect(input, output);
        } catch (Exception e) {
            e.printStackTrace();
        }

        DA.setContent(someText);
        return DA;
    }
}
