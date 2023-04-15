import {Button, Card, Col} from "react-bootstrap"
import {Link} from "react-router-dom";
import addToBasket from "../../utils/addToBasket";
import {saveProducts} from "../../utils/saveProducts";
import {useContext} from "react";
import ProductContext from "../../context/ProductContext";
import removeFromBasket from "../../utils/removeFromBasket";

function Product({product}) {
    const [products, setProducts] = useContext(ProductContext);

    function handleAddingProducts(productId) {
        const newProducts = addToBasket(products, productId);
        setProducts(newProducts);
        saveProducts(newProducts);
    }

    function handleRemovingProducts(productId) {
        const newProducts = removeFromBasket(products, productId);
        setProducts(newProducts);
        saveProducts(newProducts);
    }

    return <Col sm={6} md={4} lg={3} className={'d-flex mt-3'}>
        <Card className={'d-flex flex-column p-3 align-items-start card'}>
            <div className={'flex-grow-1 d-flex flex-column justify-content-between'}>
                <div className='text-center card_body'>
                    <img src={product.imageUrl} width={"120"} height={"140"} alt="photo"/>
                    <Link to={`/product/${product.productId}`} className={'card_link text-left mt-2'}>
                        <div className={'card_title'}>{product.title}</div>
                    </Link>
                </div>
                <div className="align-self-start">
                    <p className={'card_price'}>{product.price} грн</p>
                </div>
            </div>
            {product.addedToBasket ?
                <Button variant="danger" size={'sm'} onClick={() => handleRemovingProducts(product.productId)}>Видалити</Button> :
                <Button variant="success" size={'sm'} onClick={() => handleAddingProducts(product.productId)}>До кошика</Button>}
        </Card>
    </Col>
}

export default Product;