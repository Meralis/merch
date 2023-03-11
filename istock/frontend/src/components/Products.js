import {useEffect, useState} from "react";
import {Col, Row} from "react-bootstrap";
import Product from "./Product";
import Basket from "./Basket";
import ClientContext from "../context/ClientContext";

function Products() {
    const [products, setProducts] = useState([]);
    // const [client, setClient] = useState({});
    // const [categories, setCategories] = useState([]);
    // const [selectedCategory, setSelectedCategory] = useState('');

    useEffect(() => {

        const savedBasket = localStorage.getItem('basketItems');

        fetch('http://localhost:8080/product').then(data => data.json()).then(data => {
            if (savedBasket) {
                let savedItems = JSON.parse(savedBasket);
                for (let product of data) {
                    const savedProduct = savedItems.filter(savedItem => product.productId === savedItem.productId);
                    product.addedToBasket = savedProduct.length;
                    product.count = savedProduct.length ? savedProduct[0].count : 1;
                }
                setProducts(data);
            } else {
                setProducts(data.map(product => ({...product, addedToBasket: false, count: 1})));
            }

            // getAllCategories(data);///чи можна так?????????
        })
    }, []);


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

    function saveProducts(updatedProducts) {
        const productsToSave = updatedProducts.filter(product => product.addedToBasket).map(product => ({
            productId: product.productId,
            count: product.count
        }));
        localStorage.setItem('basketItems', JSON.stringify(productsToSave));
    }

    function changeCount(productId, newCount) {
        const newProducts = products.map(product => ({
            ...product,
            count: productId === product.productId ? newCount : product.count
        }))
        setProducts(newProducts);
        saveProducts(newProducts);
    }

    return <>
        <Row>
            <Col xs={12}>
                {/*<ClientContext.Provider value={{client}}>*/}
                    <Basket
                        changeCount={changeCount}
                        products={products.filter(product => product.addedToBasket)}
                        removeFromBasket={removeFromBasket}
                    />
                {/*</ClientContext.Provider>*/}
            </Col>
            {/*<SelectCategories categories={categories} setSelectedCategory={setSelectedCategory}/>*/}

            {/*{products.filter(product => product.category === selectedCategory || !selectedCategory)*/}

            {products.map(product => <Product key={product.productId} product={product}
                                              addToBasket={addToBasket} removeFromBasket={removeFromBasket}
            />)}
        </Row>
    </>
}

export default Products;