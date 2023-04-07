import {useContext, useEffect, useState} from "react";
import ProductContext from "../../context/ProductContext";

function SelectCategories() {
    const [products] = useContext(ProductContext);
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState('');
    const [categoryResults, setCategoryResults] = useState([]);

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
            sendCategoryRequest(selectedCategory).then(data => setCategoryResults(data));
            console.log(selectedCategory);
            console.log(categoryResults);
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

    function ChildList({children, parentName}) {
        const handleClick = (event, name) => {
            event.stopPropagation();
            setSelectedCategory(`${parentName}.${name}`);
        };
        if (!children || !children.length) {
            return null;
        }
        return (
            <ul className={'main-nav'}>
                {children.map(child => (
                    <li key={child.name} onClick={(event) => handleClick(event, child.name)}>
                        {child.name}
                        <ChildList children={child.children} parentName={`${parentName}.${child.name}`}/>
                    </li>
                ))}
            </ul>
        );
    }

    return <>
        <div className={'main-nav-block'}>
            <ChildList children={categories.children} parentName={categories.name} />
        </div>
    </>

}

export default SelectCategories;