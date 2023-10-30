package com.example.dbproject.controllers;

import com.example.dbproject.model.*;
import com.example.dbproject.repos.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
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
    private final cartRepository CartRepository;
    private final complianceRepository ComplianceRepository;
    private final concurrencyRepository ConcurrencyRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public HomeController(orderRepository OrderRepository, productRepository ProductRepository, storageRepository StorageRepository, staffRepository StaffRepository, supplierRepository supplierRepository, eventRepository eventRepository, categoryRepository categoryRepository, buyerRepository buyerRepository, discountCardRepository discountCardRepository, userRepository userRepository, cartRepository cartRepository, complianceRepository complianceRepository, concurrencyRepository concurrencyRepository, PasswordEncoder passwordEncoder){
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
        this.CartRepository = cartRepository;
        ComplianceRepository = complianceRepository;
        ConcurrencyRepository = concurrencyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/order")
    @ResponseBody
    List<Orderr> getAllOrder() {
        List<Orderr> orders = (List<Orderr>) OrderRepository.findAll();
        for (var order:orders) {
            order.getConcurrency().setStaff(null);
            order.getConcurrency().setTenants(null);
            order.getOwner().setOrderr(null);
            order.getOwner().setBuyer(null);
            order.getOwner().setCompliance(null);
        }
        return (List<Orderr>) OrderRepository.findAll();
    }

    @GetMapping("/order/{id}")
    @ResponseBody
    Optional<Orderr> getOrder(@PathVariable(name = "id") int id){
        var order = OrderRepository.findById(id);
        order.get().getConcurrency().setStaff(null);
        order.get().getConcurrency().setTenants(null);
        order.get().getOwner().setOrderr(null);
        order.get().getOwner().setBuyer(null);
        order.get().getOwner().setCompliance(null);
        return order;
    }

    @GetMapping("/staff")
    @ResponseBody
    List<Staff> getAllStaff() {
        List<Staff> staffs = (List<Staff>) StaffRepository.findAll();
        staffs.forEach(staff -> staff.getTenants().forEach(concurrency -> concurrency.getStaff().setTenants(null)));
        return staffs;
    }

    @GetMapping("/staff/{id}")
    @ResponseBody
    Optional<Staff> getStaff(@PathVariable(name = "id") int id){
        var staff = StaffRepository.findById(id);
        staff.get().getTenants().forEach(concurrency -> concurrency.getStaff().setTenants(null));
        return staff;
    }

    @GetMapping("/product")
    @ResponseBody
    List<Product> getAllProduct() {
        List<Product> products = (List<Product>) ProductRepository.findAll();
        for (var product:products) {
            product.getEvent().setTenants(null);
            product.getStorage().setTenants(null);
            product.getSupplier().setTenants(null);
            product.setTenants(null);
            if (product.getCategories() != null) {
                product.getCategories().forEach(category -> category.setProducts(null));
                product.getCategories().forEach(category -> category.setTenants(null));
            }
        }
        return products;
    }

    @GetMapping("/product/{id}")
    @ResponseBody
    Optional<Product> getProduct(@PathVariable(name = "id") int id){
        var product = ProductRepository.findById(id);
        product.get().getEvent().setTenants(null);
        product.get().getStorage().setTenants(null);
        product.get().getSupplier().setTenants(null);
        product.get().setTenants(null);
        if (product.get().getCategories() != null) {
            product.get().getCategories().forEach(category -> category.setProducts(null));
            product.get().getCategories().forEach(category -> category.setTenants(null));
        }
        return product;
    }

    @GetMapping("/storage")
    @ResponseBody
    List<Storage> getAllStorage() {
        List<Storage> storages = (List<Storage>) StorageRepository.findAll();
        storages.forEach(storage -> storage.getTenants().forEach(product -> product.setStorage(null)));
        storages.forEach(storage -> storage.getTenants().forEach(product -> product.setSupplier(null)));
        storages.forEach(storage -> storage.getTenants().forEach(product -> product.setEvent(null)));
        storages.forEach(storage -> storage.getTenants().forEach(product -> product.setCategories(null)));
        storages.forEach(storage -> storage.getTenants().forEach(product -> product.setTenants(null)));
        return storages;
    }

    @GetMapping("/storage/{id}")
    @ResponseBody
    Optional<Storage> getStorage(@PathVariable(name = "id") int id){
        var storage = StorageRepository.findById(id);
        storage.get().getTenants().forEach(product -> product.setStorage(null));
        storage.get().getTenants().forEach(product -> product.setSupplier(null));
        storage.get().getTenants().forEach(product -> product.setEvent(null));
        storage.get().getTenants().forEach(product -> product.setCategories(null));
        storage.get().getTenants().forEach(product -> product.setTenants(null));
        return storage;
    }

    @GetMapping("/supplier")
    @ResponseBody
    List<Supplier> getAllSupplier() {
        List<Supplier> suppliers = (List<Supplier>) SupplierRepository.findAll();
        suppliers.forEach(storage -> storage.getTenants().forEach(product -> product.setStorage(null)));
        suppliers.forEach(storage -> storage.getTenants().forEach(product -> product.setSupplier(null)));
        suppliers.forEach(storage -> storage.getTenants().forEach(product -> product.setEvent(null)));
        suppliers.forEach(storage -> storage.getTenants().forEach(product -> product.setCategories(null)));
        suppliers.forEach(storage -> storage.getTenants().forEach(product -> product.setTenants(null)));
        return suppliers;
    }

    @GetMapping("/supplier/{id}")
    @ResponseBody
    Optional<Supplier> getSupplier(@PathVariable(name = "id") int id){
        var supplier = SupplierRepository.findById(id);
        supplier.get().getTenants().forEach(product -> product.setStorage(null));
        supplier.get().getTenants().forEach(product -> product.setSupplier(null));
        supplier.get().getTenants().forEach(product -> product.setEvent(null));
        supplier.get().getTenants().forEach(product -> product.setCategories(null));
        supplier.get().getTenants().forEach(product -> product.setTenants(null));
        return supplier;
    }

    @GetMapping("/event")
    @ResponseBody
    List<Event> getAllEvent() {
        List<Event> events = (List<Event>) EventRepository.findAll();
        events.forEach(storage -> storage.getTenants().forEach(product -> product.setStorage(null)));
        events.forEach(storage -> storage.getTenants().forEach(product -> product.setSupplier(null)));
        events.forEach(storage -> storage.getTenants().forEach(product -> product.setEvent(null)));
        events.forEach(storage -> storage.getTenants().forEach(product -> product.setCategories(null)));
        events.forEach(storage -> storage.getTenants().forEach(product -> product.setTenants(null)));
        return events;
    }

    @GetMapping("/event/{id}")
    @ResponseBody
    Optional<Event> getEvent(@PathVariable(name = "id") int id){
        var event = EventRepository.findById(id);
        event.get().getTenants().forEach(product -> product.setStorage(null));
        event.get().getTenants().forEach(product -> product.setSupplier(null));
        event.get().getTenants().forEach(product -> product.setEvent(null));
        event.get().getTenants().forEach(product -> product.setCategories(null));
        event.get().getTenants().forEach(product -> product.setTenants(null));
        return event;
    }

    @GetMapping("/category")
    @ResponseBody
    List<Category> getAllCategory() {
        List<Category> categories = (List<Category>) CategoryRepository.findAll();
        categories.forEach(storage -> storage.getProducts().forEach(product -> product.setStorage(null)));
        categories.forEach(storage -> storage.getProducts().forEach(product -> product.setSupplier(null)));
        categories.forEach(storage -> storage.getProducts().forEach(product -> product.setEvent(null)));
        categories.forEach(storage -> storage.getProducts().forEach(product -> product.setCategories(null)));
        categories.forEach(storage -> storage.getProducts().forEach(product -> product.setTenants(null)));
        categories.forEach(storage -> storage.setTenants(null));
        return categories;
    }

    @GetMapping("/category/{id}")
    @ResponseBody
    Optional<Category> getCategory(@PathVariable(name = "id") int id){
        var category = CategoryRepository.findById(id);
        category.get().getProducts().forEach(product -> product.setStorage(null));
        category.get().getProducts().forEach(product -> product.setSupplier(null));
        category.get().getProducts().forEach(product -> product.setEvent(null));
        category.get().getProducts().forEach(product -> product.setCategories(null));
        category.get().getProducts().forEach(product -> product.setTenants(null));
        category.get().setTenants(null);
        return category;
    }

    @GetMapping("/buyer")
    @ResponseBody
    List<Buyer> getAllBuyer() {
        List<Buyer> buyers = (List<Buyer>) BuyerRepository.findAll();
        for (var buyer:buyers) if (buyer.getDiscountCard() != null) buyer.getDiscountCard().setOwner(null);
        return buyers;
    }

    @GetMapping("/buyer/{id}")
    @ResponseBody
    Optional<Buyer> getBuyer(@PathVariable(name = "id") int id){
        var buyer = BuyerRepository.findById(id);
        if (buyer.get().getDiscountCard() != null) buyer.get().getDiscountCard().setOwner(null);
        return buyer;
    }

    @GetMapping("/card")
    @ResponseBody
    List<Discount_Card> getAllCard() {
        List<Discount_Card> cards = (List<Discount_Card>) DiscountCardRepository.findAll();
        cards.forEach(discountCard -> discountCard.getOwner().setDiscountCard(null));
        return cards;
    }

    @GetMapping("/card/{id}")
    @ResponseBody
    Optional<Discount_Card> getCard(@PathVariable(name = "id") int id){
        var card = DiscountCardRepository.findById(id);
        card.get().getOwner().setDiscountCard(null);
        return card;
    }

    @GetMapping("/cart")
    @ResponseBody
    List<Cart> getAllCart() {
        List<Cart> carts = (List<Cart>) CartRepository.findAll();
        for (var cart:carts) {
            cart.getBuyer().setDiscountCard(null);
            cart.getCompliance().getCategory().setProducts(null);
            cart.getCompliance().getCategory().setTenants(null);
            cart.getCompliance().getProduct().setEvent(null);
            cart.getCompliance().getProduct().setStorage(null);
            cart.getCompliance().getProduct().setSupplier(null);
            cart.getCompliance().getProduct().setCategories(null);
            cart.getCompliance().getProduct().setTenants(null);
            if (cart.getOrderr() != null) {
                cart.getOrderr().setConcurrency(null);
                cart.getOrderr().setOwner(null);
            }
        }
        return carts;
    }

    @GetMapping("/cart/{id}")
    @ResponseBody
    Optional<Cart> getCart(@PathVariable(name = "id") int id){
        var cart = CartRepository.findById(id);
        cart.get().getBuyer().setDiscountCard(null);
        cart.get().getCompliance().getCategory().setProducts(null);
        cart.get().getCompliance().getCategory().setTenants(null);
        cart.get().getCompliance().getProduct().setEvent(null);
        cart.get().getCompliance().getProduct().setStorage(null);
        cart.get().getCompliance().getProduct().setSupplier(null);
        cart.get().getCompliance().getProduct().setCategories(null);
        cart.get().getCompliance().getProduct().setTenants(null);
        if (cart.get().getOrderr() != null) {
            cart.get().getOrderr().setConcurrency(null);
            cart.get().getOrderr().setOwner(null);
        }
        return cart;
    }

    @GetMapping("/compliance")
    @ResponseBody
    List<Compliance> getAllCompliance() {
        List<Compliance> compliances = (List<Compliance>) ComplianceRepository.findAll();
        compliances.forEach(compliance -> compliance.getProduct().setEvent(null));
        compliances.forEach(compliance -> compliance.getProduct().setSupplier(null));
        compliances.forEach(compliance -> compliance.getProduct().setStorage(null));
        compliances.forEach(compliance -> compliance.getProduct().setCategories(null));
        compliances.forEach(compliance -> compliance.getProduct().setTenants(null));
        compliances.forEach(compliance -> compliance.getCategory().setProducts(null));
        compliances.forEach(compliance -> compliance.getCategory().setTenants(null));
        return compliances;
    }

    @GetMapping("/compliance/{id}")
    @ResponseBody
    Optional<Compliance> getCompliance(@PathVariable(name = "id") int id){
        var compliance = ComplianceRepository.findById(id);
        compliance.get().getProduct().setEvent(null);
        compliance.get().getProduct().setSupplier(null);
        compliance.get().getProduct().setStorage(null);
        compliance.get().getProduct().setCategories(null);
        compliance.get().getProduct().setTenants(null);
        compliance.get().getCategory().setProducts(null);
        compliance.get().getCategory().setTenants(null);
        return compliance;
    }

    @GetMapping("/concurrency")
    @ResponseBody
    List<Concurrency> getAllConcurrency() {
        List<Concurrency> concurrencies = (List<Concurrency>) ConcurrencyRepository.findAll();
        concurrencies.forEach(concurrency -> concurrency.setTenants(null));
        concurrencies.forEach(concurrency -> concurrency.getStaff().setTenants(null));
        return concurrencies;
    }

    @GetMapping("/concurrency/{id}")
    @ResponseBody
    Optional<Concurrency> getConcurrency(@PathVariable(name = "id") int id){
        var concurrency = ConcurrencyRepository.findById(id);
        concurrency.get().setTenants(null);
        concurrency.get().getStaff().setTenants(null);
        return concurrency;
    }

    @PostMapping("/registration")
    private ResponseEntity registration(Userr user, Model model)
    {
        Userr check = UserRepository.findByUsername(user.getUsername());
        if (check != null)
        {
            return ResponseEntity.badRequest().build();
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(roleEnum.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
