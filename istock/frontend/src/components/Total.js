import {ListGroup} from "react-bootstrap";

function Total({products}) {
    return (
        <ListGroup.Item active>
            <b>Total: ${products.reduce((total, product) => total + (product.count * product.price), 0).toFixed(2)}</b>
        </ListGroup.Item>
    );
}

export default Total;