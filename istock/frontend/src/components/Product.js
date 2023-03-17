import {Button, Card, Col} from "react-bootstrap"
import {Link} from "react-router-dom";
import removeFromBasket from "../common/RemoveFromBasket";
import {useContext} from "react";
import ProductContext from "../context/ProductContext";
import addToBasket from "../common/AddToBasket";

function Product({product}) {
    const [products, setProducts] = useContext(ProductContext);
    return <Col sm={6} md={4} lg={3} className={'d-flex mt-3'}>
        <Card className={'d-flex flex-column p-3 align-items-start'}>
            <div className={'flex-grow-1'}>
                <div className='text-center pt-3'>
                    <img src={product.imageUrl} width={"140"} height={"180"} alt="photo"/>
                </div>
                <Link to={`/product/${product.productId}`}>
                    <Card.Title>{product.title}</Card.Title>
                </Link>
                <p>${product.price}</p>
            </div>
            {product.addedToBasket ?
                <Button variant="danger" onClick={() => removeFromBasket(product.productId, products, setProducts)}>Видалити</Button> :
                <Button variant="success" onClick={() => addToBasket(product.productId, products, setProducts)}>До кошика</Button>}
        </Card>
    </Col>
}

export default Product;