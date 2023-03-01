import {Button, Card, Col} from "react-bootstrap"

function Product({product}) {
    return <Col sm={6} md={4} lg={3}>
        <Card className={'d-flex flex-column'}>
            <div className='text-center pt-3'>
                <img src={product.image} width={"140"} height={"180"} alt="photo"/>
            </div>
            <Card.Body>
                <Card.Title>{product.title}</Card.Title>
                <p>Category: {product.category}</p>
                <p>${product.price.toFixed(2)}</p>
                {/*<Card.Text>{product.description}</Card.Text>*/}
                <Button variant="success">Add to Cart</Button>
            </Card.Body>
        </Card>
    </Col>
}

export default Product;