package org.klim.istock.converter;

import org.klim.istock.DTO.OrderDTO;
import org.klim.istock.DTO.OrderItemDTO;
import org.klim.istock.entity.Client;
import org.klim.istock.entity.Order;
import org.klim.istock.entity.OrderItem;
import org.klim.istock.entity.Product;
import org.klim.istock.service.ClientService;
import org.klim.istock.service.ProductService;
import org.klim.istock.util.ModelMapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class OrderConverter implements IConverter<OrderDTO, Order> {
    private final ModelMapperUtil modelMapper;
    private final ProductService productService;
    private final ClientService clientService;

    public OrderConverter(ModelMapperUtil modelMapper, ProductService productService, ClientService clientService) {
        this.modelMapper = modelMapper;
        this.productService = productService;
        this.clientService = clientService;
    }

    @Override
    public Order toEntity(OrderDTO orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        List<OrderItemDTO> dtoItems = orderDto.getItems();
        List<OrderItem> entityItems = order.getItems();
        IntStream.range(0, orderDto.getItems().size())
                .forEach(i -> {
                    OrderItem orderItem = entityItems.get(i);
                    Integer productId = dtoItems.get(i).getProductId();
                    Product product = productService.find(productId);
                    orderItem.setProduct(product);
                    orderItem.setOrder(order);
                });
        Client requestClient = order.getClient();
        Client savedClient = clientService.findByEmail(requestClient.getEmail());
        if (savedClient != null) {
            order.setClient(savedClient);
        }
        return order;
    }

    @Override
    public OrderDTO toDto(Order entity) {
        OrderDTO orderDto = modelMapper.map(entity, OrderDTO.class);
        List<OrderItemDTO> dtoItems = orderDto.getItems();
        List<OrderItem> entityItems = entity.getItems();
        IntStream.range(0, entity.getItems().size())
                .forEach(i -> {
                    int productId = entityItems.get(i).getProduct().getProductId();
                    dtoItems.get(i).setProductId(productId);
                });
        return orderDto;
    }
}
