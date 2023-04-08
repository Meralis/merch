import {useContext, useEffect, useState} from "react";
import ProductContext from "../../context/ProductContext";
import ChildListCategory from "./ChildListCategory";

function SelectCategories() {
    const [products, setProducts] = useContext(ProductContext);
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState('');

    async function sendCategoryRequest(selectedCategory) {
        const response = await fetch('http://localhost:8080/product/category', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: selectedCategory
        });
        return await response.json();
    }

    useEffect(() => {
        if (selectedCategory) {
            sendCategoryRequest(selectedCategory).then(data => setProducts(data));
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
    </>
}

export default SelectCategories;