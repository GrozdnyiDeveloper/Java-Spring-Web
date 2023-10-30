package com.example.dbproject.controllers;

import com.example.dbproject.model.*;
import com.example.dbproject.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController {
    private final orderRepository OrderRepository;
    private final staffRepository StaffRepository;
    private final productRepository ProductRepository;
    private final storageRepository StorageRepository;
    private final supplierRepository SupplierRepository;
    private final eventRepository EventRepository;
    private final categoryRepository CategoryRepository;
    private final buyerRepository BuyerRepository;
    private final discountCardRepository DiscountCardRepository;
    private final cartRepository CartRepository;
    private final complianceRepository ComplianceRepository;
    private final concurrencyRepository ConcurrencyRepository;

    @Autowired
    public AdminController(orderRepository OrderRepository, productRepository ProductRepository, storageRepository StorageRepository, staffRepository StaffRepository, supplierRepository supplierRepository, eventRepository eventRepository, categoryRepository categoryRepository, buyerRepository buyerRepository, discountCardRepository discountCardRepository, cartRepository cartRepository, complianceRepository complianceRepository, concurrencyRepository concurrencyRepository) {
        this.OrderRepository = OrderRepository;
        this.ProductRepository = ProductRepository;
        this.StorageRepository = StorageRepository;
        this.StaffRepository = StaffRepository;
        this.SupplierRepository = supplierRepository;
        this.EventRepository = eventRepository;
        this.CategoryRepository = categoryRepository;
        this.BuyerRepository = buyerRepository;
        this.DiscountCardRepository = discountCardRepository;
        this.CartRepository = cartRepository;
        ComplianceRepository = complianceRepository;
        ConcurrencyRepository = concurrencyRepository;
    }

    @PutMapping("/staff")
    @ResponseBody public Staff update(@RequestBody Staff model){
        return StaffRepository.save(model);
    }

    @PutMapping("/buyer")
    @ResponseBody public Buyer update(@RequestBody Buyer model){
        return BuyerRepository.save(model);
    }

    @PutMapping("/card")
    @ResponseBody public Discount_Card update(@RequestBody Discount_Card model){
        model.setOwner(BuyerRepository.findById(model.getId()).get());
        return DiscountCardRepository.save(model);
    }

    @PutMapping("/cart")
    @ResponseBody public Cart update(@RequestBody Cart model){
        model.setCompliance(ComplianceRepository.findById(model.getCompliance().getId()).get());
        model.setBuyer(BuyerRepository.findById(model.getBuyer().getId()).get());
        return CartRepository.save(model);
    }

    @PostMapping("/staff")
    @ResponseBody public Staff create(@RequestBody Staff model){
        return StaffRepository.save(model);
    }

    @PostMapping("/buyer")
    @ResponseBody public Buyer create(@RequestBody Buyer model){
        return BuyerRepository.save(model);
    }

    @PostMapping("/card")
    public ResponseEntity createCard(@RequestBody Discount_Card model){
        model.setId(model.getOwner().getId());
        Buyer buyer = BuyerRepository.findById(model.getOwner().getId()).get();
        model.setOwner(buyer);
        DiscountCardRepository.save(model);
        buyer.setDiscountCard(model);
        BuyerRepository.save(buyer);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cart")
    @ResponseBody public Cart create(@RequestBody Cart model){
        model.setCompliance(ComplianceRepository.findById(model.getCompliance().getId()).get());
        model.setBuyer(BuyerRepository.findById(model.getBuyer().getId()).get());
        return CartRepository.save(model);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity deleteOrder(@PathVariable(name = "id") int id){
        OrderRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/staff/{id}")
    public ResponseEntity deleteStaff(@PathVariable(name = "id") int id){
        StaffRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity deleteProduct(@PathVariable(name = "id") int id){
        ProductRepository.findById(id).get().setSupplier(null);
        ProductRepository.findById(id).get().setStorage(null);
        ProductRepository.findById(id).get().setEvent(null);
        ProductRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/storage/{id}")
    public ResponseEntity deleteStorage(@PathVariable(name = "id") int id){
        StorageRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/supplier/{id}")
    public ResponseEntity deleteSupplier(@PathVariable(name = "id") int id){
        SupplierRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity deleteEvent(@PathVariable(name = "id") int id){
        EventRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity deleteCategory(@PathVariable(name = "id") int id){
        CategoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/buyer/{id}")
    public ResponseEntity deleteBuyer(@PathVariable(name = "id") int id){
        if (DiscountCardRepository.findById(id).isPresent()) DiscountCardRepository.deleteById(id);
        BuyerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/card/{id}")
    public ResponseEntity deleteCard(@PathVariable(name = "id") int id){
        BuyerRepository.findById(id).get().setDiscountCard(null);
        DiscountCardRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity deleteCart(@PathVariable(name = "id") int id){
        if (OrderRepository.findById(id).isPresent()) OrderRepository.deleteById(id);
        CartRepository.findById(id).get().setBuyer(null);
        CartRepository.findById(id).get().setCompliance(null);
        CartRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
