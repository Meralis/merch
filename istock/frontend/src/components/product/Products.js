import {useContext} from "react";
import {Col, Row} from "react-bootstrap";
import Product from "./Product";
import ProductContext from "../../context/ProductContext";

function Products() {
    const [products] = useContext(ProductContext);
    return <>
        <Row>
            <Col xs={12}>
            </Col>
            {products.map(product => <Product key={product.productId} product={product}
            />)}
        </Row>
    </>
}

export default Products;