package com.sayurbox.sayurbox.controllers;

import com.sayurbox.sayurbox.models.Order;
import com.sayurbox.sayurbox.models.CartItem;
import com.sayurbox.sayurbox.models.Courier;
import com.sayurbox.sayurbox.models.User;
import com.sayurbox.sayurbox.services.CourierService;
import com.sayurbox.sayurbox.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CourierService courierService;

    @PostMapping("/order/create")
    public String createOrder(HttpSession session, Model model) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        User currentUser = (User) session.getAttribute("user");

        if (cartItems == null || cartItems.isEmpty()) {
            model.addAttribute("error",
                    "Keranjang Anda kosong. Tambahkan item ke keranjang sebelum melakukan pemesanan.");
            return "cart";
        }

        if (currentUser == null) {
            model.addAttribute("error", "Anda harus login untuk melakukan pemesanan.");
            return "login";
        }

        int totalItems = cartItems.stream().mapToInt(CartItem::getQuantity).sum();
        double totalPrice = cartItems.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();

        Order order = orderService.createOrder(totalItems, totalPrice, currentUser);
        session.removeAttribute("cart");

        model.addAttribute("order", order);
        model.addAttribute("cartItems", cartItems);

        // Pastikan harga sudah terhitung dengan benar
        System.out.println("Total Price: " + totalPrice); // Debug untuk memastikan harga benar

        return "order-confirmation"; // Pastikan harga yang ditampilkan sudah benar
    }

    @GetMapping("/order-list")
    public String orderList(HttpSession session, Model model) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        User currentUser = (User) session.getAttribute("user");
        List<Order> orders = orderService.getOrdersByUser(currentUser);
        model.addAttribute("orders", orders);
        return "order-list";
    }

    @GetMapping("/assign")
    public String assignCourierPage(Model model) {
        List<Order> unassignedOrders = orderService.getUnassignedOrders();
        List<Order> assignedOrders = orderService.getAssignedOrders();
        List<Courier> couriers = courierService.getAllCouriers();
        model.addAttribute("unassignedOrders", unassignedOrders);
        model.addAttribute("assignedOrders", assignedOrders);
        model.addAttribute("couriers", couriers);
        return "assign-courier";
    }

    // Menyimpan kurir ke pesanan
    @PostMapping("/assign")
    public String assignCourier(@RequestParam Long orderId, @RequestParam Long courierId) {
        orderService.assignCourierToOrder(orderId, courierId);
        return "redirect:/assign";
    }

}
