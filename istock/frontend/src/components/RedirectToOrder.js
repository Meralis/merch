import {useNavigate} from "react-router-dom";
import {Button} from "react-bootstrap";

function RedirectToOrder() {
    const navigate = useNavigate();

    function handleRedirect() {
        navigate("/order");
    }

    return <div className={'text-center'}>
        <Button variant={'success'} size={'sm'} onClick={handleRedirect}>Оформити замовлення</Button>
    </div>
}

export default RedirectToOrder;