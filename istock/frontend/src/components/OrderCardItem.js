import {ListGroup} from "react-bootstrap";

function OrderCardItem({productToOrder}) {
    console.log(productToOrder)
    return <ListGroup.Item>
      <div>{productToOrder.title}</div>

    </ListGroup.Item>
}
export default OrderCardItem;
