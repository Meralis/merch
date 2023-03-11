import {Button, Card, Col} from "react-bootstrap"
import {Link} from "react-router-dom";

function Product({product, addToBasket, removeFromBasket}) {
    return <Col sm={6} md={4} lg={3} className={'d-flex mt-3'}>
        <Card className={'d-flex flex-column p-3 align-items-start'}>
            <div className={'flex-grow-1'}>
                <div className='text-center pt-3'>
                    <img src={product.imageUrl} width={"140"} height={"180"} alt="photo"/>
                </div>
                <Link to={`/product/${product.productId}`}>
                    <Card.Title>{product.title}</Card.Title>
                </Link>
                <p>Category: {product.category}</p>
                <p>${product.price}</p>
            </div>
            {product.addedToBasket ?
                <Button variant="danger" onClick={() => removeFromBasket(product.productId)}>Видалити</Button> :
                <Button variant="success" onClick={() => addToBasket(product.productId)}>До кошика</Button>}
        </Card>
    </Col>
}

export default Product;