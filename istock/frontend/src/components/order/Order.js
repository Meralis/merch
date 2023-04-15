import OrderForm from "./OrderForm";
import OrderCard from "./OrderCard";

function Order() {
    return <>
        <div className={'order'}>
            <OrderCard/>
            <OrderForm/>
        </div>
    </>
}

export default Order;