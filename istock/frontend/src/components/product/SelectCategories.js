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

    function ChildList({children}) {
        if (!children || !children.length) {
            return null;
        }
        return (
            <ul className={'main-nav'}>
                {children.map(child => (
                    <li key={child.name}>{child.name}
                        <ChildList children={child.children}/>
                    </li>
                ))}
            </ul>
        );
    }

    return <>
        <div className={'main-nav-block'}>
            <ChildList children={categories.children}/>
        </div>
    </>

}

export default SelectCategories;

// 0: "cat.food.dry"
// "Кішки.Харчування.Сухий корм"
// 1: "cat.hygiene.care"
// "Кішки.Гігієна.Засоби по догляду"
// 2: "cat.hygiene.shampoo"
// "Кішки.Гігієна.Шампуні"
// 3: "cat.hygiene.toilet"
// "Кішки.Гігієна.Наповнювачі"
// 4: "dog.food.dry"
// "Собаки.Харчування.Сухий корм"
// 5: "dog.hygiene.care"
// "Собаки.Гігієна.Засоби по догляду"
// 6: "dog.hygiene.shampoo"
// "Собаки.Гігієна.Шампуні"
// 7: "dog.hygiene.deaper"
// "Собаки.Гігієна.Пелюшки"
// 8: "cat.food.wet"
// "Собаки.Харчування.Консервований корм"
// 9: "dog.food.wet"
// "Собаки.Харчування.Консервований корм"
// 10: "cat.food.medicinal"
// "Кішки.Харчування.Лікувальний корм"
// 11: "dog.food.medicinal"
// "Собаки.Харчування.Лікувальний корм"
// 12: "cat.food.vitamins"
// "Кішки.Харчування.Вітаміни та добавки"
// 13: "dog.food.vitamins та добавки"
// "Собаки.Харчування.Вітаміни та добавки"
// 14: "cat.health.insect"
// "Кішки.Здоров'я.Від комах"
// 15: "cat.health.worm"
// "Кішки.Здоров'я.Від глистів"
// 16: "dog.health.insect"
// "Собаки.Здоров'я.Від комах"
// 17: "dog.health.worm"
// "Собаки.Здоров'я.Від глистів"