import {ListGroup} from "react-bootstrap";
import BasketItem from "./BasketItem";


function OrderCardItem({productToOrder}) {
    console.log(productToOrder)
    return <ListGroup.Item>
        <BasketItem product={productToOrder}/>
    </ListGroup.Item>
}

export default OrderCardItem;
