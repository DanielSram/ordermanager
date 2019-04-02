package cz.vse.ordermanager;

import cz.vse.ordermanager.customer.Customer;
import cz.vse.ordermanager.order.Order;
import cz.vse.ordermanager.order.OrderRepository;
import cz.vse.ordermanager.order.OrderState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Tests {

    @Value("${app.clientId}")
    private String clientId;

    @Value("${app.clientSecret}")
    private String clientSecret;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testCreateOrder() {
        CreateOrderRequest request = new CreateOrderRequest();
        request.getCustomer().setName("Prokop Buben");
        request.getCustomer().setPhone("+420731985826");
        request.addItem(1, testRestTemplate.getRootUri() + "/product/100");

        ResponseEntity<String> reponse = testRestTemplate
                .withBasicAuth(clientId, clientSecret)
                .postForEntity("/orders", request, String.class);

        assertThat(reponse.getStatusCode(), is(HttpStatus.CREATED));

        List<Order> orders = orderRepository.findByCustomerName(request.getCustomer().getName());
        assertThat(orders.size(), is(1));

        Order order = orders.get(0);
        assertThat(order.getState(), is(OrderState.CREATED));

        Customer customer = order.getCustomer();
        assertThat(customer.getName(), is(request.getCustomer().getName()));
        assertThat(customer.getPhone(), is(request.getCustomer().getPhone()));

        assertThat(order.getItems().size(), is(1));
        assertThat(order.getItems().get(0).getQuantity(), is(1));
        assertThat(order.getItems().get(0).getProduct().getName(), is("Milk"));
    }

}
