import {ListGroup} from "react-bootstrap";
import BasketItem from "../basket/BasketItem";


function OrderCardItem({productToOrder}) {
    console.log(productToOrder)
    return <ListGroup.Item>
        <BasketItem product={productToOrder}/>
    </ListGroup.Item>
}

export default OrderCardItem;
