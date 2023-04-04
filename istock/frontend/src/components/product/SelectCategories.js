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
        }
    }, [selectedCategory])

    function getAllCategories(products) {
        const categories = products.reduce(
            (acc, product) => (acc.includes(product.category) ? acc : [...acc, product.category]), [])
        setCategories(categories);
    }

    useEffect(() => {
        if (products.length > 0) {
            getAllCategories(products);
        }
    }, [products])

    // function getSubCategory(currentCategory) {
    //     categories.filter(category => {
    //         const startIndex = category.indexOf(currentCategory);
    //        if (category.indexOf(currentCategory)!==-1){
    //            const subCategory = category.slice(startIndex + currentCategory.length + 1);
    //            console.log(subCategory)
    //        }
    //     })
    // }

    function getSubCategories(selectedCategory) {
        return categories
            .filter(category => category.indexOf(selectedCategory) !== -1)
            .map(category => category.slice(selectedCategory.length + 1));
    }

    getSubCategories("cat");

    return <>
        <div className={'main-nav-block'}>
            <ul onClick={() => setSelectedCategory('dog')}>Собаки</ul>
            {selectedCategory === 'dog' &&
                (categoryResults.map(product => (
                    <li key={product.productId}>{product.title}</li>
                )))
            }
        </div>
        <div className={'main-nav-block'}>
            <ul onClick={() => setSelectedCategory('cat')}>Кішки</ul>
            {categories.map(category => (
                <li>{category}</li>
            ))}
        </div>
    </>


}

export default SelectCategories;

// 0: "cat.food.dry"
// "Кішки.Корм.Сухий корм"
// 1: "cat.hygiene.care"
// "Кішки.Гігієна.Засоби по догляду"
// 2: "cat.hygiene.shampoo"
// "Кішки.Гігієна.Шампуні"
// 3: "cat.hygiene.toilet"
// "Кішки.Гігієна.Туалетні наповнювачі"
// 4: "dog.food.dry"
// "Собаки.Корм.Сухий корм"
// 5: "dog.hygiene.care"
// "Собаки.Гігієна.Догляд"
// 6: "dog.hygiene.shampoo"
// "Собаки.Гігієна.Шампуні"
// 7: "dog.hygiene.deaper"
// "Собаки.Гігієна.Пелюшки"
// 8: "cat.food.wet"
// "Собаки.Корм.Консерви"
// 9: "dog.food.wet"
// "Собаки.Корм.Консерви"
// 10: "cat.food.medicinal"
// "Кішки.Корм.Лікувальний корм"
// 11: "dog.food.medicinal"
// "Собаки.Корм.Лікувальний корм"
// 12: "cat.food.vitamins"
// "Кішки.Корм.Вітаміни"
// 13: "dog.food.vitamins"
// "Собаки.Корм.Вітаміни"
// 14: "cat.health.insect"
// "Кішки.Здоров'я.Від комах"
// 15: "cat.health.worm"
// "Кішки.Здоров'я.Від глистів"
// 16: "dog.health.insect"
// "Собаки.Здоров'я.Від комах"
// 17: "dog.health.worm"
// "Собаки.Здоров'я.Від глистів"