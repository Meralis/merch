import OrderCardItem from "./OrderCardItem";
import {ListGroup} from "react-bootstrap";

function OrderCard() {
    const basketItems = localStorage.getItem('basketItems');
    console.log(basketItems)


    return <ListGroup className={'my-4'}>
       <div>{products}</div>
        {products.filter(product => product.addedToBasket).map(product => <OrderCardItem
            key={product.productId}
            product={product}/>)}

    </ListGroup>
}

export default OrderCard;

