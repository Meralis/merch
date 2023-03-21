import OrderForm from "./OrderForm";
import OrderCard from "./OrderCard";
import {Alert} from "react-bootstrap";

function Order() {
    const savedItems = localStorage.getItem('basketItems');
    if(savedItems) {
        let basketItems = JSON.parse(savedItems);

        return <>
            <OrderForm basketItems={basketItems}/>
            <OrderCard basketItems={basketItems}/>
        </>
    }else {
        return <Alert variant="warning">Basket is empty</Alert>
    }
}
export default Order;