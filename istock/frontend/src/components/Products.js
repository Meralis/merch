import {useContext} from "react";
import {Col, Row} from "react-bootstrap";
import Product from "./Product";
import Basket from "./basket/Basket";
import ProductContext from "../context/ProductContext";
import {saveProducts} from "../utils/saveProducts";


function Products() {
    const [products, setProducts] = useContext(ProductContext);
    // const [client, setClient] = useState({});
    // const [categories, setCategories] = useState([]);
    // const [selectedCategory, setSelectedCategory] = useState('');


    // function getAllCategories(products) {
    //     const categories = products.reduce(
    //         (acc, product) => (acc.includes(product.category) ? acc : [...acc, product.category]), [])
    //     setCategories(categories);
    // }

    function addToBasket(productId) {
        const newProducts = products.map(product => ({
            ...product,
            addedToBasket: productId === product.productId ? true : product.addedToBasket
        }))
        setProducts(newProducts);
        saveProducts(newProducts);
    }

    function removeFromBasket(productId) {
        const newProducts = products.map(product => ({
            ...product,
            addedToBasket: productId === product.productId ? false : product.addedToBasket,
            count: productId === product.productId ? 1 : product.count
        }))
        setProducts(newProducts);
        saveProducts(newProducts);
    }

    // function saveProducts(updatedProducts) {
    //     const productsToSave = updatedProducts.filter(product => product.addedToBasket).map(product => ({
    //         productId: product.productId,
    //         count: product.count
    //     }));
    //     localStorage.setItem('basketItems', JSON.stringify(productsToSave));
    // }

    // function changeCount(productId, newCount) {
    //     const newProducts = products.map(product => ({
    //         ...product,
    //         count: productId === product.productId ? newCount : product.count
    //     }))
    //     setProducts(newProducts);
    //     saveProducts(newProducts);
    // }

    return <>
        <Row>
            <Col xs={12}>
                {/*<ClientContext.Provider value={{client}}>*/}
                <Basket
                    // changeCount={changeCount}
                    products={products.filter(product => product.addedToBasket)}
                    removeFromBasket={removeFromBasket}
                />
            </Col>
            {/*<SelectCategories categories={categories} setSelectedCategory={setSelectedCategory}/>*/}

            {/*{products.filter(product => product.category === selectedCategory || !selectedCategory)*/}

            {products.map(product => <Product key={product.productId} product={product}
                                              addToBasket={addToBasket}
                                              removeFromBasket={removeFromBasket}
            />)}
        </Row>
    </>
}

export default Products;