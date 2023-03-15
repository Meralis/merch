import {ListGroup} from "react-bootstrap";
import {useContext} from "react";
import ProductContext from "../context/ProductContext";
import OrderCardItem from "./OrderCardItem";

function getProductById(savedItem, products) {
    const foundProducts = products.filter(product => product.productId === savedItem.productId);
    if (foundProducts.length > 0) {
        return {...savedItem, ...foundProducts[0]};
    }
    return undefined;
}

function OrderCard({basketItems}) {
    const [products] = useContext(ProductContext);
    const savedProducts = basketItems.map(basketItem => getProductById(basketItem, products))
        .filter(product => product);
    return <ListGroup className={'my-4'}>
        {savedProducts.map(product => <OrderCardItem key={product.productId} productToOrder={product}/>)}
    </ListGroup>
}

export default OrderCard;

