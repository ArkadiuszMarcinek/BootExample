package org.example.collection.advance;

import org.example.BootExampleUtils;
import org.example.dto.Mall;
import org.example.dto.Product;
import org.example.dto.Store;
import org.example.exception.BootExampleException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollectionsAdvanceExample implements CollectionsAdvanceExampleInterface {

    @Override
    public Product getTheMostAvailableProductFromStore(Store store) {
        Map<Product,Long> values = store.getProducts().stream()
        .collect(Collectors.groupingBy(a -> a, Collectors.counting()));
        if(values.isEmpty())
            throw new BootExampleException(BootExampleUtils.NO_PRODUCT_FOUND);
        return values.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(new BootExampleException(BootExampleUtils.NO_PRODUCT_FOUND))
                .map(Map.Entry::getKey).get();


    }

    @Override
    public Product getTheMostAvailableProductFromMall(Mall mall) {
        return null;
    }
}
