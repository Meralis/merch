import OrderCardItem from "./OrderCardItem";
import {ListGroup} from "react-bootstrap";

function OrderCard({products}) {
    return <ListGroup className={'my-4'}>
        {products.filter(product => product.addedToBasket).map(product => <OrderCardItem
            key={product.productId}
            product={product}/>)}

    </ListGroup>
}

export default OrderCard;

