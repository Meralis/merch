import {useParams} from "react-router-dom";
import {useContext, useEffect, useState} from "react";
import {Button, Card} from "react-bootstrap";
import ProductContext from "../../context/ProductContext";
import addToBasket from "../../utils/addToBasket";
import {saveProducts} from "../../utils/saveProducts";
import removeFromBasket from "../../utils/removeFromBasket";
import {API_URL} from "../../constants/constants"

function ProductItem() {
    const {productId} = useParams();
    const [product, setProduct] = useState({});
    const [products, setProducts] = useContext(ProductContext);

    function handleAddingProducts(productId) {
        const newProducts = addToBasket(products, productId);
        const newProduct = newProducts.filter(product => product.productId === productId);
        setProducts(newProducts);
        setProduct(newProduct[0]);
        saveProducts(newProducts);
    }

    function handleRemovingProducts(productId) {
        const newProducts = removeFromBasket(products, productId);
        const newProduct = newProducts.filter(product => product.productId === productId);
        setProducts(newProducts);
        setProduct(newProduct[0]);
        saveProducts(newProducts);
    }

    useEffect(() => {
        const savedBasket = localStorage.getItem('basketItems');
        if ({productId}) {
            fetch(`${API_URL}/product/` + productId).then(data => data.json()).then(data => {
                if (savedBasket) {
                    let savedItems = JSON.parse(savedBasket);
                    const savedProduct = savedItems.filter(savedItem => data.productId === savedItem.productId);
                    data.addedToBasket = savedProduct.length > 0;
                    data.count = savedProduct.length ? savedProduct[0].count : 1;
                    setProduct(data);
                } else {
                    setProduct(product => ({...product, addedToBasket: false, count: 1}));
                }
            })
        }
    }, [productId])

    return <>
        <Card className={'productCard'}>
            <Card.Img src={product.imageLink} style={{height: '20rem', width: '18rem'}}/>
            <Card.Body>
                <Card.Title><b>{product.title}</b></Card.Title>
                <Card.Text className="text-justify pr-3">{product.description}</Card.Text>
                {product.addedToBasket ?
                    <Button variant="warning" className={'text-white'} size={'sm'}
                            onClick={() => handleRemovingProducts(product.productId)}>Видалити</Button> :
                    <Button variant="success" size={'sm'}
                            onClick={() => handleAddingProducts(product.productId)}>До кошика</Button>}
            </Card.Body>
        </Card>
    </>
}

export default ProductItem;