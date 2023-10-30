package com.example.dbproject.controllers;

import com.example.dbproject.model.*;
import com.example.dbproject.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
public class ManagerController {
    private final orderRepository OrderRepository;
    private final staffRepository StaffRepository;
    private final productRepository ProductRepository;
    private final storageRepository StorageRepository;
    private final supplierRepository SupplierRepository;
    private final eventRepository EventRepository;
    private final categoryRepository CategoryRepository;
    private final buyerRepository BuyerRepository;
    private final discountCardRepository DiscountCardRepository;
    private final userRepository UserRepository;
    private final complianceRepository ComplianceRepository;
    private final concurrencyRepository ConcurrencyRepository;
    private final cartRepository CartRepository;

    @Autowired
    public ManagerController(orderRepository OrderRepository, productRepository ProductRepository, storageRepository StorageRepository, staffRepository StaffRepository, supplierRepository supplierRepository, eventRepository eventRepository, categoryRepository categoryRepository, buyerRepository buyerRepository, discountCardRepository discountCardRepository, userRepository userRepository, complianceRepository complianceRepository, concurrencyRepository concurrencyRepository, cartRepository cartRepository){
        this.OrderRepository = OrderRepository;
        this.ProductRepository = ProductRepository;
        this.StorageRepository = StorageRepository;
        this.StaffRepository = StaffRepository;
        this.SupplierRepository = supplierRepository;
        this.EventRepository = eventRepository;
        this.CategoryRepository = categoryRepository;
        this.BuyerRepository = buyerRepository;
        this.DiscountCardRepository = discountCardRepository;
        this.UserRepository = userRepository;
        this.ComplianceRepository = complianceRepository;
        this.ConcurrencyRepository = concurrencyRepository;
        this.CartRepository = cartRepository;
    }

    @PutMapping("/order")
    @ResponseBody public Orderr update(@RequestBody Orderr model){
        model.setConcurrency(ConcurrencyRepository.findById(model.getConcurrency().getId()).get());
        model.setDelivery_ID(1);
        model.setStatus_ID(1);
        model.setOwner(CartRepository.findById(model.getOwner().getId()).get());
        return OrderRepository.save(model);
    }

    @PutMapping("/product")
    @ResponseBody public Product update(@RequestBody Product model){
        model.setStorage(StorageRepository.findById(model.getStorage().getId()).get());
        model.setSupplier(SupplierRepository.findById(model.getSupplier().getId()).get());
        model.setEvent(EventRepository.findById(model.getEvent().getId()).get());
        return ProductRepository.save(model);
    }

    @PutMapping("/storage")
    @ResponseBody public Storage update(@RequestBody Storage model){
        return StorageRepository.save(model);
    }

    @PutMapping("/supplier")
    @ResponseBody public Supplier update(@RequestBody Supplier model){
        return SupplierRepository.save(model);
    }

    @PutMapping("/event")
    @ResponseBody public Event update(@RequestBody Event model){
        return EventRepository.save(model);
    }

    @PutMapping("/category")
    @ResponseBody public Category update(@RequestBody Category model){
        return CategoryRepository.save(model);
    }

    @PostMapping("/order")
    public ResponseEntity create(@RequestBody Orderr model){
        model.setConcurrency(ConcurrencyRepository.findById(model.getConcurrency().getId()).get());
        model.setDelivery_ID(1);
        model.setStatus_ID(1);
        Cart cart = CartRepository.findById(model.getOwner().getId()).get();
        model.setOwner(cart);
        OrderRepository.save(model);
        cart.setOrderr(model);
        CartRepository.save(cart);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/product")
    @ResponseBody public Product create(@RequestBody Product model){
        model.setStorage(StorageRepository.findById(model.getStorage().getId()).get());
        model.setSupplier(SupplierRepository.findById(model.getSupplier().getId()).get());
        model.setEvent(EventRepository.findById(model.getEvent().getId()).get());
        return ProductRepository.save(model);
    }

    @PostMapping("/storage")
    @ResponseBody public Storage create(@RequestBody Storage model){
        return StorageRepository.save(model);
    }

    @PostMapping("/supplier")
    @ResponseBody public Supplier create(@RequestBody Supplier model){
        return SupplierRepository.save(model);
    }

    @PostMapping("/event")
    @ResponseBody public Event create(@RequestBody Event model){
        return EventRepository.save(model);
    }

    @PostMapping("/category")
    @ResponseBody public Category create(@RequestBody Category model){
        return CategoryRepository.save(model);
    }

    @PostMapping("/addCategory")
    public ResponseEntity add(@RequestBody forCategory model){
        Product product = ProductRepository.findById(model.getProductId()).get();
        Category category = CategoryRepository.findById(model.getCategoryId()).get();
        if (!product.getCategories().contains(category))
        {
            product.getCategories().add(category);
            category.getProducts().add(product);
            CategoryRepository.save(category);
            ProductRepository.save(product);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/removeCategory")
    public ResponseEntity remove(@RequestBody forCategory model){
        Product product = ProductRepository.findById(model.getProductId()).get();
        Category category = CategoryRepository.findById(model.getCategoryId()).get();
        product.getCategories().remove(category);
        category.getProducts().remove(product);
        CategoryRepository.save(category);
        ProductRepository.save(product);
        return ResponseEntity.ok().build();
    }
}
