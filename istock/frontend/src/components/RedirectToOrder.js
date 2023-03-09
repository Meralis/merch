import {Navigate} from "react-router-dom";
import {Button} from "react-bootstrap";
import {useState} from "react";

function RedirectToOrder() {
    const [isRedirected, setIsRedirected] = useState(false);

    function redirectToOrder() {
        setIsRedirected(true);
    }

    return <div className={'text-center'}>
        {isRedirected ? <Navigate to={'/order'}></Navigate> : ''}
        <Button variant={'success'} onClick={redirectToOrder}>Оформити замовлення</Button>
    </div>
}

export default RedirectToOrder;