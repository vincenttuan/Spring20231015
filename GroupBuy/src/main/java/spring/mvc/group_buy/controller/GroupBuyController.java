package spring.mvc.group_buy.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import spring.mvc.group_buy.model.dao.GroupBuyDao;
import spring.mvc.group_buy.model.dao.GroupBuyDaoMySQL;
import spring.mvc.group_buy.model.entity.Cart;
import spring.mvc.group_buy.model.entity.CartItem;
import spring.mvc.group_buy.model.entity.Product;
import spring.mvc.group_buy.model.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/group_buy")
public class GroupBuyController {
	
	@Autowired
    private GroupBuyDao dao;

    // 登入首頁
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // 前台登入處理
    @PostMapping("/frontend/main")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {
        Optional<User> userOpt = dao.findUserByUsername(username);
        if(userOpt.isPresent() && password.equals(userOpt.get().getPassword())) {
            session.setAttribute("user", userOpt.get());
            return "redirect:/group_buy/frontend/main";
        } else {
            model.addAttribute("loginError", "無此使用者或密碼不正確");
            return "redirect:/group_buy/login";
        }
    }

    // 團購首頁
    @GetMapping("/frontend/main")
    public String main(Model model) {
        List<Product> products = dao.findAllProducts();
        model.addAttribute("products", products);
        return "frontend/main";
    }

    // 新增完成頁
    @PostMapping("/frontend/result")
    public String addToCart(@RequestParam("productId") int productId,
                            @RequestParam("quantity") int quantity,
                            HttpSession session,
                            Model model) {
        User user = (User) session.getAttribute("user");
        // 添加到购物车逻辑...
        // ...
        return "frontend/result";
    }

    // 購物車頁面
    @GetMapping("/frontend/cart")
    public String cartPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Optional<Cart> cartOpt = dao.findNoneCheckoutCartByUserId(user.getUserId());
        cartOpt.ifPresent(cart -> {
            int total = cart.getCartItems().stream()
                            .mapToInt(item -> item.getQuantity() * item.getProduct().getPrice())
                            .sum();
            model.addAttribute("cart", cart);
            model.addAttribute("total", total);
        });
        return "frontend/cart";
    }

    // 結帳成功
    @GetMapping("/frontend/finish")
    public String checkout(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Optional<Cart> cartOpt = dao.findNoneCheckoutCartByUserId(user.getUserId());
        cartOpt.ifPresent(cart -> {
            dao.checkoutCartById(cart.getCartId());
            model.addAttribute("cart", cart);
        });
        return "frontend/finish";
    }

    // 後台首頁
    @GetMapping("/backend/main")
    public String backendMain(Model model) {
        List<Product> products = dao.findAllProducts();
        model.addAttribute("products", products);
        return "backend/main";
    }

    // 後台商品新增
    @PostMapping("/backend/result")
    public String addProduct(@ModelAttribute Product product, Model model) {
        dao.addProduct(product);
        model.addAttribute("product", product);
        return "backend/result";
    }

    // 後台報表
    @GetMapping("/backend/report")
    public String report(Model model) {
        List<Map<String, Object>> reports = dao.calculateTotalAmountPerUser();
        model.addAttribute("reports", reports);
        return "backend/report";
    }

 // 修改购物车项目
    @PostMapping("/frontend/cart/update")
    public String updateCartItem(@RequestParam("itemId") int itemId,
                                 @RequestParam("quantity") int quantity,
                                 HttpSession session) {
        if (quantity > 0) {
            dao.updateCartItemQuantity(itemId, quantity);
        } else {
            dao.removeCartItemById(itemId);
        }
        return "redirect:/group_buy/frontend/cart";
    }

    // 删除购物车项目
    @PostMapping("/frontend/cart/delete")
    public String deleteCartItem(@RequestParam("itemId") int itemId,
                                 HttpSession session) {
        dao.removeCartItemById(itemId);
        return "redirect:/group_buy/frontend/cart";
    }

    // 商品上下架处理
    @PostMapping("/backend/main/update")
    public String updateProductLaunch(@RequestParam("productId") int productId,
                                      @RequestParam("isLaunch") boolean isLaunch,
                                      Model model) {
        dao.updateProductLaunch(productId, isLaunch);
        return "redirect:/group_buy/backend/main";
    }

}
