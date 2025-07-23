package com.example.dao;

//  TODO-03: Use a stereotype annotation to mark this DAO as a Spring bean.
//  Alter this class to implement ProductDao.


import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    //  TODO-04: Autowire a VectorStore instance.

    @Autowired
    VectorStore vectorStore;

    //  TODO-05: Define the public void add() method.
    //  Define a single parameter of type List<String> containing product descriptions.
    //  Convert the List<String> into a List<Document>, where each product description String is used to create a Document.
    //  Call the vectorStore's add() method with the List<Document> to add the product descriptions to the VectorStore.
    public void add(List<String> products) {
        List<Document> documents = products.stream()
                .map(Document::new)
                .toList();
        vectorStore.add(documents);
    }

    //  TODO-06: Define the public List<String> findClosestMatches() method.
    //  Define two parameters: a String query and an int numberOfMatches.
    //  Use the SearchRequest.builder() method to create a new SearchRequest.
    //    Use the builder's query() method to set the input query String.
    //    Use the builder's topK() method to set the input numberOfMatches.
    //  Call the vectorStore's similaritySearch() method with the SearchRequest to find the closest matches.
    //  Capture the result in a List<Document>.
    //  Convert the List<Document> into a List<String> and return.
    public List<String> findClosestMatches(String query,int numberOfMatches) {

        SearchRequest request = SearchRequest.builder()
                .query(query)
                .topK(numberOfMatches)
                .build();
        List<Document> results = vectorStore.similaritySearch(request);
        return results.stream()
                .map((Document doc) -> doc.getText())
                .toList();
    }

    //  TODO-07: Define the public String findClosestMatch() method.
    //  Define a single parameter of type String containing a query.
    //  Call the findClosestMatches() method with the query and 1 as the number of matches.
    public String findClosestMatch(String query) {
        return findClosestMatches(query, 1).get(0);
    }

}
