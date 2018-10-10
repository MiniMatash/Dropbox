package com.minimatash;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class JsoupFindByIdSnippet {


    private static String CHARSET_NAME = "utf8";

    public static void main(String[] args) {

        String originPath = args[0];
        String samplePath = args[1];

        String targetElementId = "make-everything-ok-button";

        Optional<Element> buttonOpt = findElementById(new File(originPath), targetElementId);
        if(!buttonOpt.isPresent()){
            System.exit(1);
        }

        Element result = search(samplePath, buttonOpt.get().parent(), buttonOpt.get(), null, 0);

        StringBuilder elementPath = new StringBuilder().append(result.tagName());

        if(result.attributes().hasKey("class")){
            elementPath.append("(class = ")
                    .append(result.attributes().get("class"))
                    .append(")");
        } else if(result.attributes().hasKey("id")){
            elementPath.append("(id = ")
                    .append(result.attributes().get("id"))
                    .append(")");
        }

        while (result.hasParent()){
            result = result.parent();
            elementPath.insert(0, ">");

            if(result.attributes().hasKey("class")){
                elementPath.insert(0,")")
                        .insert(0, result.attributes().get("class"))
                        .insert(0,"(class = ");
            } else if(result.attributes().hasKey("id")){
                elementPath.insert(0, ")")
                        .insert(0, result.attributes().get("id"))
                        .insert(0,"(id = ");
            }

            elementPath.insert(0, result.tagName());
        }

        System.out.println(elementPath.toString());
    }

    private static Element search(String filePath, Element parent, Element criterion, Element candidate, Integer candidateSimilarity){
        String cssQuery;
        for(Attribute critAttribute: criterion.attributes()) {
            cssQuery = queryBuilder(parent, criterion.tagName(), critAttribute.getKey(), criterion.attr(critAttribute.getKey()));
            if (cssQuery == null) {
                break;
            }

            Optional<Elements> candidates = findElementsByQuery(new File(filePath), cssQuery);
            for (Element elem: candidates.get()){
                Integer elemSimilarity = 0;

                if(elem.equals(candidate)){
                    continue;
                }

                for(Attribute attribute: elem.attributes()){
                    if(criterion.attributes().hasKey(attribute.getKey()) &&
                            criterion.attributes().get(attribute.getKey()).equals(attribute.getValue())){
                        elemSimilarity++;
                    }
                }
                if(elemSimilarity>=candidateSimilarity){

                    if(candidate==null){
                        candidate = elem;
                        candidateSimilarity = elemSimilarity;
                    }

                    if((elem.attributes().hasKey("class"))
                            && (elem.attributes().get("class").equals(criterion.attributes().get("class")))||
                            ((elem.attributes().hasKey("id"))
                                    && (elem.attributes().get("id").equals(criterion.attributes().get("id"))))){
                        candidate = elem;
                        candidateSimilarity = elemSimilarity;
                    }

                }
            }

        }
        if (parent.hasParent()) {
            candidate = search(filePath, parent.parent(), criterion, candidate, candidateSimilarity);
        }
        return candidate;
    }

    private static String queryBuilder(Element parent, String objectTag,String objectAttributeName, String objectAttribute){
        StringBuilder cssQuery = new StringBuilder();
        cssQuery.append(parent.tag())
                .append("[");

        if(parent.attributes().hasKey("class")){
            cssQuery.append("class=\"")
                    .append(parent.attributes().get("class"))
                    .append("\"] ");
        } else if(parent.attributes().hasKey("id ")){
            cssQuery.append("id=\"")
                    .append(parent.attributes().get("id"))
                    .append("\"] ");
        } else {
            return null;
        }

        cssQuery.append(objectTag)
                .append("[")
                .append(objectAttributeName)
                .append("=\"")
                .append(objectAttribute)
                .append("\"]");

        return cssQuery.toString();
    }

    private static Optional<Element> findElementById(File htmlFile, String targetElementId) {
        try {
            Document doc = Jsoup.parse(
                    htmlFile,
                    CHARSET_NAME,
                    htmlFile.getAbsolutePath());

            return Optional.ofNullable(doc.getElementById(targetElementId));

        } catch (IOException e) {
            System.out.println("Error reading  file" + e);
            return Optional.empty();
        }
    }

    private static Optional<Elements> findElementsByQuery(File htmlFile, String cssQuery) {
        try {
            Document doc = Jsoup.parse(
                    htmlFile,
                    CHARSET_NAME,
                    htmlFile.getAbsolutePath());

            return Optional.ofNullable(doc.select(cssQuery));

        } catch (IOException e) {
            return Optional.empty();
        }
    }

}