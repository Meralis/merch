import {useContext, useEffect, useState} from "react";
import ChildListCategory from "./ChildListCategory";
import Product from "./Product";
import {Col, Row} from "react-bootstrap";
import ProductContext from "../../context/ProductContext";

function SelectCategories() {
    const [products, setProducts] = useContext(ProductContext);
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState('');
    const [productsByCategory, setProductsByCategory] = useState([]);

    async function sendCategoryRequest(selectedCategory) {
        const response = await fetch('http://localhost:8080/product/category', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: selectedCategory
        });
        return await response.json();
    }

    useEffect(() => {
        function selectProductByCategory() {
            return products.map(product => {
                let selectedByCategory = false;
                productsByCategory.forEach(productByCategory => {
                    if (productByCategory.productId === product.productId) {
                        selectedByCategory = true;
                    }
                });
                return {
                    ...product,
                    selectedByCategory: selectedByCategory
                };
            });
        }

        const updateProducts = selectProductByCategory();
        setProducts(updateProducts);
    }, [productsByCategory])

    useEffect(() => {
        if (selectedCategory) {
            sendCategoryRequest(selectedCategory).then(data => {
                setProductsByCategory(data);
            })
        }
    }, [selectedCategory])

    async function sendCategoryTreeRequest() {
        const response = await fetch('http://localhost:8080/product/categoryTree', {
            method: 'GET',
            headers: {'Content-Type': 'application/json'},
        });
        return await response.json();
    }

    useEffect(() => {
        sendCategoryTreeRequest().then(data => setCategories(data));
    }, [])

    const handleCategoryChange = (currentCategory) => {
        setSelectedCategory(currentCategory);
    }
    return <>
        <div className={'main-nav-block'}>
            <ChildListCategory children={categories.children} parentName={categories.name}
                               handleCategoryChange={handleCategoryChange}/>
        </div>
        {selectedCategory && (
            <Row>
                <Col xs={12}>
                </Col>
                {products
                    .filter(product => product.selectedByCategory)
                    .map(product => <Product key={product.productId} product={product}/>)
                }
            </Row>
        )}
    </>
}

export default SelectCategories;