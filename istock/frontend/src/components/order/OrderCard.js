import {ListGroup} from "react-bootstrap";
import {useContext} from "react";
import ProductContext from "../../context/ProductContext";
import OrderCardItem from "./OrderCardItem";
import Total from "../Total";
import {getProductById} from "../../utils/getProductById";

function OrderCard() {
    const [products] = useContext(ProductContext);
    const savedItems = localStorage.getItem('basketItems');
    let basketItems = JSON.parse(savedItems);
    const savedProducts = basketItems.map(basketItem => getProductById(basketItem, products))
        .filter(product => product);
    return <>
        <ListGroup className={'my-4'}>
            {savedProducts.map(product => <OrderCardItem key={product.productId} productToOrder={product}/>)}
        </ListGroup>
        <Total products={products.filter(product => product.addedToBasket)}/>
    </>
}

export default OrderCard;

