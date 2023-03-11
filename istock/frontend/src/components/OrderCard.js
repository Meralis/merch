import {Alert, ListGroup} from "react-bootstrap";
import {useContext} from "react";
import ProductContext from "../context/ProductContext";
import OrderCardItem from "./OrderCardItem";

function getProductById(savedItem, products) {
    const foundProducts = products.filter(product => product.productId === savedItem.productId);
    console.log('products',products)
    console.log('foundProducts',foundProducts)

    if (foundProducts.length > 0) {
        return {...savedItem, ...foundProducts[0]};
    }
    return undefined;
}

function OrderCard({basketItems}) {
    const [products] = useContext(ProductContext);
    console.log('products', products)

    if (basketItems) {
        let savedItems = JSON.parse(basketItems);

        const savedProducts = savedItems.map(savedItem => getProductById(savedItem, products))
            .filter(product => product);


        console.log('savedProducts', savedProducts)
        console.log("savedItems",savedItems)
        return <ListGroup className={'my-4'}>
            {/*{savedProducts.map(product => <OrderCardItem key={product.productId} productToOrder={product}/>)}*/}

        </ListGroup>
    } else {
        return <Alert variant="warning"> Basket is empty</Alert>
    }
}

export default OrderCard;

