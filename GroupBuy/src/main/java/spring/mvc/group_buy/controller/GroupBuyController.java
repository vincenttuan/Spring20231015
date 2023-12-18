package spring.mvc.group_buy.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import spring.mvc.group_buy.model.dao.GroupBuyDao;
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
    @GetMapping(value = {"/login", "/"})
    public String loginPage() {
        return "group_buy/login";
    }

    // 前台登入處理
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {
        Optional<User> userOpt = dao.findUserByUsername(username);
        if(userOpt.isPresent() && password.equals(userOpt.get().getPassword())) {
            session.setAttribute("user", userOpt.get());
            return "redirect:/mvc/group_buy/frontend/main";
        } else {
            model.addAttribute("loginError", "無此使用者或密碼不正確");
            return "redirect:/mvc/group_buy/login";
        }
    }
    
    // 登出處理
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
        return "redirect:/mvc/group_buy/login";
    }
    
    // 團購首頁
    @GetMapping("/frontend/main")
    public String main(Model model) {
        List<Product> products = dao.findAllProducts();
        model.addAttribute("products", products);
        return "group_buy/frontend/main";
    }

    // 新增完成頁
    @PostMapping("/frontend/result")
    public String addToCart(@RequestParam("productId") int productId,
                            @RequestParam("quantity") int quantity,
                            HttpSession session,
                            Model model) {
        User user = (User) session.getAttribute("user");
        // 使用者購物車
		Cart cart = null;
		// 先找該 user 目前有沒有還未結帳的購物車
		Optional<Cart> cartOpt = dao.findNoneCheckoutCartByUserId(user.getUserId());
		if(cartOpt.isPresent()) {
			cart = cartOpt.get(); // 取得該使用者的購物車
		} else {
			cart = new Cart(); // 建立一個新的購物車
			cart.setUserId(user.getUserId());
			dao.addCart(cart); // 存放到資料表中
			
			// 新增之後馬上又要查詢建議可以下達一段 delay
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
			
			// 再抓一次使用者的 cart, 目的是要得到 carId
			cart = dao.findNoneCheckoutCartByUserId(user.getUserId()).get();
		}
		// 建立購物項目
		CartItem cartItem = new CartItem();
		cartItem.setCartId(cart.getCartId());
		cartItem.setProductId(productId);
		cartItem.setQuantity(quantity);
		// 新增商品到購物車
		dao.addCartItem(cartItem);
		
		model.addAttribute("product", dao.findProductById(productId).get());
		model.addAttribute("quantity", quantity);
        return "group_buy/frontend/result";
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
        return "group_buy/frontend/cart";
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
        return "group_buy/frontend/finish";
    }

    // 後台首頁
    @GetMapping("/backend/main")
    public String backendMain(Model model) {
        List<Product> products = dao.findAllProducts();
        model.addAttribute("products", products);
        return "group_buy/backend/main";
    }

    // 後台商品新增
    @PostMapping("/backend/result")
    public String addProduct(@ModelAttribute Product product, Model model) {
        dao.addProduct(product);
        model.addAttribute("product", product);
        return "group_buy/backend/result";
    }

    // 後台報表
    @GetMapping("/backend/report")
    public String report(@RequestParam(name = "userId", defaultValue = "0") int userId, Model model) {
        List<Map<String, Object>> reports = dao.calculateTotalAmountPerUser();
        model.addAttribute("reports", reports);
        // 揭露該 userId 的明細
		if(userId != 0) {
			List<Cart> carts = dao.findCartsbyUserIdAndCheckoutStatus(userId, true);
			//System.out.println(carts);
			model.addAttribute("carts", carts);
		}
        return "group_buy/backend/report";
    }

    // 修改购物车项目
    @GetMapping("/frontend/cart/update")
    public String updateCartItem(@RequestParam("itemId") int itemId,
                                 @RequestParam("quantity") int quantity,
                                 HttpSession session) {
    	//System.out.println("quantity:" + quantity);
        if (quantity > 0) {
            dao.updateCartItemQuantity(itemId, quantity);
        } else {
            dao.removeCartItemById(itemId);
        }
        return "redirect:/mvc/group_buy/frontend/cart";
    }

    // 删除购物车项目
    @GetMapping("/frontend/cart/delete")
    public String deleteCartItem(@RequestParam("itemId") int itemId,
                                 HttpSession session) {
    	System.out.println("itemId:" + itemId);
        dao.removeCartItemById(itemId);
        return "redirect:/mvc/group_buy/frontend/cart";
    }

    // 商品上下架处理
    @GetMapping("/backend/main/update_product_launch")
    public String updateProductLaunch(@RequestParam("productId") int productId,
                                      @RequestParam("isLaunch") boolean isLaunch,
                                      Model model) {
        dao.updateProductLaunch(productId, isLaunch);
        return "redirect:/mvc/group_buy/backend/main";
    }

}
