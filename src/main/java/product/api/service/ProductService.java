package product.api.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import product.api.dto.ProductRequest;
import product.api.exception.ValidationException;
import product.api.model.Product;
import product.api.repository.ProductRepository;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    private static final ValidationException PRODUCT_NOT_FOUND = new ValidationException("Product not found.");

    @Autowired
    private ProductRepository repository;

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product getById(Long id) {
        return repository.getReferenceById(id);
    }

    @Transactional
    public Product create(ProductRequest request) {
        var product = Product.of(request);
        return repository.save(product);
    }

    @Transactional
    public Product update(ProductRequest request, Long id) {
        var product = repository.getReferenceById(id);
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setUpdatedAt(LocalDateTime.now());
        return repository.save(product);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void exportCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=products_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<Product> listProducts = getAll();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"PRODUCT ID", "NAME", "PRICE", "CREATED_AT"};
        String[] nameMapping = {"id", "name", "price", "createdAt"};

        csvWriter.writeHeader(csvHeader);

        for (Product product : listProducts) {
            csvWriter.write(product, nameMapping);
        }

        csvWriter.close();
    }
}
