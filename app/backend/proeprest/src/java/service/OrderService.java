/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;
import model.Item;
import model.Order;

/**
 * @author Amir
 **/
public class OrderService {

    List<Order> orderList = new ArrayList<>();
    
    public OrderService() {
        orderList.add(new Order(0, "Tycho"));
        orderList.add(new Order(1, "Tycho"));
        Order ord = new Order(2, "Tom");
        orderList.add(ord);
    }

    public List<Order> getOrdersByName(String username) {
        List<Order> returnorders = new ArrayList<>();
        for (Order o : orderList) {
            if (username.equals(o.getOwner())) {
                returnorders.add(o);
            }
        }
        return returnorders;
    }

    public boolean createOrder(Order orderNew) {
        for (Order order : orderList) {
            if (order.getOwner().equals(orderNew.getOwner())) {
                return false;
            }
        }
        orderList.add(orderNew);
        return true;
    }

    public boolean updateOrder(Order or) {
        for (Order order : orderList) {
            if (order.getID() == or.getID()) {
                order = or;
                return true;
            }
        }
        return false;
    }

    public boolean AddItemToOrder(Order o, Item it) {
        o.AddItemToOrder(it);
//        for (Item item : getOrderByName(username)) {
//            if (order.getID() == or.getID()) {
//                order = or;
//                return true;
//            }
//        }
        return true;
    }

    public boolean AddOrder(Order ord) {
        for (Order order : orderList) {
            if (order.getID() == ord.getID()) {
                return false;
            }
        }
        orderList.add(ord);
        return true;
    }
}
