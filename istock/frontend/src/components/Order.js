import OrderForm from "./OrderForm";
import OrderCard from "./OrderCard";

function Order() {
    const basketItems = localStorage.getItem('basketItems');

    return <>
    <OrderForm basketItems={basketItems}/>
    <OrderCard basketItems={basketItems}/>
    </>
}
export default Order;