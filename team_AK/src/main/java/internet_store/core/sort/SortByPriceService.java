package internet_store.core.sort;

import internet_store.core.domain.Product;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SortByPriceService {

    public List<Product> sort(List<Product> entryList) {
        return entryList.stream().sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());
    }
}