package product.api.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import product.api.dto.ProductRequest;
import product.api.dto.ProductResponse;
import product.api.service.ProductService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<ProductResponse> getAll() {
        return service.getAll().stream().map(ProductResponse::of).collect(Collectors.toList());
    }

    @GetMapping(value = "{id}")
    public ProductResponse getAll(@PathVariable Long id) {
        return ProductResponse.of(service.getById(id));
    }

    @PostMapping
    public ProductResponse create(@RequestBody ProductRequest request) {
        return ProductResponse.of(service.create(request));
    }

    @PutMapping(value = "{id}")
    public ProductResponse update(@RequestBody ProductRequest request, @PathVariable Long id) {
        return ProductResponse.of(service.update(request, id));
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping(value = "export-csv")
    public void exportCsv(HttpServletResponse response) throws IOException {
        service.exportCsv(response);
    }

}
