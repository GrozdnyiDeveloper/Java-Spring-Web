package com.example.dbproject.controllers;

import com.example.dbproject.model.*;
import com.example.dbproject.repos.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collections;

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
    private PasswordEncoder passwordEncoder;

    public String search;
    public String modelName;
    public String column;

    @Autowired
    public HomeController(orderRepository OrderRepository, productRepository ProductRepository, storageRepository StorageRepository, staffRepository StaffRepository, supplierRepository supplierRepository, eventRepository eventRepository, categoryRepository categoryRepository, buyerRepository buyerRepository, discountCardRepository discountCardRepository, userRepository userRepository, cartRepository cartRepository, PasswordEncoder passwordEncoder){
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
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping()
    public String mainPage(Model model){
        //Это поиск
        if (search != null && modelName != null && column != null) {
            switch (modelName) {
                case "product":
                    switch (column) {
                        case "Номер":
                            model.addAttribute("products", ProductRepository.findByNumber(search));
                            break;
                        case "Наименование":
                            model.addAttribute("products", ProductRepository.findByName(search));
                            break;
                        case "Количество":
                            model.addAttribute("products", ProductRepository.findByAmount(Integer.parseInt(search)));
                            break;
                        case "Цена":
                            model.addAttribute("products", ProductRepository.findByPrice(Double.parseDouble(search)));
                            break;
                        case "Хранилище":
                            model.addAttribute("products", ProductRepository.findByStorageAddress(search));
                            break;
                        case "Поставщик":
                            model.addAttribute("products", ProductRepository.findBySupplierName(search));
                            break;
                        case "Акция":
                            model.addAttribute("products", ProductRepository.findByEventName(search));
                            break;
                    }
                    break;
                case "storage":
                    switch (column) {
                        case "Адрес":
                            model.addAttribute("storages", StorageRepository.findByAddress(search));
                            break;
                        case "Вместительность":
                            model.addAttribute("storages", StorageRepository.findByCapacity(Integer.parseInt(search)));
                            break;
                    }
                    break;
                case "supplier":
                    switch (column) {
                        case "Название":
                            model.addAttribute("suppliers", SupplierRepository.findByName(search));
                            break;
                    }
                    break;
                case "event":
                    switch (column) {
                        case "Название":
                            model.addAttribute("events", EventRepository.findByName(search));
                            break;
                    }
                    break;
                case "staff":
                    switch (column) {
                        case "Фамилия":
                            model.addAttribute("staffs", StaffRepository.findByLastName(search));
                            break;
                        case "Имя":
                            model.addAttribute("staffs", StaffRepository.findByName(search));
                            break;
                        case "Отчество":
                            model.addAttribute("staffs", StaffRepository.findByMiddleName(search));
                            break;
                        case "Пол":
                            model.addAttribute("staffs", StaffRepository.findByGender(search));
                            break;
                        case "Почта":
                            model.addAttribute("staffs", StaffRepository.findByEmail(search));
                            break;
                        case "Пароль":
                            model.addAttribute("staffs", StaffRepository.findByPassword(search));
                            break;
                    }
                    break;
                case "order":
                    switch (column) {
                        case "Номер":
                            model.addAttribute("orders", OrderRepository.findByNumber(Integer.parseInt(search)));
                            break;
                        case "Дата составления":
                            model.addAttribute("orders", OrderRepository.findByDate(Date.valueOf(search)));
                            break;
                        case "Факт оплаты":
                            model.addAttribute("orders", OrderRepository.findByPayment(Boolean.getBoolean(search)));
                            break;
                    }
                    break;
                case "category":
                    switch (column) {
                        case "Название":
                            model.addAttribute("categories", CategoryRepository.findByName(search));
                            break;
                    }
                    break;
                case "buyer":
                    switch (column) {
                        case "Почта":
                            model.addAttribute("buyers", BuyerRepository.findByEmail(search));
                            break;
                        case "Телефон":
                            model.addAttribute("buyers", BuyerRepository.findByPhone(search));
                            break;
                        case "Пароль":
                            model.addAttribute("buyers", BuyerRepository.findByPassword(search));
                            break;
                        case "Номер скидочной карты":
                            model.addAttribute("buyers", BuyerRepository.findByDiscountCardNumber(Integer.parseInt(search)));
                            break;
                        case "Размер скидки":
                            model.addAttribute("buyers", BuyerRepository.findByDiscountCardDiscount(Integer.parseInt(search)));
                            break;
                    }
                    break;
                case "card":
                    switch (column) {
                        case "Номер":
                            model.addAttribute("cards", DiscountCardRepository.findByNumber(Integer.parseInt(search)));
                            break;
                        case "Скидка":
                            model.addAttribute("cards", DiscountCardRepository.findByDiscount(Integer.parseInt(search)));
                            break;
                        case "Владелец":
                            model.addAttribute("cards", DiscountCardRepository.findByOwnerEmail(search));
                            break;
                    }
                    break;
                case "cart":
                    switch (column) {
                        case "Количество":
                            model.addAttribute("carts", CartRepository.findByAmount(Integer.parseInt(search)));
                            break;
                        case "Покупатель":
                            model.addAttribute("carts", CartRepository.findByBuyerEmail(search));
                            break;
                        case "Продукт":
                            model.addAttribute("carts", CartRepository.findByComplianceProductNumber(search));
                            break;
                    }
                    break;
            }
            search = null;
            modelName = null;
            column = null;
        }
        else {
            model.addAttribute("orders", OrderRepository.findAll());
            model.addAttribute("staffs", StaffRepository.findAll());
            model.addAttribute("products", ProductRepository.findAll());
            model.addAttribute("storages", StorageRepository.findAll());
            model.addAttribute("suppliers", SupplierRepository.findAll());
            model.addAttribute("events", EventRepository.findAll());
            model.addAttribute("categories", CategoryRepository.findAll());
            model.addAttribute("buyers", BuyerRepository.findAll());
            model.addAttribute("cards", DiscountCardRepository.findAll());
            model.addAttribute("carts", CartRepository.findAll());
        }
        return "main";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("error", false);
        return "registration";
    }

    @PostMapping("/registration")
    private String registration(Userr user, Model model)
    {
        Userr check = UserRepository.findByUsername(user.getUsername());
        if (check != null)
        {
            model.addAttribute("error", true);
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(roleEnum.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserRepository.save(user);
        return "redirect:/login";
    }

    @PostMapping("/search")
    public String search(@RequestParam(value = "model") String model, @RequestParam(value = "column") String column, @RequestParam(value = "search") String search) {
        modelName = model;
        this.search = search;
        this.column = column;
        return "redirect:/";
    }
}
